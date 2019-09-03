package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.dao.ActivityDao;
import org.oddys.timetracking.dao.DaoFactory;
import org.oddys.timetracking.dao.RoleDao;
import org.oddys.timetracking.dao.UserDao;

import java.sql.Connection;

public class MysqlDaoFactory implements DaoFactory {
    private static DaoFactory INSTANCE = new MysqlDaoFactory();

    private MysqlDaoFactory() {
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public RoleDao getRoleDao() {
        return new MysqlRoleDao();
    }

    @Override
    public UserDao getUserDao() {
        return new MysqlUserDao();
    }

    @Override
    public ActivityDao getActivityDao(Connection connection) {
        return new MysqlActivityDao(connection);
    }

//    @Override
//    public UserActivityDao getUserActivityDao() {
//        return null;
//    }
//
//    @Override
//    public ActivityRecordDao getActivityRecordDao() {
//        return null;
//    }
}
