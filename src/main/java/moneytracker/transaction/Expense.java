package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

/**
 * Represents an expense that a user has incurred. An <code>Expense</code>
 * object has an amount, a description, a date and an expense category.
 */
public class Expense extends Transaction {
    private final String expenseCategory;

    /**
     * Initializes an <code>Expense</code> object.
     *
     * @param amount Amount of money for an expense.
     * @param description Description of an expense.
     * @param date Date of an expense.
     */
    public Expense(double amount, String description, String date, String expenseCategory) throws MoneyTrackerException {
        super(amount, description, date);
        this.expenseCategory = expenseCategory;
    }

    /**
     * Initializes an <code>Expense</code> object.
     *
     * @param amount Amount of money for an expense.
     * @param description Description of an expense.
     */
    public Expense(double amount, String description, String expenseCategory) {
        super(amount, description);
        this.expenseCategory = expenseCategory;
    }
}
