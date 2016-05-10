package by.training.exception;

public enum HTTPException {

    HTTP_200("OK"), HTTP_404("Not Found"), HTTP_503("Service Unavailable");

    private String message;

    private HTTPException(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
