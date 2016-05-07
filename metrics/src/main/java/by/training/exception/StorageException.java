package by.training.exception;

public class StorageException extends Exception {

    private static final long  serialVersionUID  = -3966314545915903556L;

    public static final String LOAD_BEFORE_ERROR = "Failed to load metrics before the date";
    public static final String LOAD_AFTER_ERROR  = "Failed to load metrics afret the date";

    public StorageException(final String message) {
        super(message);
    }

}
