package org.oddys.timetracking.transaction;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final TransactionManager INSTANCE = new TransactionManager();
    private ConnectionWrapper connectionWrapper = ConnectionWrapper.getInstance();

    private TransactionManager() {}

    public static TransactionManager getInstance() {
        return INSTANCE;
    }

    public void beginTransaction() {
        connectionWrapper.setTransaction(true);
        Connection connection = connectionWrapper.getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();  // FIXME
        }
    }

    public void commit() {
        Connection connection = connectionWrapper.getConnection();
        try {
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();  // FIXME
        }
    }

    public void rollback() {
        Connection connection = connectionWrapper.getConnection();
        try {
            connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();  // FIXME
        }
    }

    public void endTransaction() {
        connectionWrapper.setTransaction(false);
        Connection connection = connectionWrapper.getConnection();
        try {
            connection.setAutoCommit(true);
            ConnectionWrapper.getInstance().close();
        } catch (SQLException e) {
            e.printStackTrace();  // FIXME
        }
    }
}
