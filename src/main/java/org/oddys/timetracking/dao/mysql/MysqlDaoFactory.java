package org.oddys.timetracking.dao.mysql;

import org.oddys.timetracking.dao.Dao;
import org.oddys.timetracking.dao.DaoFactory;
import org.oddys.timetracking.dao.RoleDao;

import java.util.Map;

public class MysqlDaoFactory implements DaoFactory {
    private DaoFactory INSTANCE = new MysqlDaoFactory();
    private Map<String, Dao> daoInstances;

    private MysqlDaoFactory() {
        daoInstances.put("roleDao", new MysqlRoleDao());
//        daoInstances.put("userDao", new MysqlUserDao());
        // TODO Add other daos
    }

    @Override
    public DaoFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public RoleDao getRoleDao() {
        return null;
    }

//    @Override
//    public UserDao getUserDao() {
//        return null;
//    }
//
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
