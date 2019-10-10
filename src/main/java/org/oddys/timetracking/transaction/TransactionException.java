package org.oddys.timetracking.transaction;

/**
 * Thrown to indicate that an exceptional situation occurred
 * during performing a transaction.
 */
public class TransactionException extends RuntimeException {
    /**
     * @see RuntimeException
     */
    public TransactionException() {
        super();
    }

    /**
     * @see RuntimeException
     */
    public TransactionException(String message) {
        super(message);
    }

    /**
     * @see RuntimeException
     */
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @see RuntimeException
     */
    public TransactionException(Throwable cause) {
        super(cause);
    }
}
