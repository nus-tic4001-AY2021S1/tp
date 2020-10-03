package moneytracker.command;

import moneytracker.storage.Storage;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Contains the methods for user to exit Money Tracker.
 */
public class ExitCommand extends Command {
    /**
     * Executes the exit program moneytracker.command.
     *
     * @param transactions List of <code>Transaction</> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) {
        ui.printGoodbye();
    }

    /**
     * Returns true so that Money Tracker will exist after this moneytracker.command is executed.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
