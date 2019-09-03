package org.oddys.timetracking.transaction;

import org.oddys.timetracking.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionWrapper {
    private static final ConnectionWrapper INSTANCE = new ConnectionWrapper();
    private final ThreadLocal<Connection> CONNECTION;

    private ConnectionWrapper() {
        CONNECTION = new ThreadLocal<>();
        try {
            CONNECTION.set(ConnectionPool.getInstance().getConnection());
        } catch (SQLException e) {
            e.printStackTrace(); // FIXME Add adequate exception handling
        }
    }

    public static ConnectionWrapper getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return CONNECTION.get();
    }
}
