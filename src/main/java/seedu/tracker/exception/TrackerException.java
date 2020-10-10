package seedu.tracker.exception;

/**
 * Inherits the Exception class.
 * Overrides the constructor that takes a String parameter with custom error
 * information when you create a TaskManagerException object.
 * Throw a new TaskManagerException object when you detect
 * some necessary information is missing in the seedu.tracker.command.
 * Catch that seedu.tracker.exception somewhere
 * else and print the message inside the seedu.tracker.exception object.
 */
public class TrackerException extends Exception {
    public TrackerException(String input) {
        super(input);
    }
}