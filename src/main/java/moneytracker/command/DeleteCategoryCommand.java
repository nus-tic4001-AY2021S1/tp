package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.Category;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Contains the methods for user to delete a category.
 */
public class DeleteCategoryCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>DeleteCategoryCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public DeleteCategoryCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the delete category command.
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
        categories.setIsInitialized(false);
        int categoryIndex = Parser.getIndex(fullCommand);
        Category categoryToDelete;
        try {
            categoryToDelete =
                    categories.getCategory(categories.getSearchResultIndex(categoryIndex));
        } catch (IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("The index is invalid.");
        }
        String description = categoryToDelete.toString();
        String type = categoryToDelete.getType().toLowerCase() + " category";
        categories.removeCategory(categories.getSearchResultIndex(categoryIndex));
        storage.saveCategories(categories);
        ui.printRemoveCategory(categories.getSize(), description, type);
    }
}
