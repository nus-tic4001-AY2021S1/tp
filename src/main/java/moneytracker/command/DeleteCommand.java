package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.ExpenseCategoryList;
import moneytracker.transaction.IncomeCategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

/**
 * Contains the methods for user to delete a transaction.
 */
public class DeleteCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>DeleteCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        IncomeCategoryList incomeCategories,
                        ExpenseCategoryList expenseCategories) throws MoneyTrackerException {
        if (!(transactions.getIsInitialized())) {
            throw new MoneyTrackerException("Please run the list command first.");
        }
        int transactionIndex = Parser.getTransactionIndex(fullCommand);
        String transactionDescription;
        try {
            transactionDescription =
                    transactions.getTransaction(transactions.getSearchResultIndex(transactionIndex)).toString();
            transactions.removeTransaction(transactions.getSearchResultIndex(transactionIndex));
            transactions.setIsInitialized(false);
        } catch (IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("The transaction index is invalid.");
        }
        storage.saveTransactions(transactions);
        ui.printRemovedTransaction(transactions.getSize(), transactionDescription);
    }
}
