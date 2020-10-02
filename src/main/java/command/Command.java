package command;

import exception.MoneyTrackerException;
import storage.Storage;
import transaction.TransactionList;
import ui.Ui;

/**
 * Represents a command that a user wants to execute. A <code>Command</code> object contains
 * methods needed for executing the user's command . This class defines the common behaviour that
 * can be inherited by subclasses with specific implementation of a Command.
 */
public abstract class Command {
    /**
     * Executes the user's command.
     *
     * @param transactions List of <code>Transaction</> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     */
    public abstract void execute(TransactionList transactions, Ui ui, Storage storage) throws MoneyTrackerException;

    /**
     * Returns false to prevent Duke from exiting after the command.
     * Subclasses should override this method to return true if Duke
     * needs to exit after the command.
     */
    public boolean isExit() {
        return false;
    }
}
