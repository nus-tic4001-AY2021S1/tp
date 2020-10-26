package moneytracker.command;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.summary.Budget;
import moneytracker.storage.Storage;
import moneytracker.transaction.TransactionList;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.Income;
import moneytracker.transaction.Expense;
import moneytracker.transaction.CategoryList;
import moneytracker.ui.Ui;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Contains the methods for user to list all transactions.
 */
public class ListCommand extends Command {
    private final String fullCommand;
    private final String errMsg = null;

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
     * @param categories List of <code>Category</code> objects.
     * @param budget <code>Budget</code> object.
     */
    @Override
    public void execute(TransactionList transactions, Ui ui, Storage storage,
                        CategoryList categories, Budget budget) throws MoneyTrackerException {


        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();
        for (int i = 0; i < transactions.getSize(); i++) {
            transactions.addSearchResultIndex(i);
        }


        String commandParameterString = fullCommand.replaceFirst("(?i)list", "").trim();
        String[] commandParametersArray = commandParameterString.split("/");
        String[] rules = new String[commandParametersArray.length];
        for (int i = 0; i < commandParametersArray.length; i++) {
            rules[i] = commandParametersArray[i].trim();
        }


        ArrayList<Transaction> filteredTransaction = transactions.getTransactions();
        if (transactions.getSize() == 0) {
            throw new MoneyTrackerException("Sorry, there is no transaction in your list.");
        }

        assert commandParameterString.isBlank() : "Command Parameter is blank, so execute list all function";
        /// When there is only one keyword: list, so the commandParameterString is Empty.
        /// We have to execute list all records. and return to main screen
        if (commandParameterString.isEmpty()) {
            ui.printListTransaction(transactions);
            return;
        }

        ///When there are multiple keywords in the list command, we have to check each rule and return matched records
        for (int i = 1; i < rules.length; i++) {
            filteredTransaction = getFilteredList(filteredTransaction, rules[i].trim());
        }
        ui.printMyFilteredTransactions(filteredTransaction);

    }


    /**
     * Gets a list of transactions which satisfies filter requirement.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param rule String of rule provided by the user input.
     */
    protected ArrayList<Transaction>  getFilteredList(ArrayList<Transaction> transactions, String rule)
            throws MoneyTrackerException {

        if (rule.equals("te")) {
            ArrayList<Transaction> filterByExpense = (ArrayList<Transaction>) transactions.stream().filter(record ->
                    record instanceof Expense).collect(Collectors.toList());
            if (filterByExpense.size() < 1) {
                throw new MoneyTrackerException("Sorry, there is no expense in your list.");
            }
            return filterByExpense;
        } else if (rule.equals("ti")) {
            ArrayList<Transaction> filterByIncome = (ArrayList<Transaction>) transactions.stream().filter(record ->
                    record instanceof Income).collect(Collectors.toList());
            if (filterByIncome.size() < 1) {
                throw new MoneyTrackerException("Sorry, there is no income in your list.");
            }
            return filterByIncome;
        }  else if (rule.startsWith("c")) {
            String inputCategory = rule.replace("c", "").trim().toUpperCase();
            ArrayList<Transaction> expenseList = (ArrayList<Transaction>) transactions.stream().filter(record ->
                    record instanceof Expense)
                    .filter(record -> ((Expense) record).getExpenseCategory().equals(inputCategory))
                    .collect(Collectors.toList());
            ArrayList<Transaction> incomeList = (ArrayList<Transaction>) transactions.stream().filter(record
                -> record instanceof Income).filter(record -> ((Income) record)
                    .getIncomeCategory().equals(inputCategory)).collect(Collectors.toList());
            ArrayList<Transaction> filteredByCategory = new ArrayList<>(expenseList);
            filteredByCategory.addAll(incomeList);

            if (incomeList.size() < 1 && expenseList.size() < 1) {
                throw new MoneyTrackerException("Sorry, there is no category '" + inputCategory + "' in the list.");
            }

            return filteredByCategory;

        } else if (rule.startsWith("m")) {
            String inputMonth = rule.replace("m", "").trim();
            ArrayList<Transaction> filterByMonthList = (ArrayList<Transaction>) transactions.stream().filter(record
                -> record.setMonth().equals(inputMonth)).collect(Collectors.toList());
            if (filterByMonthList.size() < 1) {
                throw new MoneyTrackerException("Sorry, there is no record for '" +  inputMonth + "'.");
            }
            return filterByMonthList;
        }

        throw new MoneyTrackerException(("Sorry, invalid list command."));
    }
}
