package moneytracker.storage;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Income;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    private final String filePath;

    /**
     * Initializes a <code>Storage</code> object.
     *
     * @param filePath Path of the text file used for storing app data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Transaction> loadTransactions(String filePath) throws MoneyTrackerException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        return transactions;
    }

    /**
     * Saves information of all <code>Transaction</code> objects to text file.
     *
     * @throws MoneyTrackerException If text file is not found or inaccessible.
     */
    public void saveTransactions(TransactionList transactions) throws MoneyTrackerException {
        FileWriter fw;
        try {
            fw = new FileWriter(filePath);
            for (int i = 0; i < transactions.getSize(); i++) {
                String description = transactions.getTransaction(i).getDescription();
                String amount = transactions.getTransaction(i).getAmount();
                String date = transactions.getTransaction(i).getDate();
                String lineToSave = " | " + amount + " | " + date + " | " + description;
                if (transactions.getTransaction(i) instanceof Income) {
                    String incomeCategory = ((Income) transactions.getTransaction(i)).getIncomeCategory();
                    lineToSave =  "I" + lineToSave + " | " + incomeCategory + System.lineSeparator();
                } else if (transactions.getTransaction(i) instanceof Expense) {
                    String expenseCategory = ((Expense) transactions.getTransaction(i)).getExpenseCategory();
                    lineToSave =  "E" + lineToSave + " | " + expenseCategory + System.lineSeparator();
                } else {
                    throw new MoneyTrackerException("I've problem saving to the file.");
                }
                fw.write(lineToSave);
            }
            fw.close();
        } catch (IOException e) {
            throw new MoneyTrackerException("I've problem saving to the file.");
        }
    }
}