package org.oddys.timetracking.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.connection.ConnectionWrapper;

import java.sql.SQLException;

public class TransactionManager {
    private static final Logger log = LogManager.getLogger();
    private static final TransactionManager INSTANCE = new TransactionManager();
    private ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();

    private TransactionManager() {}

    public static TransactionManager getInstance() {
        return INSTANCE;
    }

    public void beginTransaction() throws TransactionException {
        connectionWrapper.setTransaction(true);
        try {
            connectionWrapper.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            throw new TransactionException("Failed to begin a transaction", e);
        }
    }

    public void commit() throws TransactionException {
        try {
            connectionWrapper.getConnection().commit();
        } catch (SQLException e) {
            throw new TransactionException("Failed to commit a transaction", e);
        }
    }

    public void rollback() throws TransactionException {
        try {
            connectionWrapper.getConnection().rollback();
        } catch (SQLException e) {
            throw new TransactionException("Failed to rollback a transaction", e);
        }
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
