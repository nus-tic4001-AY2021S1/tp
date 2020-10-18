package moneytracker.transaction;

import moneytracker.exception.MoneyTrackerException;

import java.util.ArrayList;

/**
 * Manages the in-memory transaction list. It contains an ArrayList that stores
 * individual <code>Transaction</code> objects and methods to perform operations
 * such as adding and deleting transactions.
 */
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
     * Gets a <code>Transaction</code> object from the <code>TransactionList</code>.
     *
     * @param index Index of <code>Transaction</code> in the <code>TransactionList</code>.
     * @return <code>Transaction</code> object.
     */
    public Transaction getTransaction(int index) {
        return transactions.get(index);
    }

    /**
     * Adds a transaction to the <code>TransactionList</code>.
     *
     * @param transaction <code>Transaction</code>.
     */
    public void addTransaction(Transaction transaction, CategoryList categories) throws MoneyTrackerException {
        String category;
        String type;
        if (transaction instanceof Income) {
            category = ((Income) transaction).getIncomeCategory();
            type = "INCOME";
            categories.checkIfCategoryExists(category, type);
        } else {
            category = ((Expense) transaction).getExpenseCategory();
            type = "EXPENSE";
        }
        if (categories.checkIfCategoryExists(category, type)) {
            transactions.add(transaction);
        } else {
            throw new MoneyTrackerException("The category \"" + category + "\" does not exist.");
        }
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

    /**
     * Gets the size of a <code>TransactionList</code>.
     * @return Size of a <code>TransactionList</code>.
     */
    public int getSize() {
        return transactions.size();
    }

    /**
     * Gets the status of whether the list command has been used.
     */
    public boolean getIsInitialized() {
        return isInitialized;
    }

    /**
     * Sets the status that the list command has just been used.
     */
    public void setIsInitialized(boolean isInitialized) {
        this.isInitialized = isInitialized;
    }

    /**
     * Adds index for a particular transaction listed by the list command.
     * @param searchResultIndex Index of category listed by the list command.
     */
    public void addSearchResultIndex(int searchResultIndex) {
        searchResultIndexes.add(searchResultIndex);
    }

    /**
     * Clears the indexes from the list command.
     */
    public void clearSearchResultIndexes() {
        searchResultIndexes.clear();
    }

    /**
     * Gets the indexes from the list command.
     */
    public ArrayList<Integer> getSearchResultIndexes() {
        return searchResultIndexes;
    }

    /**
     * Gets the index for a particular transaction listed by the list command.
     */
    public int getSearchResultIndex(int resultIndex) {
        return searchResultIndexes.get(resultIndex);
    }

    /**
     * Clears the <code>TransactionList</code>.
     */

    public void clearTransactions() {
        transactions.clear();
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    /**
     * Updates the category name of <code>Transaction</code> objects in the <code>TransactionList</code>.
     *
     * @param currentName Current category name of <code>Transaction</code> objects.
     * @param newName New category name of <code>Transaction</code> objects.
     */

    public void updateTransactionsCategory(String currentName, String newName) {
        for (int i = 0; i < transactions.size(); i++) {
            Transaction transaction = transactions.get(i);
            if (transaction instanceof Income && ((Income) transaction).getIncomeCategory().equals(currentName)) {
                ((Income) transaction).setIncomeCategory(newName);
            }
            if (transaction instanceof Expense && ((Expense) transaction).getExpenseCategory().equals(currentName)) {
                ((Expense) transaction).setExpenseCategory(newName);
            }
        }
    }
}