package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.Income;
import moneytracker.transaction.Expense;
import moneytracker.ui.Ui;

import java.util.HashMap;

import static moneytracker.command.Utilities.getTransactionType;

/**
 * Contains the methods for user to edit a transaction.
 */
public class EditCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>EditCategoryCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public EditCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the edit transaction command.
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
        if (!(transactions.getIsInitialized())) {
            throw new MoneyTrackerException("Please run the list command first.");
        }
        HashMap<String, String> editParams = Parser.getEditTransactionParams(fullCommand);
        String indexString = editParams.get("index");
        int index;
        try {
            index = Integer.parseInt(indexString) - 1;
        } catch (NumberFormatException e) {
            throw new MoneyTrackerException("The index is invalid.");
        }
        Transaction transactionToEdit;
        try {
            transactionToEdit = transactions.getTransaction(transactions.getSearchResultIndex(index));
        } catch (IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("The index is invalid.");
        }
        String currentDescription = transactionToEdit.toString();
        if (editParams.containsKey("amount")) {
            transactionToEdit.setAmount(Double.parseDouble(editParams.get("amount")));
        }
        if (editParams.containsKey("category")) {
            if (getTransactionType(transactionToEdit).equals("INCOME")) {
                ((Income) transactionToEdit).setIncomeCategory(editParams.get("category"));
            }
            if (getTransactionType(transactionToEdit).equals("EXPENSE")) {
                ((Expense) transactionToEdit).setExpenseCategory(editParams.get("category"));
            }
        }
        if (editParams.containsKey("date")) {
            transactionToEdit.setDate(editParams.get("date"));
        }
        if (editParams.containsKey("description")) {
            transactionToEdit.setDescription(editParams.get("description"));
        }
        String newDescription = transactionToEdit.toString();
        storage.saveTransactions(transactions);
        String type = getTransactionType(transactionToEdit).toLowerCase();
        ui.printEditItem(currentDescription, newDescription, type);
        transactions.setIsInitialized(false);
    }
}