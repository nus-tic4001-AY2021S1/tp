package moneytracker.ui;

import moneytracker.transaction.ExpenseCategoryList;
import moneytracker.transaction.IncomeCategoryList;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Income;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    public static final String LINE = "____________________________________________________________________";
    public static final String INDENT = "   ";

    /**
     * Get the input stream from the user.
     * @return Input stream from the user.
     */
    public String readUserCommand() {
        Scanner in = new Scanner(System.in);
        System.out.print("You:  ");
        return in.nextLine().trim();
    }

    public void printWelcome() {
        String logo = " __  __                          _______             _             " + System.lineSeparator()
                + "|  \\/  |                        |__   __|           | |            " + System.lineSeparator()
                + "| \\  / | ___  _ __   ___ _   _     | |_ __ __ _  ___| | _____ _ __ " + System.lineSeparator()
                + "| |\\/| |/ _ \\| '_ \\ / _ \\ | | |    | | '__/ _` |/ __| |/ / _ \\ '__|" + System.lineSeparator()
                + "| |  | | (_) | | | |  __/ |_| |    | | | | (_| | (__|   <  __/ |   " + System.lineSeparator()
                + "|_|  |_|\\___/|_| |_|\\___|\\__, |    |_|_|  \\__,_|\\___|_|\\_\\___|_|   " + System.lineSeparator()
                + "                          __/ |                                    " + System.lineSeparator()
                + "                         |___/                                     " + System.lineSeparator();

        printLine();
        System.out.println(logo);
        System.out.println("Hello! What can I do for you?");
        printLine();
    }

    public void printAddedTransaction(TransactionList transactions) throws MoneyTrackerException {
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

    public void printAddedExpenseCategory(ExpenseCategoryList expenseCategories) {
        System.out.println("Got it! I’ve added this expense category:");
        printIndentation();
        System.out.println(expenseCategories.getExpenseCategory(expenseCategories.getSize() - 1).toString());
        printIndentation();
        System.out.println("Now you have " + expenseCategories.getSize() + " expense categories in your list.");
        printLine();
    }

    public void printAddedIncomeCategory(IncomeCategoryList incomeCategories) {
        System.out.println("Got it! I’ve added this income category:");
        printIndentation();
        System.out.println(incomeCategories.getIncomeCategory(incomeCategories.getSize() - 1).toString());
        printIndentation();
        System.out.println("Now you have " + incomeCategories.getSize() + " income categories in your list.");
        printLine();
    }


    public void printError(String errorMessage) {
        System.out.println("OOPS!! " + errorMessage);
        printLine();
    }

    public void printHelp() {
        System.out.println("User guide will be available soon!");
        printLine();
    }

    public void printGoodbye() {
        System.out.println("Bye! Hope to see you again soon.");
        printLine();
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printIndentation() {
        System.out.print(INDENT);
    }

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

    public void printRemovedTransaction(int size, String transactionDescription, String transactionType) {
        System.out.println("Noted! I've removed this " + transactionType + ": ");
        printIndentation();
        System.out.println(transactionDescription);
        printIndentation();
        System.out.println("Now you have " + size + " transactions in the list.");
        printLine();
    }

    public void printFilteredTasks(TransactionList transactions) {
        ArrayList<Integer> searchResultIndexes = transactions.getSearchResultIndexes();
        {
            for (int i = 0; i < searchResultIndexes.size(); i++) {
                printIndentation();
                System.out.println((i + 1) + ". " + transactions.getTransaction(searchResultIndexes.get(i)).toString());
            }
        }
        printLine();
    }


    public void printListTransactionExpenseOnly(TransactionList transactions) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();

        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {
            System.out.println("Here are your expenses:");
            for (int i = 0; i < transactions.getSize(); i++) {
                Transaction parentRecord = transactions.getTransaction(i);
                if (parentRecord instanceof Expense) {
                    transactions.addSearchResultIndex(i);
                }
            }

        }

        printFilteredTasks(transactions);

    }

    public void printListTransactionIncomeOnly(TransactionList transactions) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();

        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {
            System.out.println("Here are your incomes:");
            for (int i = 0; i < transactions.getSize(); i++) {
                Transaction parentRecord = transactions.getTransaction(i);
                if (parentRecord instanceof Income) {
                    transactions.addSearchResultIndex(i);
                }
            }
        }

        printFilteredTasks(transactions);
    }


    public void printListTransactionMonthOnly(TransactionList transactions, String listMonthName) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();

        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {
            System.out.println("Here are your transactions for " + listMonthName + " :");
            for (int i = 0; i < transactions.getSize(); i++) {
                if (transactions.getTransaction(i).setMonth().equals(listMonthName)) {
                    transactions.addSearchResultIndex(i);   //// add matched index to a new list
                }
            }

        }
        printFilteredTasks(transactions);
    }


    public void printListTransactionIncomeByMonth(TransactionList transactions, String listMonthName) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();

        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {
            System.out.println("Here are your income records for " + listMonthName + " :");
            for (int i = 0; i < transactions.getSize(); i++) {
                Transaction parentRecord = transactions.getTransaction(i);
                if (parentRecord instanceof Income & transactions.getTransaction(i).setMonth().equals(listMonthName)) {
                    transactions.addSearchResultIndex(i);
                }
            }
        }

        printFilteredTasks(transactions);
    }

    public void printListTransactionExpenseByMonth(TransactionList transactions, String listMonthName) {
        transactions.setIsInitialized(true);
        transactions.clearSearchResultIndexes();

        if (transactions.getSize() == 0) {
            System.out.println("Sorry, there is no record in your list.");
        } else {

            System.out.println("Here are your expense records for " + listMonthName + " :");
            for (int i = 0; i < transactions.getSize(); i++) {
                Transaction parentRecord = transactions.getTransaction(i);
                if (parentRecord instanceof Expense & transactions.getTransaction(i).setMonth().equals(listMonthName)) {
                    transactions.addSearchResultIndex(i);
                }
            }
        }
        printFilteredTasks(transactions);
    }


}