package org.oddys.timetracking.dao;

import java.sql.Connection;

public interface DaoFactory {
    RoleDao getRoleDao();
    UserDao getUserDao();
    ActivityDao getActivityDao(Connection connection);
//    UserActivityDao getUserActivityDao(Connection connection);
//    ActivityRecordDao getActivityRecordDao(Connection connection);
}
