package org.oddys.timetracking.dao;

import org.oddys.timetracking.dao.mysql.MysqlDaoFactory;

public class DaoFactoryProvider {
    private static final DaoFactoryProvider INSTANCE = new DaoFactoryProvider();

    private DaoFactoryProvider() {}

    public static DaoFactoryProvider getInstance() {
        return INSTANCE;
    }

    public DaoFactory getFactory(String dbmsName) {
        switch (dbmsName) {
            case "MYSQL":
                return MysqlDaoFactory.getInstance();
            default:
                throw new IllegalArgumentException("Illegal DBMS name");
        }
    }
}
