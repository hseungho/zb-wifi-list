package global.exception;

public class ResourceNotFoundException extends Exception {

    private final int status;
    private final String message;

    public ResourceNotFoundException() {
        this.status = 404;
        this.message = "NOT_FOUND";
    }
}
