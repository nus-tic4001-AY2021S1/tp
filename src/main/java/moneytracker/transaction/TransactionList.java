package moneytracker.transaction;

import java.util.ArrayList;

public class TransactionList {
    private ArrayList<Transaction> transactions = new ArrayList<>();

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

}
