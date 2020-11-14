package moneytracker.command;

import moneytracker.summary.Budget;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Contains the methods for user to list all categories.
 */
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
     * @param categories List of <code>Category</code> objects.
     * @param budget <code>Budget</code> object.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        CategoryList categories, Budget budget) {
        categories.setIsInitialized(true);
        categories.clearSearchResultIndexes();
        for (int i = 0; i < categories.getSize(); i++) {
            categories.addSearchResultIndex(i);
        }

        int len = this.fullCommand.split(" ").length;
        String[] lineArr = this.fullCommand.split(" ", 4);
        assert lineArr.length >= 0 : "There should be at least 1 element";

        if (len == 1) {
            ui.printListCategory(categories);
        } else if ((len == 2) & (lineArr[1].toLowerCase().equals("/te"))) {
            ui.printListCategoryExpenseOnly(categories);

        } else if ((len == 2) & (lineArr[1].toLowerCase().equals("/ti"))) {
            ui.printListCategoryIncomeOnly(categories);
        } else {
            ui.printError("Please enter valid command.");
        }
    }
}