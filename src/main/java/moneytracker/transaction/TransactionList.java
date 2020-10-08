package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

import java.util.ArrayList;

public class TransactionList {
    private ArrayList<Transaction> transactions = new ArrayList<>();
    private final ArrayList<Integer> searchResultIndexes = new ArrayList<>();
    private boolean isInitialized = false;

    /**
     * Initializes an empty <code>TransactionList</code> object.
     */
    public TransactionList() {
    }

    /**
     * Initializes a <code>TransactionList</code> object.
     *
     * @param transactions List of <code>Transaction</code> objects.
     */
    public TransactionList(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    /**
     * Get <code>Transaction</code> object from the <code>TransactionList</code>.
     *
     * @param index Index of Transaction in the <code>TransactionList</code>.
     * @return <code>Transaction</code> object.
     */
    public Transaction getTransaction(int index) {
        return transactions.get(index);
    }

    /**
     * Add a transaction to the <code>TransactionList</code> object.
     *
     * @param t <code>Transaction</code> object.
     */
    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    /**
     * Remove a <code>Transaction</code> object from the <code>TransactionList</code>.
     *
     * @param index Index of a <code>Transaction</code> in <code>TransactionList</code>.
     * @throws MoneyTrackerException If index is invalid.
     */
    public void removeTransaction(int index) throws MoneyTrackerException {
        try {
            transactions.remove(transactions.get(index));
        } catch (IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("Invalid index");
        }
    }

    public int getSize() {
        return transactions.size();
    }
    public void setIsInitialized(boolean isInitialized) {
        this.isInitialized = isInitialized;
    }
    public boolean getIsInitialized() {
        return isInitialized;
    }
    public void clearSearchResultIndexes() {
        searchResultIndexes.clear();
    }

    public void addSearchResultIndex(int searchResultIndex) {
        searchResultIndexes.add(searchResultIndex);
    }

}