package moneytracker.command;

import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

public class ReportCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>ReportCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public ReportCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the display report command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     * @param categories List of categories.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        CategoryList categories) {

    }
}
