package org.oddys.timetracking.dao;

import org.oddys.timetracking.dao.mysql.MysqlDaoFactory;
import org.oddys.timetracking.util.ConfigProvider;
import org.oddys.timetracking.util.ResourceInitializationException;

public class DaoFactoryProvider {
    private static volatile DaoFactoryProvider INSTANCE;
    private final DaoFactory FACTORY = MysqlDaoFactory.getInstance();

    private DaoFactoryProvider() {}

//    private DaoFactoryProvider(DaoFactory factory) {
//        FACTORY = factory;
//    }

//    public DaoFactoryProvider getInstance() {
//        if (INSTANCE == null) {
//            synchronized (DaoFactoryProvider.class) {
//                if (INSTANCE == null) {
//                    String dbName = ConfigProvider.getInstance().getProperty("db.dbms");
//                    if (dbName == null) {
//                        throw new ResourceInitializationException("Cannot resolve DBMS name");
//                    }
//                    switch (dbName) {
//                        case "MYSQL":
//                            INSTANCE = new DaoFactoryProvider(MysqlDaoFactory.getInstance());
//                        default:
//                            throw new ResourceInitializationException("Cannot resolve DBMS name");
//                    }
//                }
//            }
//        }
//        return INSTANCE;
//    }

    public DaoFactory getFactory() {
        return FACTORY;
    }
}
