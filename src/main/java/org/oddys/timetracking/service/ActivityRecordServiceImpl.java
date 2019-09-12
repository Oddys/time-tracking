package org.oddys.timetracking.service;

import org.oddys.timetracking.dao.ActivityRecordDao;
import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.util.ConfigManager;

public class ActivityRecordServiceImpl implements ActivityRecordService {
    private static final ActivityRecordService INSTANCE = new ActivityRecordServiceImpl();
    private ActivityRecordDao dao;

    private ActivityRecordServiceImpl() {
        String dbmsName = ConfigManager.getInstance().getProperty(ConfigManager.DBMS);
        dao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getActivityRecordDao();
    }

    public static ActivityRecordService getInstance() {
        return INSTANCE;
    }

    @Override
    public long getNumberOfRows() throws ServiceException {
        try {
            return dao.getNumberOfRows();
        } catch (DaoException e) {
            throw new ServiceException("Failed to obtain number of rows", e);
        }
    }
}