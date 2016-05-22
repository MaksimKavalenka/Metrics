package by.training.exception;

public class ConfigEditorException extends Exception {

    private static final long  serialVersionUID    = -4729835040083702859L;

    public static final String FILE_ACCESS_ERROR   = "File access error";
    public static final String CONFIG_ACCESS_ERROR = "Config access error";
    public static final String CONFIG_UPDATE_ERROR = "Config update error";
    public static final String JAXB_ERROR          = "JAXB error";

    public ConfigEditorException(final String message) {
        super(message);
    }

}
