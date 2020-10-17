package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.transaction.Category;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.Income;
import moneytracker.transaction.Expense;
import moneytracker.ui.Ui;

/**
 * Contains the methods for user to add edit a category.
 */
public class EditCategoryCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>EditCategoryCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public EditCategoryCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the edit category command.
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
        if (!(categories.getIsInitialized())) {
            throw new MoneyTrackerException("Please run the listcat command first.");
        }
        String[] editParameters = Parser.getEditParameters(fullCommand);
        int categoryIndex;
        try {
            categoryIndex = Integer.parseInt(editParameters[0]) - 1;
        } catch (NumberFormatException e) {
            throw new MoneyTrackerException("The index is invalid.");
        }
        Category categoryToEdit;
        try {
            categoryToEdit =
                    categories.getCategory(categories.getSearchResultIndex(categoryIndex));
        } catch (IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("The index is invalid.");
        }
        String newName;
        try {
            newName = editParameters[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MoneyTrackerException("The new category name is missing.");
        }
        String currentName = categoryToEdit.getName();
        String currentDescription = categoryToEdit.toString();
        categoryToEdit.setName(newName.toUpperCase());
        transactions.updateTransactionsCategory(currentName, newName);
        String type = categoryToEdit.getType().toLowerCase() + " category";
        String newDescription = categoryToEdit.toString();
        ui.printEditCategory(currentDescription, newDescription, type);
        storage.saveCategories(categories);
        categories.setIsInitialized(false);
    }
}