package moneytracker;

import moneytracker.command.Command;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.ExpenseCategoryList;
import moneytracker.transaction.IncomeCategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

public class MoneyTracker {
    private final Storage storage;
    private final Ui ui;
    private TransactionList transactions;
    private IncomeCategoryList incomeCategories;
    private ExpenseCategoryList expenseCategories;

    /**
     * Initializes a <code>MoneyTracker</code> object.
     *
     * @param filePath Path of the text file used for storing app data.
     */
    public MoneyTracker(String filePath) {
        assert !filePath.isBlank() : "filePath should not be blank";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            transactions = new TransactionList(storage.loadTransactions(filePath));
            incomeCategories = new IncomeCategoryList();
            expenseCategories = new ExpenseCategoryList();
        } catch (MoneyTrackerException e) {
            ui.printError(e.getMessage());
            transactions = new TransactionList();
        }
    }

    public void run() {
        ui.printWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(transactions, ui, storage, incomeCategories, expenseCategories);
                isExit = c.isExit();
            } catch (MoneyTrackerException e) {
                ui.printError(e.getMessage());
            }
        }
    }

    /**
     *  Main method of Money Tracker. This is the starting point of the app.
     *  @param args Command line arguments. Not used.
     */
    public static void main(String[] args) {
        new MoneyTracker("data/transactions.txt").run();
    }
}