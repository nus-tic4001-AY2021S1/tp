package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.storage.Storage;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

public class UnknownCommand extends Command {
    /**
     * Executes the invalid input moneytracker.command.
     *
     * @param transactions List of <code>Transaction</> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoneyTrackerException {
        ui.printError("I'm sorry, I don't know what that means.");
    }
}