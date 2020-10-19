package moneytracker.command;

import moneytracker.budget.Budget;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Contains the method to show the URL of the user guide.
 */
public class HelpCommand extends Command {
    /**
     * Executes the help command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     * @param categories List of <code>Category</code> objects.
     * @param budget <code>Budget</code> object.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        CategoryList categories, Budget budget) {
        ui.printHelp();
    }
}
