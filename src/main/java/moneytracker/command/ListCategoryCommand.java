package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

public class ListCategoryCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>ListCategoryCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public ListCategoryCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the list categories command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     * @param categories List of categories.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        CategoryList categories) {

        int len = this.fullCommand.split(" ").length;
        String[] lineArr = this.fullCommand.split(" ", 4);

        if (len == 1) {
            ui.printListCategory(categories);
        } else if ((len == 2) & (lineArr[1].toLowerCase().equals("/te"))) {
            ui.printListCategoryExpenseOnly(categories);

        } else if ((len == 2) & (lineArr[1].toLowerCase().equals("/ti"))) {
            ui.printListCategoryIncomeOnly(categories);
        }


    }
}
