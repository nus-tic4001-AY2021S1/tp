package moneytracker;

import moneytracker.command.Command;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

public class MoneyTracker {
    private final Storage storage;
    private final Ui ui;
    private TransactionList transactions;
    private CategoryList categories;

    /**
     * Initializes a <code>MoneyTracker</code> object.
     *
     * @param transactionsFilePath Path of the text file used for storing app data.
     */
    public MoneyTracker(String transactionsFilePath, String categoriesFilePath) {
        assert !transactionsFilePath.isBlank() : "filePath should not be blank";
        ui = new Ui();
        storage = new Storage(transactionsFilePath, categoriesFilePath);
        try {
            storage.createDirectory();
        } catch (MoneyTrackerException e) {
            ui.printError(e.getMessage());
        }
        try {
            categories = new CategoryList(storage.loadCategories(categoriesFilePath));
        } catch (MoneyTrackerException e) {
            ui.printError(e.getMessage());
            categories = new CategoryList();
        }
        try {
            transactions = new TransactionList(storage.loadTransactions(transactionsFilePath));
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
                c.execute(transactions, ui, storage, categories);
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
        new MoneyTracker("data/transactions.txt",
                "data/categories.txt").run();
    }
}