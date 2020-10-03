package moneytracker.exception;

/**
 * Overrides the Exception class's constructor to implement
 * custom error messages for user-defined exceptions.
 */
public class MoneyTrackerException extends Exception {
    public MoneyTrackerException(String errorMessage) {
        super(errorMessage);
    }
}