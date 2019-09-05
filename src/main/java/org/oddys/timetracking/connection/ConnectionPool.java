package org.oddys.timetracking.connection;

import org.oddys.timetracking.util.ResourceInitializationException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private static final String ENVIRONMENT_NAME = "java:comp/env";
    private static final String SOURCE_NAME = "jdbc/timetracking";
    private final DataSource DATA_SOURCE;

    private ConnectionPool() {
        DataSource ds = null;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(ENVIRONMENT_NAME);
            ds = (DataSource) envContext.lookup(SOURCE_NAME);
        } catch (NamingException e) {
            throw new ResourceInitializationException("Failed to obtain a data source for ConnectionPool", e);
        }
        DATA_SOURCE = ds;
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        try {
            return DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);  // FIXME
        }
    }
}
