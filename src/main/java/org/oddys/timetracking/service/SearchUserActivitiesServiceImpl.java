package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.UserActivitiesDto;
import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.dto.UserActivityDto;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchUserActivitiesServiceImpl implements SearchUserActivitiesService {
    private static final SearchUserActivitiesServiceImpl INSTANCE = new SearchUserActivitiesServiceImpl();
    private UserDao userDao;
    private UserActivityDao userActivityDao;

    private SearchUserActivitiesServiceImpl() {
        String dbmsName = ConfigManager.getInstance().getProperty(ConfigManager.DBMS);
        userActivityDao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getUserActivityDao();
        userDao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getUserDao();
    }

    public static SearchUserActivitiesServiceImpl getInstance() {
        return INSTANCE;
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
                .collect(Collectors.toCollection(ArrayList::new));
        dto.setUserActivities(activities);
        return dto;
    }
}
