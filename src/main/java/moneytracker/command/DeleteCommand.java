package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Income;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Contains the methods for user to delete a transaction.
 */
public class DeleteCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>DeleteCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the delete transaction command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     * @param categories List of categories.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        CategoryList categories) throws MoneyTrackerException {
        if (!(transactions.getIsInitialized())) {
            throw new MoneyTrackerException("Please run the list command first.");
        }
        transactions.setIsInitialized(false);
        int transactionIndex = Parser.getTransactionIndex(fullCommand);
        Transaction transactionToDelete;
        try {
            transactionToDelete =
                    transactions.getTransaction(transactions.getSearchResultIndex(transactionIndex));
        } catch (IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("The transaction index is invalid.");
        }
        String transactionDescription = transactionToDelete.toString();
        String transactionType = getTransactionType(transactionToDelete);
        transactions.removeTransaction(transactions.getSearchResultIndex(transactionIndex));
        storage.saveTransactions(transactions);
        ui.printRemovedTransaction(transactions.getSize(), transactionDescription, transactionType);
    }

    /**
     * Gets transaction type from transaction input string.
     *
     * @param transaction <code>Transaction</code> object.
     * @return type of a <code>Transaction</code> object.
     * @throws MoneyTrackerException when this exceptional condition happens.
     */
    private String getTransactionType(Transaction transaction) throws MoneyTrackerException {
        String transactionType;
        if (transaction instanceof Income) {
            transactionType = "Income";
        } else if (transaction instanceof Expense) {
            transactionType = "Expense";
        } else {
            throw new MoneyTrackerException("The transaction type is invalid");
        }
        return transactionType;
    }
}
