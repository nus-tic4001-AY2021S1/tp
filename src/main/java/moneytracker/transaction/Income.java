package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

/**
 * Represents an income that a user has earned. An <code>Income</code>
 * object has an amount, a description, a date and an income category.
 */
public class Income extends Transaction {
    private final String incomeCategory;

    /**
     * Initializes an <code>Income</code> object.
     *
     * @param amount Amount of money for an income.
     * @param description Description of an income.
     * @param date Date of an income.
     */
    public Income(double amount, String description, String date, String incomeCategory) throws MoneyTrackerException {
        super(amount, description, date);
        this.incomeCategory = incomeCategory;
    }

    /**
     * Initializes an <code>Income</code> object.
     *
     * @param amount Amount of money for an income.
     * @param description Description of an income.
     */
    public Income(double amount, String description, String incomeCategory) {
        super(amount, description);
        this.incomeCategory = incomeCategory;
    }

    @Override
    public String toString() {
        return "[I] " + incomeCategory.toUpperCase() + " " + super.toString();
    }
}
