package org.oddys.timetracking.service;

import org.modelmapper.ModelMapper;
import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.dto.PageDto;
import org.oddys.timetracking.dto.UserActivitiesDto;
import org.oddys.timetracking.dto.UserActivityDto;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.entity.UserActivity;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;

import java.util.List;
import java.util.stream.Collectors;

public class UserActivityServiceImpl implements UserActivityService {
    private static final UserActivityService INSTANCE = new UserActivityServiceImpl();
    private UserActivityDao userActivityDao;
    private UserDao userDao;
    private final ModelMapper modelMapper = ModelMapperWrapper.getMapper();

    private UserActivityServiceImpl() {
        String dbmsName = ConfigManager.getInstance().getProperty(ConfigManager.DBMS);
        userActivityDao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getUserActivityDao();
        userDao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getUserDao();
    }

    public static UserActivityService getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean requestActivityAssigned(Long userId, Long activityId) {
        UserActivity userActivity = userActivityDao.findByUserIdAndActivityId(userId, activityId);
        if (userActivity == null) {
            return userActivityDao.add(userId, activityId, false, true) > 0;
        } else if (!userActivity.getStatusChangeRequested() && !userActivity.getAssigned()) {
            userActivity.setStatusChangeRequested(true);
            return userActivityDao.update(userActivity) > 0;
        }
        return false;
    }

    @Override
    public boolean requestUserActivityStop(Long userActivityId) {
        return userActivityDao.requestStatusChange(userActivityId) > 0;
    }

    @Override
    public long getNumberOfPagesStatusChangeRequested(int rowsPerPage) {
        long numRows = userActivityDao.getNumberOfStatusChangeRequested();
        long numPages = numRows / rowsPerPage;
        return numRows % rowsPerPage == 0 ? numPages : ++numPages;
    }

    @Override
    public PageDto<UserActivityDto> findAllStatusChangeRequested(long currentPage, int rowsPerPage) {
        PageDto<UserActivityDto> page = new PageDto<>();
        page.setCurrentPage(currentPage);
        page.setRowsPerPage(rowsPerPage);
        List<UserActivityDto> userActivities = userActivityDao.findAllStatusChangeRequested(currentPage, rowsPerPage)
                .stream()
                .map(ua -> modelMapper.map(ua, UserActivityDto.class))
                .collect(Collectors.toList());
        page.setElements(userActivities);
        page.setNumPages(getNumberOfPagesStatusChangeRequested(rowsPerPage));
        return page;
    }

    @Override
    public boolean changeStatus(Long userActivityId, boolean currentValue) {
        UserActivity userActivity = userActivityDao.findById(userActivityId);
        if (userActivity == null || !userActivity.getStatusChangeRequested()) {
            return false;
        }
        return userActivityDao.updateAssignedAndStatusChangeRequested(userActivityId, !currentValue) > 0;
    }

    @Override
    public UserActivitiesDto searchUserActivitiesByUserId(Long userId) {
        UserActivitiesDto dto = new UserActivitiesDto();
        User user = userDao.findById(userId);
        if (user == null) {
            return dto;
        }
        dto.setUserId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        List<UserActivityDto> activities = userActivityDao.findAllByUserId(userId).stream()
                .map(ua -> ModelMapperWrapper.getMapper().map(ua, UserActivityDto.class))
                .collect(Collectors.toList());
        dto.setUserActivities(activities);
        return dto;
    }
}
