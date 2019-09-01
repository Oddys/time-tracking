package org.oddys.timetracking.connection;

import org.oddys.timetracking.util.ResourceInitializationException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    private final DataSource DATA_SOURCE;

    private ConnectionPool(DataSource ds) {
        DATA_SOURCE = ds;
    }

    private static class InitializationHelper {
        private static final ConnectionPool CONNECTION_POOL;

        static {
            DataSource ds = null;
            try {
                Context initContext = new InitialContext();
                Context envContext = (Context) initContext.lookup("java:comp/env");
                ds = (DataSource) envContext.lookup("jdbc/timetracking");
            } catch (NamingException e) {
                throw new ResourceInitializationException(e);
            }
            CONNECTION_POOL = new ConnectionPool(ds);
        }
    }

    public static ConnectionPool getInstance() {
        return InitializationHelper.CONNECTION_POOL;
    }

    public Connection getConnection() throws SQLException {
        return DATA_SOURCE.getConnection();
    }
}
