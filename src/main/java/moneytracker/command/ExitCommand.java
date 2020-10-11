package moneytracker.command;

import moneytracker.storage.Storage;
import moneytracker.transaction.ExpenseCategoryList;
import moneytracker.transaction.IncomeCategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Contains the methods for user to exit Money Tracker.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     * @param incomeCategories List of income categories.
     * @param expenseCategories List of expense categories.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        IncomeCategoryList incomeCategories,
                        ExpenseCategoryList expenseCategories) {
        ui.printGoodbye();
    }

    /**
     * Returns true so that Money Tracker will exist after this command is executed.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
