package moneytracker.command;

import moneytracker.summary.Budget;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Represents a command that a user wants to execute. A <code>Command</code> object contains
 * methods needed for executing the user's command . This class defines the common behaviour that
 * can be inherited by subclasses with specific implementation of a Command.
 */
public abstract class Command {
    /**
     * Executes the user's command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     * @param categories List of <code>Category</code> objects.
     * @param budget <code>Budget</code> object.
     * @throws MoneyTrackerException If there is a processing error.
     */
    public abstract void execute(TransactionList transactions, Ui ui, Storage storage,
                        CategoryList categories, Budget budget) throws MoneyTrackerException;

    /**
     * Returns false to prevent Money Tracker from exiting after the command.
     * Subclasses should override this method to return true if Duke
     * needs to exit after the command.
     */
    public boolean isExit() {
        return false;
    }
}
