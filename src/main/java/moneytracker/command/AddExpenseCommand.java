package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.ExpenseCategoryList;
import moneytracker.transaction.IncomeCategoryList;
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

    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        IncomeCategoryList incomeCategories,
                        ExpenseCategoryList expenseCategories) throws MoneyTrackerException {
        transactions.addTransaction(Parser.createExpense(fullCommand));
        storage.saveTransactions(transactions);
        ui.printAddedTransaction(transactions);
    }
}
