package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.storage.Storage;
import moneytracker.transaction.ExpenseCategoryList;
import moneytracker.transaction.IncomeCategoryList;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

import java.util.Arrays;

public class ListCommand extends Command {
    private final String fullCommand;

    /**
     * Initializes a <code>ListCommand</code> object.
     *
     * @param fullCommand User's full input string.
     */
    public ListCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        IncomeCategoryList incomeCategories,
                        ExpenseCategoryList expenseCategories) throws MoneyTrackerException {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();
        for (int i = 0; i < transactions.getSize(); i++) {
            transactions.addSearchResultIndex(i);
        }


        String listMonthName = null;

        int len = this.fullCommand.split(" ").length;


        String[] lineArr = this.fullCommand.split(" ", 4);
        assert lineArr.length == 4 : "OOPS!!! There are multiple description inputs.";

        for (String inner : lineArr) {
            if (inner.toLowerCase().contains("/m")) {
                listMonthName = inner.replace("/m", "").trim();
            }
        }


        if (len == 1) {
            ui.printListTransaction(transactions);
        } else if ((len == 2) & (lineArr[1].toLowerCase().equals("/te"))) {
            ui.printListTransactionExpenseOnly(transactions);
        } else if ((len == 2) & (lineArr[1].toLowerCase().equals("/ti"))) {
            ui.printListTransactionIncomeOnly(transactions);
        } else if ((len == 2) & (lineArr[1].toLowerCase().contains("/m"))) {
            ui.printListTransactionMonthOnly(transactions,listMonthName);
        } else if ((len == 3) & (Arrays.toString(lineArr).contains("/te"))) {
            ui.printListTransactionExpenseByMonth(transactions,listMonthName);
        } else if ((len == 3) & (Arrays.toString(lineArr).contains("/ti"))) {
            ui.printListTransactionIncomeByMonth(transactions,listMonthName);
        } else if (len < 1) {
            ui.printError("OOPS!!! The description cannot be empty.");
        }
    }
}
