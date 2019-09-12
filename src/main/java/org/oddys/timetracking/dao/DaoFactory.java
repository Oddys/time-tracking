package org.oddys.timetracking.dao;

public interface DaoFactory {
    RoleDao getRoleDao();
    UserDao getUserDao();
    ActivityDao getActivityDao();
    UserActivityDao getUserActivityDao();
    ActivityRecordDao getActivityRecordDao();
}
