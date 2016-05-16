package by.training.exception;

public class JPanelEditorException extends Exception {

    private static final long  serialVersionUID   = -4895408065371565930L;

    public static final String CREATE_PANEL_ERROR = "Failed to create panel";
    public static final String RUN_PANEL_ERROR    = "Failed to run panel";
    public static final String SET_PANEL_ERROR    = "Failed to set panel as dependency";

    public JPanelEditorException(final String message) {
        super(message);
    }

}
