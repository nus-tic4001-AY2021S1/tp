package moneytracker.storage;

import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.TransactionList;
import moneytracker.transaction.IncomeCategoryList;
import moneytracker.transaction.ExpenseCategoryList;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.Income;
import moneytracker.transaction.Expense;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String transactionsFilePath;
    private final String incomeCategoriesFilePath;
    private final String expenseCategoriesFilePath;

    /**
     * Initializes a <code>Storage</code> object.
     *
     * @param transactionsFilePath Path of the text file used for storing transactions.
     * @param incomeCategoriesFilePath Path of the text file used for storing income categories.
     * @param expenseCategoriesFilePath Path of the text file used for storing expense categories.
     */
    public Storage(String transactionsFilePath, String incomeCategoriesFilePath, String expenseCategoriesFilePath) {
        this.transactionsFilePath = transactionsFilePath;
        this.incomeCategoriesFilePath = incomeCategoriesFilePath;
        this.expenseCategoriesFilePath = expenseCategoriesFilePath;
    }

    /**
     * Loads information of all <code>Transaction</code> objects from text file.
     */
    public ArrayList<Transaction> loadTransactions(String filePath) throws MoneyTrackerException {
        createDirectory("data/");
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
     * Loads information of all income categories from text file.
     */
    public ArrayList<String> loadIncomeCategories(String filePath) throws MoneyTrackerException {
        createDirectory("data/");
        ArrayList<String> incomeCategories = new ArrayList<>();
        ArrayList<String> lines = getLines(filePath);
        for (String line : lines) {
            if (!(line.trim().isEmpty())) {
                incomeCategories.add((line));
            }
        }
        return incomeCategories;
    }

    /**
     * Loads information of all expense categories from text file.
     */
    public ArrayList<String> loadExpenseCategories(String filePath) throws MoneyTrackerException {
        createDirectory("data/");
        ArrayList<String> expenseCategories = new ArrayList<>();
        ArrayList<String> lines = getLines(filePath);
        for (String line : lines) {
            if (!(line.trim().isEmpty())) {
                expenseCategories.add((line));
            }
        }
        return expenseCategories;
    }

    /**
     * Saves information of all <code>Transaction</code> objects to text file.
     *
     * @throws MoneyTrackerException If text file is not found or inaccessible.
     */
    public void saveTransactions(TransactionList transactions) throws MoneyTrackerException {
        FileWriter fw;
        try {
            fw = new FileWriter(transactionsFilePath);
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

    /**
     * Saves information of all income categories to text file.
     *
     * @throws MoneyTrackerException If text file is not found or inaccessible.
     */
    public void saveIncomeCategories(IncomeCategoryList incomeCategories) throws MoneyTrackerException {
        FileWriter fw;
        try {
            fw = new FileWriter(incomeCategoriesFilePath);
            for (int i = 0; i < incomeCategories.getSize(); i++) {
                fw.write(incomeCategories.getIncomeCategory(i) + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new MoneyTrackerException("I've problem saving to the file.");
        }
    }

    /**
     * Saves information of all expense categories to text file.
     *
     * @throws MoneyTrackerException If text file is not found or inaccessible.
     */
    public void saveExpenseCategories(ExpenseCategoryList expenseCategories) throws MoneyTrackerException {
        FileWriter fw;
        try {
            fw = new FileWriter(expenseCategoriesFilePath);
            for (int i = 0; i < expenseCategories.getSize(); i++) {
                fw.write(expenseCategories.getExpenseCategory(i)  + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new MoneyTrackerException("I've problem saving to the file.");
        }
    }

    public void clearAllData() throws MoneyTrackerException {
        clearDataInFile(transactionsFilePath);
        clearDataInFile(incomeCategoriesFilePath);
        clearDataInFile(expenseCategoriesFilePath);
    }

    private void clearDataInFile(String filePath) throws MoneyTrackerException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.close();
        } catch (IOException e) {
            throw new MoneyTrackerException("I've problem clearing data in this file: " + filePath);
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

    private void createDirectory(String directoryPath) throws MoneyTrackerException {
        try {
            Path path = Paths.get(directoryPath);
            if (!(Files.exists(path))) {
                Files.createDirectory(path);
            }
        } catch (IOException e) {
            throw new MoneyTrackerException("I've problem creating the save directory!");
        }
    }
}