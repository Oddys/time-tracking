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

public class SearchUserActivitiesServiceImpl implements SearchUserActivitiesService {
    private static final SearchUserActivitiesServiceImpl INSTANCE = new SearchUserActivitiesServiceImpl();
    private UserActivityDao userActivityDao;

    private SearchUserActivitiesServiceImpl() {
        String dbmsName = ConfigManager.getInstance().getProperty(ConfigManager.DBMS);
        userActivityDao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getUserActivityDao();
    }

    public static SearchUserActivitiesServiceImpl getInstance() {
        return INSTANCE;
    }
    @Override
    public List<UserActivityDto> searchUserActivitiesByUserId(Long userId) throws ServiceException {
        try {
            return userActivityDao.findAllByUserId(userId).stream()
                    .map(ua -> ModelMapperWrapper.getMapper().map(ua, UserActivityDto.class))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (DaoException e) {
            throw new ServiceException("Failed to find UserActivities by User's ID", e);
        }
    }
}
