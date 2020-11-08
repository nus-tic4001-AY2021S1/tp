package moneytracker.helper;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Income;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;

public class Helper {
    /**
     * Gets the type of a <code>Transaction</code> object.
     *
     * @param transaction <code>Transaction</code> object.
     * @return Type of a <code>Transaction</code> object.
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

    /**
     * Gets a <code>Transaction</code> based on the index listed by the list command.
     *
     * @param transactions A <code>TransactionList</code> object.
     * @return <code>Transaction</code> object.
     * @throws MoneyTrackerException If the index of the transaction is invalid.
     */
    public static Transaction getTransactionFromList(TransactionList transactions, int index)
            throws MoneyTrackerException {
        Transaction transaction;
        try {
            transaction = transactions.getTransaction(transactions.getSearchResultIndex(index));
        } catch (IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("The index is invalid.");
        }
        return transaction;
    }
}