package org.oddys.timetracking.connection;

/**
 * Thrown to indicate that the attempt to obtain a connection from the pool failed.
 */
public class ConnectionPoolException extends RuntimeException {
    /**
     * Constructs a new ConnectionPoolException with null as its detail message.
     *
     * @see RuntimeException
     */
    public ConnectionPoolException() {
        super();
    }

    /**
     * Constructs a new ConnectionPoolException with the specified detail message.
     *
     * @param message the detail message
     *
     * @see RuntimeException
     */
    public ConnectionPoolException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConnectionPoolException with the specified detail message and cause.
     *
     * @param message the detail message
     * @param cause the cause
     *
     * @see RuntimeException
     */
    public ConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ConnectionPoolException with the specified cause and
     * a detail message of (cause==null ? null : cause.toString())
     * @param cause the cause
     *
     * @see RuntimeException
     */
    public ConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
