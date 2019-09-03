package org.oddys.timetracking.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.connection.ConnectionPool;
import org.oddys.timetracking.util.ResourceInitializationException;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionWrapper {
    private static final Logger log = LogManager.getLogger();
    private static final ConnectionWrapper INSTANCE = new ConnectionWrapper();
    private static final ThreadLocal<Connection> CONNECTION = new ThreadLocal<>() {
        @Override
        protected Connection initialValue() {
            Connection connection = null;
            try {
                connection = ConnectionPool.getInstance().getConnection();
            } catch (SQLException e) {
                throw new ResourceInitializationException("Failed to obtain a connection for ConnectionWrapper", e);
            }
            return connection;
        }
    };

//    private ConnectionWrapper() {
//        try {
//            Connection connection = ConnectionPool.getInstance().getConnection();
//            CONNECTION.set(connection);
//        } catch (SQLException e) {
//            throw new ResourceInitializationException("Failed to obtain a connection for ConnectionWrapper", e);
//        }
//    }

    public static ConnectionWrapper getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        if (CONNECTION.get() == null) {
            try {
                CONNECTION.set(ConnectionPool.getInstance().getConnection());
            } catch (SQLException e) {
                throw new ResourceInitializationException("Failed to obtain a connection for ConnectionWrapper", e);
            }
        }
        return CONNECTION.get();
    }
}
