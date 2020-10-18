package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

import java.util.HashMap;

/**
 * Contains the methods for user to edit a transaction.
 */
public class EditCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>EditCategoryCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public EditCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the edit transaction command.
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
        HashMap<String, String> editParameters = Parser.getEditTransactionParams(fullCommand);
        String indexString = editParameters.get("index");
        int index;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new MoneyTrackerException("The index is invalid.");
        }
        
        storage.saveTransactions(transactions);
        transactions.setIsInitialized(false);

    }
}
