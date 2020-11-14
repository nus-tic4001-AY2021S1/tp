package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

/**
 * Represents an income that a user has earned. An <code>Income</code>
 * object has an amount, a description, a date and a category.
 */
public class Income extends Transaction {
    private String incomeCategory;

    /**
     * Initializes an <code>Income</code> object.
     *
     * @param amount Amount of money of an <code>Income</code>.
     * @param description Description of an <code>Income</code>.
     * @param date Date of an <code>Income</code>.
     */
    public Income(double amount, String description, String date, String incomeCategory) throws MoneyTrackerException {
        super(amount, description, date);
        this.incomeCategory = incomeCategory;
    }

    /**
     * Initializes an <code>Income</code> object.
     *
     * @param amount Amount of money of an <code>Income</code>.
     * @param description Description of an <code>Income</code>.
     */
    public Income(double amount, String description, String incomeCategory) {
        super(amount, description);
        this.incomeCategory = incomeCategory;
    }

    /**
     * Gets the category of an <code>Income</code>.
     *
     * @return Category of an <code>Income</code>.
     */
    public String getIncomeCategory() {
        return incomeCategory;
    }

    /**
     * Sets the category of an <code>Income</code>.
     * @param incomeCategory Category of an <code>Income</code> .
     */
    public void setIncomeCategory(String incomeCategory) {
        this.incomeCategory = incomeCategory;
    }

    /**
     * Gets the String representation of an <code>Income</code>.
     */
    @Override
    public String toString() {
        return "[I] " + incomeCategory.toUpperCase() + " " + super.toString();
    }
}
