package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

/**
 * Represents an expense that a user has incurred. An <code>Expense</code>
 * object has an amount, a description, a date and a category.
 */
public class Expense extends Transaction {
    protected final String expenseCategory;

    /**
     * Initializes an <code>Expense</code> object.
     *
     * @param amount Amount of money of an expense.
     * @param description Description of an expense.
     * @param date Date of an expense.
     */
    public Expense(double amount, String description,String date,
                   String expenseCategory) throws MoneyTrackerException {
        super(amount, description, date);
        this.expenseCategory = expenseCategory;
    }

    /**
     * Initializes an <code>Expense</code> object.
     *
     * @param amount Amount of money of an expense.
     * @param description Description of an expense.
     */
    public Expense(double amount, String description, String expenseCategory) {
        super(amount, description);
        this.expenseCategory = expenseCategory;
    }

    /**
     * Gets the category of an <code>Expense</code> object.
     *
     * @return Category of an <code>Expense</code> object.
     */
    public String getExpenseCategory() {
        return expenseCategory;
    }

    /**
     * Gets the String representation of an <code>Expense</code>.
     */
    @Override
    public String toString() {
        return "[E] " + expenseCategory.toUpperCase() + " " + super.toString();
    }
}
