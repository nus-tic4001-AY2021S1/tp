package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Contains the methods for user to clear all data.
 */
public class ClearCommand extends Command {

    /**
     * Executes the clear all data command.
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
        ui.printClearConfirmation();
        while (true) {
            String confirmation = ui.readUserCommand().toUpperCase();
            if (confirmation.equals("Y")) {
                categories.clearCategories();
                transactions.clearTransactions();
                storage.clearAllData();
                ui.printClearAllData(confirmation);
                break;
            } else if (confirmation.equals("N")) {
                ui.printClearAllData(confirmation);
                break;
            } else {
                ui.printClearAllData(confirmation);
            }
        }
    }
}