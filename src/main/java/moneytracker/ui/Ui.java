package moneytracker.ui;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Category;
import moneytracker.transaction.CategoryList;
import moneytracker.transaction.Income;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Contains methods that interacts with the users such as obtaining commands
 * from user input and displaying messages to the users.
 */
public class Ui {
    public static final String LINE = "____________________________________________________________________";
    public static final String INDENT = "   ";

    /**
     * Gets the input stream from the user.
     *
     * @return Input stream from the user.
     */
    public String readUserCommand() {
        Scanner in = new Scanner(System.in);
        System.out.print("You:  ");
        return in.nextLine().trim();
    }

    /**
     * Executes Welcome message.
     */
    public void printWelcome() {
        String logo = " __  __                          _______             _             " + System.lineSeparator()
                + "|  \\/  |                        |__   __|           | |            " + System.lineSeparator()
                + "| \\  / | ___  _ __   ___ _   _     | |_ __ __ _  ___| | _____ _ __ " + System.lineSeparator()
                + "| |\\/| |/ _ \\| '_ \\ / _ \\ | | |    | | '__/ _` |/ __| |/ / _ \\ '__|" + System.lineSeparator()
                + "| |  | | (_) | | | |  __/ |_| |    | | | | (_| | (__|   <  __/ |   " + System.lineSeparator()
                + "|_|  |_|\\___/|_| |_|\\___|\\__, |    |_|_|  \\__,_|\\___|_|\\_\\___|_|   " + System.lineSeparator()
                + "                          __/ |                                    " + System.lineSeparator()
                + "                         |___/                                     " + System.lineSeparator();
        System.out.println(logo);
        System.out.println("Hello! What can I do for you?");
        printLine();
    }

    public void printAddTransaction(TransactionList transactions) throws MoneyTrackerException {
        Transaction transactionToPrint = transactions.getTransaction(transactions.getSize() - 1);
        if (transactionToPrint instanceof Income) {
            System.out.println("Got it! I have added this income:");
        } else if (transactionToPrint instanceof Expense) {
            System.out.println("Got it! I have added this expense:");
        } else {
            throw new MoneyTrackerException("The transaction type is invalid");
        }
        printIndentation();
        System.out.println(transactionToPrint.toString());
        printIndentation();
        System.out.println("Now you have " + transactions.getSize() + " transactions in your list.");
        printLine();
    }

    public void printAddCategory(CategoryList categories) throws MoneyTrackerException {
        Category categoryToPrint = categories.getCategory(categories.getSize() - 1);
        if (categoryToPrint.getType().equals("INCOME")) {
            System.out.println("Got it! I have added this income category:");
        } else if (categoryToPrint.getType().equals("EXPENSE")) {
            System.out.println("Got it! I have added this expense category:");
        } else {
            throw new MoneyTrackerException("The category type is invalid");
        }
        printIndentation();
        System.out.println(categoryToPrint);
        printIndentation();
        System.out.println("Now you have " + categories.getSize() + " categories in your list.");
        printLine();
    }

    public void printRemoveCategory(int size, String description, String type) {
        System.out.println("Noted! I have removed this " + type + ": ");
        printIndentation();
        System.out.println(description);
        printIndentation();
        System.out.println("Now you have " + size + " categories in the list.");
        printLine();
    }

    public void printRemoveTransaction(int size, String description, String type) {
        System.out.println("Noted! I have removed this " + type + ": ");
        printIndentation();
        System.out.println(description);
        printIndentation();
        System.out.println("Now you have " + size + " transactions in the list.");
        printLine();
    }

    public void printEditItem(String oldDescription, String newDescription, String type) {
        System.out.println("Noted! I have edited this " + type + ": ");
        printIndentation();
        System.out.println("From " + oldDescription + " to " + newDescription);
        printLine();
    }

    public void printError(String errorMessage) {
        System.out.println("OOPS!! " + errorMessage);
        printLine();
    }

    public void printHelp() {
        System.out.println("Please refer this online user guide:");
        System.out.println("https://ay2021s1-tic4001-2.github.io/tp/UserGuide.html");
        printLine();
    }

    public void printGoodbye() {
        System.out.println("Bye! Hope to see you again soon.");
        printLine();
    }

    public void printClearConfirmation() {
        System.out.println("Are you sure you want to clear all data? Y / N");
    }

    public void printClearAllData(String confirmation) {
        if (confirmation.equals("Y")) {
            System.out.println("Noted! I have cleared all data.");
        } else if (confirmation.equals("N")) {
            System.out.println("Noted! Your data is untouched.");
        } else {
            System.out.println("Sorry, please enter \"Y\" or \"N\" only");
        }
        printLine();
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printIndentation() {
        System.out.print(INDENT);
    }

    /**
     * Executes the list transactions command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     */
    public void printListTransaction(TransactionList transactions) {
        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {
            System.out.println("Here are your transactions:");
            for (int i = 0; i < transactions.getSize(); i++) {
                printIndentation();
                System.out.println((i + 1) + ". " + transactions.getTransaction(i).toString());
            }
        }
        printLine();
    }


    public void printRemoveTransaction(int size, String description, String type) {
        System.out.println("Noted! I have removed this " + type + ": ");
        printIndentation();
        System.out.println(description);
        printIndentation();
        System.out.println("Now you have " + size + " transactions in the list.");
        printLine();
    }

    /**
     * Executes the FilteredTransactions command.
     *
     * @param transactions List of <code>Transaction</code> objects.
     */
    public void printFilteredTransactions(TransactionList transactions) {
        ArrayList<Integer> searchResultIndexes = transactions.getSearchResultIndexes();
        {
            for (int i = 0; i < searchResultIndexes.size(); i++) {
                printIndentation();
                System.out.println((i + 1) + ". " + transactions.getTransaction(searchResultIndexes.get(i)).toString());
            }
        }
        printLine();
    }

    /**
     * Executes the list transactions command to only display expense records.
     *
     * @param transactions List of <code>Transaction</code> objects.
     */
    public void printListTransactionExpenseOnly(TransactionList transactions) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();
        int matcheCount = 0;

        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {

            for (int i = 0; i < transactions.getSize(); i++) {
                Transaction parentRecord = transactions.getTransaction(i);
                if (parentRecord instanceof Expense) {
                    matcheCount = matcheCount + 1;
                    transactions.addSearchResultIndex(i);
                }
            }

        }


        if (matcheCount == 0) {
            System.out.println("Sorry! Cannot find any matched expenses records");
        } else {
            System.out.println("Here are your expenses:");
            printFilteredTransactions(transactions);
        }


    }

    /**
     * Executes the list transactions command to only display income records.
     *
     * @param transactions List of <code>Transaction</code> objects.
     */
    public void printListTransactionIncomeOnly(TransactionList transactions) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();
        int matcheCount = 0;

        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {

            for (int i = 0; i < transactions.getSize(); i++) {
                Transaction parentRecord = transactions.getTransaction(i);
                if (parentRecord instanceof Income) {
                    matcheCount = matcheCount + 1;
                    transactions.addSearchResultIndex(i);
                }
            }
        }

        if (matcheCount == 0) {
            System.out.println("Sorry! Cannot find any matched income records");
        } else {
            System.out.println("Here are your incomes:");
            printFilteredTransactions(transactions);
        }


    }

    /**
     * Executes the list transactions command to only display records in selected month.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param listMonthName String of Month from the user input.
     */
    public void printListTransactionMonthOnly(TransactionList transactions, String listMonthName) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();
        int matchedMonthCount = 0;

        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {

            for (int i = 0; i < transactions.getSize(); i++) {
                if (transactions.getTransaction(i).setMonth().equals(listMonthName)) {
                    matchedMonthCount = matchedMonthCount + 1;
                    transactions.addSearchResultIndex(i);
                }
            }

        }

        if (matchedMonthCount == 0) {
            System.out.println("Sorry! Cannot find any matched transactions for " + listMonthName);
        } else {
            System.out.println("Here are your transactions for " + listMonthName + " :");
            printFilteredTransactions(transactions);
        }

    /**
     * Executes the list transactions command to only display income records in selected month.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param listMonthName String of Month from the user input.
     */
    public void printListTransactionIncomeByMonth(TransactionList transactions, String listMonthName) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();
        int matchedMonthCount = 0;

        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {

            for (int i = 0; i < transactions.getSize(); i++) {
                Transaction parentRecord = transactions.getTransaction(i);
                if (parentRecord instanceof Income & transactions.getTransaction(i).setMonth().equals(listMonthName)) {
                    matchedMonthCount = matchedMonthCount + 1;
                    transactions.addSearchResultIndex(i);
                }
            }
        }

        if (matchedMonthCount == 0) {
            System.out.println("Sorry! Cannot find any matched income records for " + listMonthName);
        } else {
            System.out.println("Here are your income records for " + listMonthName + " :");
            printFilteredTransactions(transactions);
        }
    }

    /**
     * Checks if the records match to the income category.
     * Return the match counts.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param userInputCategoryDetails String of category details from the user input.
     */
    public int checkIncomeCategoryDetails(TransactionList transactions, String userInputCategoryDetails) {
        String incomeCategoryDetails;
        int matchedCount = 0;
        for (int i = 0; i < transactions.getSize(); i++) {
            Transaction parentRecord = transactions.getTransaction(i);
            if (parentRecord instanceof Income) {
                incomeCategoryDetails = ((Income) parentRecord).getIncomeCategory();
                if (incomeCategoryDetails.contains(userInputCategoryDetails)) {
                    matchedCount = matchedCount + 1;
                }
            }
        }
        return matchedCount;
    }

    /**
     * Checks if the records match to the expense category.
     * Return the match counts.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param userInputCategoryDetails String of category details from the user input.
     */
    public int checkExpenseCategoryDetails(TransactionList transactions, String userInputCategoryDetails) {
        String expenseCategoryDetails;
        int matchedCount = 0;
        for (int i = 0; i < transactions.getSize(); i++) {
            Transaction parentRecord = transactions.getTransaction(i);
            if (parentRecord instanceof Expense) {
                expenseCategoryDetails = ((Expense) parentRecord).getExpenseCategory();
                if (expenseCategoryDetails.contains(userInputCategoryDetails)) {
                    matchedCount = matchedCount + 1;
                }
            }
        }
        return matchedCount;
    }

    /**
     * Executes the list transactions command to only display income records in selected month and category.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param listMonthName String of Month from the user input.
     * @param categoryDetails String of categoryDetails from the user input.
     */
    public void printListTransactionIncomeByMonthByCatDetails(TransactionList transactions,
                                                              String listMonthName, String categoryDetails) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();
        int matchedMonthCategoryDetailsCount = 0;
        int matchedMonthCount = 0;

        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {

            for (int i = 0; i < transactions.getSize(); i++) {

                Transaction parentRecord = transactions.getTransaction(i);

                if ((parentRecord instanceof Income)
                        & (transactions.getTransaction(i).setMonth().equals(listMonthName))) {
                    matchedMonthCount = matchedMonthCount + 1;

                    if (((Income) parentRecord).getIncomeCategory().equals(categoryDetails)) {
                        matchedMonthCategoryDetailsCount = matchedMonthCategoryDetailsCount + 1;
                        transactions.addSearchResultIndex(i);
                    }

                }
            }
        }


        if (matchedMonthCount == 0) {
            System.out.println("Sorry! Cannot find any matched income records for " + listMonthName);
        }
        if (checkIncomeCategoryDetails(transactions, categoryDetails) == 0) {
            System.out.println("Sorry! Cannot find any matched income categories for " + categoryDetails);
        } else if (matchedMonthCategoryDetailsCount == 0) {
            System.out.println("Sorry! Cannot find any matched income records ("
                    + categoryDetails + ") for " + listMonthName);
        } else {
            System.out.println("Here are your income records (" + categoryDetails + ") for " + listMonthName + " :");
            printFilteredTransactions(transactions);
        }
    }

    /**
     * Executes the list transactions command to only display expense records in selected month.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param listMonthName String of Month from the user input.
     */
    public void printListTransactionExpenseByMonth(TransactionList transactions, String listMonthName) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();
        int matchedMonthCount = 0;

        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {


            for (int i = 0; i < transactions.getSize(); i++) {
                Transaction parentRecord = transactions.getTransaction(i);
                if (parentRecord instanceof Expense & transactions.getTransaction(i).setMonth().equals(listMonthName)) {
                    matchedMonthCount = matchedMonthCount + 1;
                    transactions.addSearchResultIndex(i);
                }
            }
        }

        if (matchedMonthCount == 0) {
            System.out.println("Sorry! Cannot find any matched expense records for " + listMonthName);
        } else {
            System.out.println("Here are your expense records for " + listMonthName + " :");
            printFilteredTransactions(transactions);
        }

    }

    /**
     * Executes the list transactions command to only display expense records in selected month and category.
     *
     * @param transactions List of <code>Transaction</code> objects.
     * @param listMonthName String of Month from the user input.
     * @param categoryDetails String of categoryDetails from the user input.
     */
    public void printListTransactionExpenseByMonthByCatDetails(TransactionList transactions, String listMonthName,
                                                               String categoryDetails) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();
        int matchedMonthCategoryDetailsCount = 0;
        int matchedMonthCount = 0;


        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {

            for (int i = 0; i < transactions.getSize(); i++) {
                Transaction parentRecord = transactions.getTransaction(i);
                if (parentRecord instanceof Expense & transactions.getTransaction(i).setMonth().equals(listMonthName)) {
                    matchedMonthCount = matchedMonthCount + 1;
                    if (((Expense) parentRecord).getExpenseCategory().equals(categoryDetails)) {
                        matchedMonthCategoryDetailsCount = matchedMonthCategoryDetailsCount + 1;
                        transactions.addSearchResultIndex(i);
                    }
                }
            }
        }


        if (matchedMonthCount == 0) {
            System.out.println("Sorry! Cannot find any matched expense records for " + listMonthName);
        }
        if (checkExpenseCategoryDetails(transactions, categoryDetails) == 0) {
            System.out.println("Sorry! Cannot find any matched expense categories for " + categoryDetails);
        } else if (matchedMonthCategoryDetailsCount == 0) {
            System.out.println("Sorry! Cannot find any matched expense records ("
                    + categoryDetails + ") for " + listMonthName);
        } else {
            System.out.println("Here are your expense records (" + categoryDetails + ") for " + listMonthName + " :");
            printFilteredTransactions(transactions);
        }
    }


    /**
     * Executes the Filtered Categories command.
     *
     * @param categories List of <code>categories</code> objects.
     */
    private void printFilteredCategories(CategoryList categories) {
        ArrayList<Integer> searchResultIndexes = categories.getSearchResultIndexes();
        {
            for (int i = 0; i < searchResultIndexes.size(); i++) {
                printIndentation();
                System.out.println((i + 1) + ". " + categories.getCategory(searchResultIndexes.get(i)).toString());
            }
        }
        printLine();
    }

    /**
     * Executes the list category command.
     *
     * @param categories List of <code>categories</code> objects.
     */
    public void printListCategory(CategoryList categories) {
        if (categories.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {
            System.out.println("Here are your categories:");
            for (int i = 0; i < categories.getSize(); i++) {
                printIndentation();
                System.out.println((i + 1) + ". " + categories.getCategory(i).toString());
            }
        }
        printLine();
    }

    /**
     * Executes the list category command to display expense categoty only.
     *
     * @param categories List of <code>categories</code> objects.
     */
    public void printListCategoryExpenseOnly(CategoryList categories) {
        categories.setIsInitialized(true);
        categories.clearSearchResultIndexes();

        if (categories.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {
            System.out.println("Here are your expenses categories:");
            for (int i = 0; i < categories.getSize(); i++) {
                if (categories.getCategory(i).getType().equals("EXPENSE")) {
                    categories.addSearchResultIndex(i);
                }
            }
        }
        printFilteredCategories(categories);
    }

    /**
     * Executes the list category command to display income categoty only.
     *
     * @param categories List of <code>categories</code> objects.
     */
    public void printListCategoryIncomeOnly(CategoryList categories) {
        categories.setIsInitialized(true);
        categories.clearSearchResultIndexes();

        if (categories.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {
            System.out.println("Here are your income categories:");
            for (int i = 0; i < categories.getSize(); i++) {
                if (categories.getCategory(i).getType().equals("INCOME")) {
                    categories.addSearchResultIndex(i);
                }
            }
        }
        printFilteredCategories(categories);
    }
}