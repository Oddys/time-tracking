package org.oddys.timetracking.service;

import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.dao.DaoException;
import org.oddys.timetracking.dto.UserActivityDto;
import org.oddys.timetracking.entity.UserActivity;
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
    public boolean assignActivity(Long userId, Long activityId) {
        UserActivity userActivity = dao.findByUserIdAndActivityId(userId, activityId);
        if (userActivity == null) {
            return dao.add(userId, activityId, false, true) > 0;
        } else if (!userActivity.getStatusChangeRequested() && !userActivity.getAssigned()) {
            userActivity.setStatusChangeRequested(true);
            return dao.update(userActivity) > 0;
        }
        return false;
    }

    @Override
    public boolean requestStatusChange(Long userActivityId) {
        return dao.requestStatusChange(userActivityId) > 0;
    }

    @Override
    public long getNumberOfPagesStatusChangeRequested(int rowsPerPage) {
        try {
            long numRows = dao.getNumberOfStatusChangeRequested();
            long numPages = numRows / rowsPerPage;
            return numRows % rowsPerPage == 0 ? numPages : ++numPages;
        } catch (DaoException e) {
            throw new ServiceException("Failed to get the number of pages", e);
        }
    }

    @Override
    public List<UserActivityDto> findAllStatusChangeRequested(long currentPage, int rowsPerPage) {
        return dao.findAllStatusChangeRequested(currentPage, rowsPerPage).stream()
                .map(ua -> ModelMapperWrapper.getMapper().map(ua, UserActivityDto.class))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public boolean changeUserActivityStatus(Long userActivityId, boolean currentValue) {
        return dao.updateAssignedAndStatusChangeRequested(userActivityId, !currentValue) > 0;
    }
}
