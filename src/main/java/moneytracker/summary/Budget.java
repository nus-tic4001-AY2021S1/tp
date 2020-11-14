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

    /**
     * Calculate the percentage exceed of <code>expense</code>.
     */

    public String calPercentage(double expense) {
        double percentage = expense / amount;
        if (percentage >= 0 & percentage < 0.5) {
            return "0";
        } else if (percentage >= 0.5 & percentage < 0.75) {
            return "50%";
        } else if (percentage >= 0.75 & percentage < 1) {
            return "75%";
        } else {
            return "100%";
        }
    }
}