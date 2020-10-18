package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.storage.Storage;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.Income;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;
import moneytracker.ui.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Contains the methods for user to list all transactions.
 */
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

    /**
     * Executes the list transactions command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param ui <code>Ui</code> object for displaying user interactions.
     * @param storage <code>Storage</code> object for loading and saving user data.
     * @param categories List of categories.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        CategoryList categories) throws MoneyTrackerException {
        String listMonthName = null;
        String categoryDetails = null;

        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();
        for (int i = 0; i < transactions.getSize(); i++) {
            transactions.addSearchResultIndex(i);
        }


        String commandParameterString = fullCommand.replaceFirst("(?i)list", "").trim();
        String[] commandParametersArray = commandParameterString.split("/");
        final int lenCommandParameters = commandParametersArray.length;

        /// When there is only one keyword: list, so the commandParameterString is Empty.
        /// We have to execute list all records. and return to main screen
        if (commandParameterString.isEmpty()) {
            ui.printListTransaction(transactions);
            throw new MoneyTrackerException("");
        }

        for (String innerCommand : commandParametersArray) {
            if (innerCommand.toLowerCase().startsWith("m")) {
                listMonthName = innerCommand.replace("m", "").trim();
            }
            if (innerCommand.toLowerCase().startsWith("c")) {
                categoryDetails = innerCommand.replace("c", "").trim().toUpperCase();
            }
        }

        Arrays.parallelSetAll(commandParametersArray, (i) -> commandParametersArray[i].trim());
        List<String> list = Arrays.asList(commandParametersArray);

        if ((lenCommandParameters == 2) & (commandParametersArray[1].toLowerCase().equals("te"))) {
            ui.printListTransactionExpenseOnly(transactions);
        } else if ((lenCommandParameters == 2) & (commandParametersArray[1].toLowerCase().equals("ti"))) {
            ui.printListTransactionIncomeOnly(transactions);
        } else if ((lenCommandParameters == 2) & (commandParametersArray[1].toLowerCase().startsWith("m"))) {
            ui.printListTransactionMonthOnly(transactions,listMonthName);
        } else if ((lenCommandParameters == 3) & (list.contains("te") || list.contains("m"))) {
            ui.printListTransactionExpenseByMonth(transactions,listMonthName);
        } else if ((lenCommandParameters == 3) & (list.contains("ti") || list.contains("m"))) {
            ui.printListTransactionIncomeByMonth(transactions,listMonthName);
        } else if ((lenCommandParameters == 4) & (list.contains("ti") || list.contains("m") || list.contains("c"))) {
            ui.printListTransactionIncomeByMonthByCatDetails(transactions,listMonthName,categoryDetails);
        } else if ((lenCommandParameters == 4) & (list.contains("te") || list.contains("m") || list.contains("c"))) {
            ui.printListTransactionExpenseByMonthByCatDetails(transactions,listMonthName,categoryDetails);
        }

    }
}
