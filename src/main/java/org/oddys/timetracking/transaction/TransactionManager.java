package org.oddys.timetracking.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class TransactionManager {
    private static final Logger log = LogManager.getLogger();
    private static final TransactionManager INSTANCE = new TransactionManager();
    private ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();

    private TransactionManager() {}

    public static TransactionManager getInstance() {
        return INSTANCE;
    }

    public void beginTransaction() throws SQLException {
        connectionWrapper.setTransaction(true);
        connectionWrapper.getConnection().setAutoCommit(false);
    }

    public void commit() throws SQLException {
        connectionWrapper.getConnection().commit();
    }

    public void rollback() throws SQLException {
        connectionWrapper.getConnection().rollback();
    }

    public void endTransaction() {
        connectionWrapper.setTransaction(false);
        try {
            connectionWrapper.getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            log.error("Transaction manager failed to set autocommit to true", e);
        }
        ConnectionWrapper.getInstance().close();
    }
}
