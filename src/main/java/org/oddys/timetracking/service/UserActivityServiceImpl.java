package org.oddys.timetracking.service;

import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.dto.UserActivityDto;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserActivityServiceImpl implements UserActivityService {
    private static final UserActivityService INSTANCE = new UserActivityServiceImpl();
    private UserActivityDao dao;

    private UserActivityServiceImpl() {
        String dbmsName = ConfigManager.getInstance().getProperty(ConfigManager.DBMS);
        dao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getUserActivityDao();
    }

    public static UserActivityService getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean addUserActivity(Long userId, Long activityId) throws ServiceException {
        try {
            if (dao.exists(userId, activityId)) {
                return false;
            }
            return dao.add(userId, activityId, false, true) > 0;
        } catch (DaoException e) {
            throw new ServiceException("UserActivityService failed to add UserActivity", e);
        }
    }

    @Override
    public boolean requestStatusChange(Long userActivityId) throws ServiceException {
        try {
            return dao.requestStatusChange(userActivityId) > 0;
        } catch (DaoException e) {
            throw new ServiceException("UserActivityService failed to request for status change", e);
        }
    }

    @Override
    public long getNumberOfPagesStatusChangeRequested(int rowsPerPage) throws ServiceException {
        try {
            long numRows = dao.getNumberOfStatusChangeRequested();
            long numPages = numRows / rowsPerPage;
            return numRows % rowsPerPage == 0 ? numPages : ++numPages;
        } catch (DaoException e) {
            throw new ServiceException("Failed to get the number of pages", e);
        }
    }

    @Override
    public List<UserActivityDto> findAllStatusChangeRequested(long currentPage, int rowsPerPage) throws ServiceException {
        try {
            return dao.findAllStatusChangeRequested(currentPage, rowsPerPage).stream()
                    .map(ua -> ModelMapperWrapper.getMapper().map(ua, UserActivityDto.class))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (DaoException e) {
            throw new ServiceException("Failed to find UserActivities", e);
        }
    }
    @Override
    public boolean processActivityRequest(Long userActivityId, boolean currentValue) throws ServiceException {
        try {
            return dao.updateAssignedAndStatusChangeRequested(userActivityId, !currentValue) > 0;
        } catch (DaoException e) {
            throw new ServiceException("UserActivityService failed to process an activity request", e);
        }
    }
}
