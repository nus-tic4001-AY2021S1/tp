package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.ExpenseCategoryList;
import moneytracker.transaction.IncomeCategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Contains the methods for user to add an income.
 */
public class AddIncomeCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>AddIncomeCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public AddIncomeCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        IncomeCategoryList incomeCategories,
                        ExpenseCategoryList expenseCategories) throws MoneyTrackerException {
        transactions.addTransaction(Parser.createIncome(fullCommand));
        storage.saveTransactions(transactions);
        ui.printAddedTransaction(transactions);
    }
}
