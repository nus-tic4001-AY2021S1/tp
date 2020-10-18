package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Income;
import moneytracker.transaction.Transaction;

public class Utilities {
    /**
     * Gets the type of a <code>Transaction</code> object.
     *
     * @param transaction <code>Transaction</code> object.
     * @return type of a <code>Transaction</code> object.
     * @throws MoneyTrackerException If the type of a transaction is invalid.
     */
    public static String getTransactionType(Transaction transaction) throws MoneyTrackerException {
        String type;
        if (transaction instanceof Income) {
            type = "INCOME";
        } else if (transaction instanceof Expense) {
            type = "EXPENSE";
        } else {
            throw new MoneyTrackerException("The type is invalid");
        }
        return type;
    }
}
