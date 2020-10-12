package seedu.duke.exception;

public class ProcessingException extends Exception {
    private final String message;

    public ProcessingException(String message) {
        super(message);
        this.message = message;

    }
}