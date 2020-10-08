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
        Transaction testExpense1 = new Expense(100, "Expensive dinner",
                "2020-12-25", "FOOD");
        System.out.println(testExpense1);
        Transaction testExpense2 = new Expense(200, "Expensive dinner",
                "2020-12-25", "FOOD");
        System.out.println(testExpense2);
        Transaction testExpense3 = new Expense(300, "Expensive dinner",
                "2020-12-25", "FOOD");
        System.out.println(testExpense3);

        transactions.addTransaction(testExpense1);
        transactions.addTransaction(testExpense2);
        transactions.addTransaction(testExpense3);
        transactions.setIsInitialized(false);
        System.out.println("added new things to expenses transaction");

    }
}
