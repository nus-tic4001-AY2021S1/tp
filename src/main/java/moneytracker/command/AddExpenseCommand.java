package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Contains the methods for user to add an expense.
 */
public class AddExpenseCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>AddExpenseCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public AddExpenseCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the add expense command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     * @param categories List of categories.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        CategoryList categories) throws MoneyTrackerException {
        transactions.addTransaction(Parser.createExpense(fullCommand), categories);
        storage.saveTransactions(transactions);
        ui.printAddedTransaction(transactions);
    }
}
