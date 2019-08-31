package org.oddys.timetracking.util;

import org.oddys.timetracking.dao.DaoFactory;
import org.oddys.timetracking.dao.mysql.MysqlDaoFactory;

import java.util.Optional;

public enum DaoFactoryProvider {
    MYSQL() {
        @Override
        public DaoFactory getFactory() {
            return MysqlDaoFactory.getInstance();
        }
    };

    public abstract DaoFactory getFactory();
//    private static final DaoFactoryProvider INSTANCE = new DaoFactoryProvider();
//
//    private DaoFactoryProvider() {}
//
//    public static DaoFactoryProvider getInstance() {
//        return INSTANCE;
//    }
//
//    public Optional<DaoFactory> getDaoFactory(String rdbms) {
//        switch (rdbms) {
//            case "MYSQL":
//                return Optional.of(MysqlDaoFactory.getInstance());
//            default:
//                return Optional.empty();
//        }
//    }
}
