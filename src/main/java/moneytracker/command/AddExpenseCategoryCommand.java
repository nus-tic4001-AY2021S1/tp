package moneytracker.command;

import moneytracker.summary.Budget;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Contains the methods for user to add an expense category.
 */
public class AddExpenseCategoryCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>AddIncomeCategoryCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public AddExpenseCategoryCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the add expense category command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     * @param categories List of <code>Category</code> objects.
     * @param budget <code>Budget</code> object.
     * @throws MoneyTrackerException If there is a processing error.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        CategoryList categories, Budget budget) throws MoneyTrackerException {
        categories.addCategory(Parser.createExpenseCategory(fullCommand));
        storage.saveCategories(categories);
        ui.printAddCategory(categories);
    }
}
