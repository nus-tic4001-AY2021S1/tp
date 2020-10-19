package moneytracker.command;

import moneytracker.budget.Budget;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Contains the methods for user to change the monthly budget.
 */
public class BudgetCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>BudgetCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public BudgetCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }
    /**
     * Executes the budget command.
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
        budget.setBudget(Parser.getBudgetAmount(fullCommand));
        System.out.println(budget.getAmount());
        storage.saveBudget(budget);
        ui.printBudget(budget);
    }
}