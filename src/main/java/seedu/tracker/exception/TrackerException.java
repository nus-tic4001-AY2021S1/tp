package seedu.tracker.exception;

/**
 * Inherits the Exception class. <p> Overrides the constructor that takes a String parameter with custom error
 * information when you create a TaskManagerException object.<p> Throw a new TaskManagerException object when you detect
 * some necessary information is missing in the seedu.tracker.command. <p> Catch that seedu.tracker.exception somewhere
 * else and print the message inside the seedu.tracker.exception object.
 */
public class TrackerException extends Exception {
    public TrackerException(String input) {
        super(input);
    }
}