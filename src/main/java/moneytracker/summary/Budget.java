package moneytracker.summary;

/**
 * Represents the monthly budget. A <code>Budget</code> object has a double variable
 * that indicates the amount of this budget.
 */
public class Budget {
    private double amount;

    /**
     * Initializes a <code>Budget</code> object.
     */
    public Budget() {
        amount = 0;
    }

    /**
     * Initializes a <code>Budget</code> object.
     *
     * @param amount Amount in a <code>Budget</code>.
     */
    public Budget(Double amount) {
        this.amount = amount;
    }

    public Budget(String s) {
    }

    /**
     * Gets the amount of a <code>Budget</code>.
     *
     * @return Amount of a <code>Budget</code>
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the amount of a <code>Budget</code>.
     */
    public void setBudget(Double amount) {
        this.amount = amount;
    }
}