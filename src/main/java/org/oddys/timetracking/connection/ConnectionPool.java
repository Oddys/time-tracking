package org.oddys.timetracking.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.util.ResourceInitializationException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * A connection pool implementation.
 */
public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final ConnectionPool INSTANCE = new ConnectionPool();
    private static final String ENVIRONMENT_NAME = "java:comp/env";
    private static final String SOURCE_NAME = "jdbc/timetracking";
    private final DataSource DATA_SOURCE;

    private ConnectionPool() {
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(ENVIRONMENT_NAME);
            DATA_SOURCE = (DataSource) envContext.lookup(SOURCE_NAME);
        } catch (NamingException e) {
            LOGGER.error("Failed to obtain a data source for ConnectionPool", e);
            throw new ResourceInitializationException(e);
        }
    }

    /**
     * Returns an instance of a ConnectionPool.
     *
     * @return an instance of a ConnectionPool
     */
    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    /**
     * Attempts to provide a connection from this ConnectionPool object.
     *
     * @return a connection to the data source
     *
     * @throws ConnectionPoolException if attempt to obtain a connection failed
     */
    public Connection getConnection() {
        try {
            return DATA_SOURCE.getConnection();
        } catch (SQLException e) {
            throw new ConnectionPoolException(e);
        }
    }
}
