package org.oddys.timetracking.transaction;

/**
 * Thrown to indicate an exception condition that occurs during a method call
 * on a proxy object.
 */
public class ProxyException extends RuntimeException {
    public ProxyException() {
        super();
    }

    /**
     * @see RuntimeException
     */
    public ProxyException(String message) {
        super(message);
    }

    /**
     * @see RuntimeException
     */
    public ProxyException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @see RuntimeException
     */
    public ProxyException(Throwable cause) {
        super(cause);
    }
}
