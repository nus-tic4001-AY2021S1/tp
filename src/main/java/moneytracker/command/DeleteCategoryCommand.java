package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

public class DeleteCategoryCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>DeleteCategoryCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public DeleteCategoryCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the delete category command.
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

    }
}
