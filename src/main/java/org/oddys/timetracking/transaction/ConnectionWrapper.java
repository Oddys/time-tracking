package org.oddys.timetracking.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.connection.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionWrapper implements AutoCloseable {
    private static final Logger log = LogManager.getLogger();
    private static ConnectionWrapper INSTANCE = new ConnectionWrapper();
    private ThreadLocal<Connection> connectionThreadLocal = ThreadLocal
            .withInitial(() -> ConnectionPool.getInstance().getConnection());
    private ThreadLocal<Boolean> isTransactionThreadLocal = ThreadLocal.withInitial(() -> false);

    private ConnectionWrapper() { ;
    }

    public static ConnectionWrapper getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return connectionThreadLocal.get();
    }

    public void setTransaction(boolean isTransaction) {
        isTransactionThreadLocal.set(isTransaction);
    }

    @Override
    public void close() {
        if (!isTransactionThreadLocal.get()) {
            try {
                connectionThreadLocal.get().close();
            } catch (SQLException e) {
                e.printStackTrace(); // FIXME
            }
            connectionThreadLocal.remove();
        }
    }
}
