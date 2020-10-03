package moneytracker.transaction;

import java.util.ArrayList;

public class TransactionList {
    private ArrayList<Transaction> transactions = new ArrayList<>();

    /**
     * Initializes an empty <code>TransactionList<Transaction></code> object.
     */
    public TransactionList() {
    }

    /**
     * Initializes a <code>TransactionList<Task></code> object.
     *
     * @param transactions List of <code>Transaction</> objects.
     */
    public TransactionList(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

}
