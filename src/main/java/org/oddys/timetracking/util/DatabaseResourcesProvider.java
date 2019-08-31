package org.oddys.timetracking.util;

import org.oddys.timetracking.dao.DaoFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseResourcesProvider {
    private static DataSource dataSource;
    private static DaoFactoryProvider daoFactoryProvider;

    public static void initialize() {
        if (daoFactoryProvider != null || dataSource != null) {
            throw new RuntimeException("Attempt to reinitialize"); // TODO Change to custom exception
        }
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:comp/env");
            dataSource = (DataSource) envContext.lookup(ConfigProvider.getProperty("db.resource"));
            daoFactoryProvider = DaoFactoryProvider.valueOf(ConfigProvider.getProperty("db.rdbms"));
        } catch (IllegalArgumentException | NullPointerException | NamingException e) {
            e.printStackTrace(); // TODO Handle
        }
    }

    Connection getConnection() throws SQLException {return dataSource.getConnection();}
    DaoFactory getDaoFactory() {return daoFactoryProvider.getFactory();}
}
