package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.storage.Storage;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

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
    public void execute(TransactionList transactions, Ui ui, Storage storage) throws MoneyTrackerException {
  

    }
}
