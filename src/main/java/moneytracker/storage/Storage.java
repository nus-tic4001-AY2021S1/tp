package moneytracker.storage;

import moneytracker.summary.Budget;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Category;
import moneytracker.transaction.CategoryList;
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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Storage {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final String transactionsFilePath;
    private final String categoriesFilePath;
    private final String budgetFilePath;

    /**
     * Initializes a <code>Storage</code> object.
     *
     * @param transactionsFilePath Path of the text file used for storing transactions.
     * @param categoriesFilePath Path of the text file used for storing categories.
     */
    public Storage(String transactionsFilePath, String categoriesFilePath, String budgetFilePath) {
        this.transactionsFilePath = transactionsFilePath;
        this.categoriesFilePath = categoriesFilePath;
        this.budgetFilePath = budgetFilePath;
    }

    /**
     * Loads information of all <code>Transaction</code> objects from text file.
     */
    public ArrayList<Transaction> loadTransactions(String filePath) throws MoneyTrackerException {
        ArrayList<Transaction> transactions = new ArrayList<>();
        ArrayList<String> lines;
        try {
            lines = getLines(filePath);
        } catch (IOException e) {
            throw new MoneyTrackerException("I have problem reading the transactions save file."
                    + " Let's start with an empty transaction list instead.");
        }
        for (String line : lines) {
            if (!(line.trim().isEmpty())) {
                transactions.add(createTransaction(line));
            }
        }
        return transactions;
    }

    /**
     * Loads information of all categories from text file.
     */
    public ArrayList<Category> loadCategories(String filePath) throws MoneyTrackerException {
        ArrayList<Category> categories = new ArrayList<>();
        ArrayList<String> lines;
        try {
            lines = getLines(filePath);
        } catch (IOException e) {
            throw new MoneyTrackerException("I have problem reading the categories save file."
                    + " Let's start with an empty category list instead.");
        }
        for (String line : lines) {
            if (!(line.trim().isEmpty())) {
                categories.add(createCategory(line));
            }
        }
        return categories;
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
                    throw new MoneyTrackerException("I have problem saving to the file.");
                }
                fw.write(lineToSave);
            }
            fw.close();
        } catch (IOException e) {
            throw new MoneyTrackerException("I have problem saving to the file.");
        }
    }

    /**
     * Saves information of all categories to text file.
     *
     * @throws MoneyTrackerException If text file is not found or inaccessible.
     */
    public void saveCategories(CategoryList categories) throws MoneyTrackerException {
        FileWriter fw;
        try {
            fw = new FileWriter(categoriesFilePath);
            for (int i = 0; i < categories.getSize(); i++) {
                String name = categories.getCategory(i).getName();
                String type = categories.getCategory(i).getType();
                if (type.equals("INCOME")) {
                    fw.write("I" + " | " + name + System.lineSeparator());
                } else if (type.equals("EXPENSE")) {
                    fw.write("E" + " | " + name + System.lineSeparator());
                } else {
                    throw new MoneyTrackerException("I have problem saving to the file.");
                }
            }
            fw.close();
        } catch (IOException e) {
            throw new MoneyTrackerException("I have problem saving to the file.");
        }
    }

    /**
     * Saves information of <code>Budget</code> object to text file.
     *
     * @throws MoneyTrackerException If text file is not found or inaccessible.
     */
    public void saveBudget(Budget budget) throws MoneyTrackerException {
        FileWriter fw;
        try {
            fw = new FileWriter(budgetFilePath);
            fw.write(String.valueOf(budget.getAmount()));
            fw.close();
        } catch (IOException e) {
            throw new MoneyTrackerException("I have problem saving to the file.");
        }
    }

    /**
     * Loads information of <code>Budget</code> object from text file.
     *
     * @throws MoneyTrackerException If text file is not found or inaccessible.
     */
    public Double loadBudget(String filePath) throws MoneyTrackerException {
        ArrayList<String> lines;
        try {
            lines = getLines(filePath);
        } catch (IOException e) {
            throw new MoneyTrackerException("I have problem reading the budget save file.");
        }
        Double budget;
        try {
            budget = Double.valueOf(lines.get(0));
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new MoneyTrackerException("I have problem reading the budget save file.");
        }
        return budget;
    }

    /**
     * Clear information in all text files.
     *
     * @throws MoneyTrackerException If text file is not found or inaccessible.
     */
    public void clearAllData() throws MoneyTrackerException {
        clearDataInFile(transactionsFilePath);
        clearDataInFile(categoriesFilePath);
        clearDataInFile(budgetFilePath);
    }

    /**
     * Clear information in a specified text file.
     *
     * @throws MoneyTrackerException If text file is not found or inaccessible.
     */
    private void clearDataInFile(String filePath) throws MoneyTrackerException {
        try {
            FileWriter fw = new FileWriter(filePath);
            if (filePath.equals(budgetFilePath)) {
                fw.write("0.0");
            }
            fw.close();
        } catch (IOException e) {
            throw new MoneyTrackerException("I have problem clearing data in this file: " + filePath);
        }
    }

    private ArrayList<String> getLines(String filePath) throws IOException {
        File f = new File(filePath);
        ArrayList<String> result = new ArrayList<>();
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            result.add(s.nextLine());
        }
        return result;
    }

    private Category createCategory(String line) throws MoneyTrackerException {
        String type = "";
        String name = "";
        try {
            type = line.split("\\|")[0].trim();
            name = line.split("\\|")[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.SEVERE, "Error reading categories.txt", e);
            System.exit(1);
        }
        if (type.equals("I")) {
            return new Category(name, "INCOME");
        } else if (type.equals("E")) {
            return new Category(name, "EXPENSE");
        } else {
            throw new MoneyTrackerException("There is invalid data in the save file.");
        }
    }

    private Transaction createTransaction(String line) throws MoneyTrackerException {
        String transactionType = "";
        String amount = "";
        String date = "";
        String description = "";
        String category = "";
        try {
            transactionType = line.split("\\|")[0].trim();
            amount = line.split("\\|")[1].trim();
            date = line.split("\\|")[2].trim();
            description = line.split("\\|")[3].trim();
            category = line.split("\\|")[4].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.log(Level.SEVERE, "Error reading transactions.txt", e);
            System.exit(1);
        }
        switch (transactionType) {
        case "I":
            return new Income(Double.parseDouble(amount), description, date, category);
        case "E":
            return new Expense(Double.parseDouble(amount), description, date, category);
        default:
            throw new MoneyTrackerException("There is invalid data in the save file.");
        }
    }

    /**
     * Creates the directory for save files.
     *
     * @throws MoneyTrackerException If the creation of file directory fails.
     */
    public void createDirectory() throws MoneyTrackerException {
        try {
            Path path = Paths.get("data/");
            if (!(Files.exists(path))) {
                Files.createDirectory(path);
                clearAllData();
            }
        } catch (IOException e) {
            throw new MoneyTrackerException("I have problem creating the save directory!");
        }
    }
}