package org.oddys.timetracking.dao;

public interface DaoFactory {
    DaoFactory getInstance();
    RoleDao getRoleDao();
//    UserDao getUserDao();
//    ActivityDao getActivityDao();
//    UserActivityDao getUserActivityDao();
//    ActivityRecordDao getActivityRecordDao();
}
