package moneytracker.storage;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Transaction;

import java.util.ArrayList;

public class Storage {
    private final String filePath;

    /**
     * Initializes a <code>Storage</code> object.
     *
     * @param filePath Path of the text file used for storing app data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Transaction> loadTransactions(String filePath) throws MoneyTrackerException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        return transactions;
    }
}