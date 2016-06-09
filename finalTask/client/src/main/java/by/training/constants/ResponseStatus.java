package by.training.constants;

public enum ResponseStatus {

    OK("OK"), NOT_FOUND("Not Found"), SERVICE_UNAVAILABLE("Service Unavailable");

    private String message;

    private ResponseStatus(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
