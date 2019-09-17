package org.oddys.timetracking.service;

import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.UserActivityDao;
import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.util.ConfigManager;

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
}
