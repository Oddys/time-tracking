package org.oddys.timetracking.dao;

import java.sql.Connection;

public interface DaoFactory {
    DaoFactory getInstance();
    RoleDao getRoleDao(Connection connection);
//    UserDao getUserDao(Connection connection);
//    ActivityDao getActivityDao(Connection connection);
//    UserActivityDao getUserActivityDao(Connection connection);
//    ActivityRecordDao getActivityRecordDao(Connection connection);
}
