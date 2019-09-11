package org.oddys.timetracking.dao;

import java.sql.Connection;

public interface DaoFactory {
    RoleDao getRoleDao();
    UserDao getUserDao();
    ActivityDao getActivityDao();
    UserActivityDao getUserActivityDao();
//    ActivityRecordDao getActivityRecordDao(Connection connection);
}
