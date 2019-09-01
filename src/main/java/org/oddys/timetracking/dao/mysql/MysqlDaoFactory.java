package org.oddys.timetracking.dao.mysql;

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
    public RoleDao getRoleDao(Connection connection) {
        return new MysqlRoleDao(connection);
    }

    @Override
    public UserDao getUserDao(Connection connection) {
        return new MysqlUserDao(connection);
    }

//    @Override
//    public ActivityDao getActivityDao() {
//        return null;
//    }
//
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
