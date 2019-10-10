package org.oddys.timetracking.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.connection.ConnectionWrapper;

import java.sql.SQLException;

/**
 * A class for managing JDBC transactions.
 *
 * All methods can throw a TransactionException if a database error occurs
 * during their execution
 */
public class TransactionManager {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final TransactionManager INSTANCE = new TransactionManager();
    private ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();

    private TransactionManager() {}

    /**
     * Provides an instance of this class.
     *
     * @return a TransactionManager instance
     */
    public static TransactionManager getInstance() {
        return INSTANCE;
    }

    /**
     * Sets the beginning of a transaction.
     *
     * @throws TransactionException
     */
    public void beginTransaction() {
        connectionWrapper.setTransaction(true);
        try {
            connectionWrapper.getConnection().setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("Failed to begin a transaction", e);
            throw new TransactionException(e);
        }
    }

    /**
     * Commits a current transaction.
     *
     * @throws TransactionException
     */
    public void commit() {
        try {
            connectionWrapper.getConnection().commit();
        } catch (SQLException e) {
            LOGGER.error("Failed to commit a transaction", e);
            throw new TransactionException(e);
        }
    }

    /**
     * Rolls back a current transaction.
     *
     * @throws TransactionException
     */
    public void rollback() {
        try {
            connectionWrapper.getConnection().rollback();
        } catch (SQLException e) {
            LOGGER.error("Failed to rollback a transaction", e);
            throw new TransactionException(e);
        }
    }

    /**
     * Performs a cleanup after finishing a transaction.
     *
     * @throws TransactionException
     */
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
