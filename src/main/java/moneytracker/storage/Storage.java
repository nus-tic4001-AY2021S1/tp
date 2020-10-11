package moneytracker.storage;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Income;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

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

    /**
     * Saves information of all <code>Transaction</code> objects to text file.
     *
     * @throws MoneyTrackerException If the directory creation failed.
     */
    public ArrayList<Transaction> loadTransactions(String filePath) throws MoneyTrackerException {
        try {
            Path path = Paths.get("data/");
            if (!(Files.exists(path))) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            throw new MoneyTrackerException("I've problem creating the save directory!");
        }
        ArrayList<Transaction> transactions = new ArrayList<>();
        ArrayList<String> lines;
        lines = getLines(filePath);
        for (String line : lines) {
            if (!(line.trim().isEmpty())) {
                transactions.add(createTransaction(line));
            }
        }
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

    private ArrayList<String> getLines(String filePath) throws MoneyTrackerException {
        File f = new File(filePath);
        ArrayList<String> result = new ArrayList<>();
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                result.add(s.nextLine());
            }
        } catch (IOException e) {
            throw new MoneyTrackerException("I've problem reading the save file."
                    + " Let's start with an empty transaction list instead.");
        }
        return result;
    }

    private Transaction createTransaction(String line) throws MoneyTrackerException {
        String transactionType = line.split("\\|")[0].trim();
        String amount = line.split("\\|")[1].trim();
        String date = line.split("\\|")[2].trim();
        String description = line.split("\\|")[3].trim();
        String category = line.split("\\|")[4].trim();
        switch (transactionType) {
        case "I":
            return new Income(Double.parseDouble(amount), description, date, category);
        case "E":
            return new Expense(Double.parseDouble(amount), description, date, category);
        default:
            throw new MoneyTrackerException("There is invalid data in the save file.");
        }
    }
}