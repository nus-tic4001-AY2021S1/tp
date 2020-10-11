package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.parser.Parser;
import moneytracker.storage.Storage;
import moneytracker.transaction.ExpenseCategoryList;
import moneytracker.transaction.IncomeCategoryList;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Income;
import moneytracker.transaction.Transaction;
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
        transactions.setIsInitialized(false);
        int transactionIndex = Parser.getTransactionIndex(fullCommand);
        Transaction transactionToDelete;
        try {
            transactionToDelete =
                    transactions.getTransaction(transactions.getSearchResultIndex(transactionIndex));
        } catch (IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("The transaction index is invalid.");
        }
        String transactionDescription = transactionToDelete.toString();
        String transactionType = getTransactionType(transactionToDelete);
        transactions.removeTransaction(transactions.getSearchResultIndex(transactionIndex));
        storage.saveTransactions(transactions);
        ui.printRemovedTransaction(transactions.getSize(), transactionDescription, transactionType);
    }

    private String getTransactionType(Transaction transaction) throws MoneyTrackerException {
        String transactionType;
        if (transaction instanceof Income) {
            transactionType = "Income";
        } else if (transaction instanceof Expense) {
            transactionType = "Expense";
        } else {
            throw new MoneyTrackerException("The transaction type is invalid");
        }
        return transactionType;
    }
}
