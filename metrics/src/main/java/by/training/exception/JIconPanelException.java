package by.training.exception;

public class JIconPanelException extends Exception {

    private static final long  serialVersionUID      = 1957903674375858508L;

    public static final String STORAGE_INIT_ERROR    = "Failed to init storage";
    public static final String STORAGE_REFRESH_ERROR = "Failed to refresh storage";

    public JIconPanelException(final String message) {
        super(message);
    }

}
