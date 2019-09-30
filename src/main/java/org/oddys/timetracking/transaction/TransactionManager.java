package org.oddys.timetracking.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.connection.ConnectionWrapper;

import java.sql.SQLException;

public class TransactionManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final TransactionManager INSTANCE = new TransactionManager();
    private ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();

    private TransactionManager() {}

    public static TransactionManager getInstance() {
        return INSTANCE;
    }

    public void beginTransaction() {
        connectionWrapper.setTransaction(true);
        try {
            connectionWrapper.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Failed to begin a transaction", e);
            throw new TransactionException(e);
        }
    }

    public void commit() {
        try {
            connectionWrapper.getConnection().commit();
        } catch (SQLException e) {
            LOGGER.error("Failed to commit a transaction", e);
            throw new TransactionException(e);
        }
    }

    public void rollback() {
        try {
            connectionWrapper.getConnection().rollback();
        } catch (SQLException e) {
            LOGGER.error("Failed to rollback a transaction", e);
            throw new TransactionException(e);
        }
    }

    public void endTransaction() {
        connectionWrapper.setTransaction(false);
        try {
            connectionWrapper.getConnection().setAutoCommit(true);
        } catch (SQLException e) {
            LOGGER.error("Transaction manager failed to set autocommit to true", e);
            throw new TransactionException(e);
        } finally {
            ConnectionWrapper.getInstance().close();
        }
    }
}
