package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

import static moneytracker.command.Utilities.getTransactionType;
import static moneytracker.command.Utilities.getTransaction;

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
     * @throws MoneyTrackerException If there is a processing error.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        CategoryList categories) throws MoneyTrackerException {
        if (!(transactions.getIsInitialized())) {
            throw new MoneyTrackerException("Please run the list command first.");
        }
        int index = Parser.getDeleteIndex(fullCommand);
        Transaction transactionToDelete = getTransaction(transactions, index);
        String description = transactionToDelete.toString();
        String type = getTransactionType(transactionToDelete);
        transactions.removeTransaction(transactions.getSearchResultIndex(index));
        storage.saveTransactions(transactions);
        ui.printRemoveTransaction(transactions.getSize(), description, type);
        transactions.setIsInitialized(false);
    }
}