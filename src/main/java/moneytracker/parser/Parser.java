package moneytracker.parser;

import moneytracker.command.Command;
import moneytracker.command.AddExpenseCategoryCommand;
import moneytracker.command.AddIncomeCategoryCommand;
import moneytracker.command.HelpCommand;
import moneytracker.command.ListCategoryCommand;
import moneytracker.command.DeleteCategoryCommand;
import moneytracker.command.EditCategoryCommand;
import moneytracker.command.AddIncomeCommand;
import moneytracker.command.AddExpenseCommand;
import moneytracker.command.ListCommand;
import moneytracker.command.EditCommand;
import moneytracker.command.DeleteCommand;
import moneytracker.command.ClearCommand;
import moneytracker.command.ReportCommand;
import moneytracker.command.BudgetCommand;
import moneytracker.command.ExitCommand;
import moneytracker.command.UnknownCommand;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.TransactionList;
import moneytracker.transaction.Transaction;
import moneytracker.transaction.Income;
import moneytracker.transaction.Expense;
import moneytracker.transaction.Category;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Comparator;
import java.util.LinkedHashMap;


/**
 * Contains methods that deal with parsing user commands to extract meaningful details from them.
 */
public class Parser {

    /**
     * Gets the command word from user's input string.
     * @param fullCommand User's full input string.
     * @return Command word from user's input string.
     */
    public static String getCommand(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    /**
     * Creates an income <code>Category</code> object.
     *
     * @param fullCommand User's full input string.
     * @return <code>Category</code> object.
     * @throws MoneyTrackerException If income category is missing.
     */
    public static Category createIncomeCategory(String fullCommand) throws MoneyTrackerException {
        assert !fullCommand.isBlank() : "fullCommand should not be blank";
        String name = fullCommand.replaceFirst("(?i)addcati", "").toUpperCase().trim();
        if (name.isEmpty()) {
            throw new MoneyTrackerException("The income category name is missing.");
        }
        return new Category(name, "INCOME");
    }

    /**
     * Creates an expense <code>Category</code> object.
     *
     * @param fullCommand User's full input string.
     * @return <code>Category</code> object.
     * @throws MoneyTrackerException If expense category is missing.
     */
    public static Category createExpenseCategory(String fullCommand) throws MoneyTrackerException {
        assert !fullCommand.isBlank() : "fullCommand should not be blank";
        String name = fullCommand.replaceFirst("(?i)addcate", "").toUpperCase().trim();
        if (name.isEmpty()) {
            throw new MoneyTrackerException("The expense category name is missing.");
        }
        return new Category(name, "EXPENSE");
    }

    /**
     * Creates an <code>Income</code> object.
     *
     * @param fullCommand User's full input string.
     * @return <code>Income</code> object.
     * @throws MoneyTrackerException If amount or income category is missing.
     */
    public static Income createIncome(String fullCommand) throws MoneyTrackerException {
        assert !fullCommand.isBlank() : "fullCommand should not be blank";
        String commandParameterString = fullCommand.replaceFirst("(?i)addi", "").trim();
        if (commandParameterString.isEmpty()) {
            throw new MoneyTrackerException("The parameters of the command are missing.");
        }
        HashMap<String, String> commandParams = getCommandParams(commandParameterString);
        if (!(commandParams.containsKey("amount"))) {
            throw new MoneyTrackerException("The amount parameter is missing.");
        }
        if (!(commandParams.containsKey("category"))) {
            throw new MoneyTrackerException("The income category parameter is missing.");
        }
        if (!(commandParams.containsKey("description"))) {
            commandParams.put("description", "");
        }
        double amount;
        try {
            amount = Double.parseDouble(commandParams.get("amount"));
        } catch (NumberFormatException e) {
            throw new MoneyTrackerException("The amount must be a decimal value. E.g. 30.50");
        }
        if (amount < 0) {
            throw new MoneyTrackerException("Amount should not be a negative number.");
        }
        String description = commandParams.get("description");
        String date = commandParams.get("date");
        String incomeCategory = commandParams.get("category");
        if (commandParams.containsKey("date")) {
            return new Income(amount, description, date, incomeCategory);
        } else {
            return new Income(amount, description, incomeCategory);
        }
    }

    /**
     * Creates an <code>Expense</code> object.
     *
     * @param fullCommand User's full input string.
     * @return <code>Expense</code> object.
     * @throws MoneyTrackerException If amount or expense category is missing.
     */
    public static Expense createExpense(String fullCommand) throws MoneyTrackerException {
        assert !fullCommand.isBlank() : "fullCommand should not be blank";
        String commandParameterString = fullCommand.replaceFirst("(?i)adde", "").trim();
        if (commandParameterString.isEmpty()) {
            throw new MoneyTrackerException("The parameters of the command are missing.");
        }
        HashMap<String, String> commandParams = getCommandParams(commandParameterString);
        if (!(commandParams.containsKey("amount"))) {
            throw new MoneyTrackerException("The amount parameter is missing.");
        }
        if (!(commandParams.containsKey("category"))) {
            throw new MoneyTrackerException("The expense category parameter is missing.");
        }
        if (!(commandParams.containsKey("description"))) {
            commandParams.put("description", "");
        }
        double amount;
        try {
            amount = Double.parseDouble(commandParams.get("amount"));
        } catch (NumberFormatException e) {
            throw new MoneyTrackerException("The amount must be a decimal value. E.g. 30.50");
        }
        if (amount < 0) {
            throw new MoneyTrackerException("Amount should not be a negative number.");
        }
        String description = commandParams.get("description");
        String date = commandParams.get("date");
        String expenseCategory = commandParams.get("category");
        if (commandParams.containsKey("date")) {
            return new Expense(amount, description, date, expenseCategory);
        } else {
            return new Expense(amount, description, expenseCategory);
        }
    }

    /**
     * Gets parameters for editing transaction from user's full input string.
     *
     * @param fullCommand User's full input string.
     * @return Parameters for editing transaction.
     * @throws MoneyTrackerException If command parameters are missing.
     */
    public static HashMap<String, String> getEditTransactionParams(String fullCommand) throws MoneyTrackerException {
        String commandParameterString = fullCommand.replaceFirst("(?i)edit", "").trim();
        if (commandParameterString.isEmpty()) {
            throw new MoneyTrackerException("The command parameters are missing.");
        }
        HashMap<String, String> editParameters;
        try {
            editParameters = getCommandParams(commandParameterString.split(" ", 2)[1].trim());
            editParameters.put("index", commandParameterString.split(" ", 2)[0].trim());
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MoneyTrackerException("The command parameters are invalid.");
        }
        return editParameters;
    }

    /**
     * Gets transaction/category index from user's full input string.
     *
     * @param fullCommand User's full input string.
     * @return Index of transaction/category.
     * @throws MoneyTrackerException If index is missing or invalid.
     */
    public static int getDeleteIndex(String fullCommand) throws MoneyTrackerException {
        String commandParameterString =
                fullCommand.replaceFirst("(?i)deletecat", "")
                        .replaceFirst("(?i)delete", "").trim();
        if (commandParameterString.isEmpty()) {
            throw new MoneyTrackerException("The index is missing.");
        }
        int index;
        try {
            index = Integer.parseInt(commandParameterString);
        } catch (NumberFormatException e) {
            throw new MoneyTrackerException("The index is invalid.");
        }
        return index - 1;
    }

    /**
     * Gets parameters for editing category from user's full input string.
     *
     * @param fullCommand User's full input string.
     * @return Parameters for editing category.
     * @throws MoneyTrackerException If index is missing or invalid.
     */
    public static String[] getEditCategoryParams(String fullCommand) throws MoneyTrackerException {
        String commandParameterString = fullCommand.replaceFirst("(?i)editcat", "").trim();
        if (commandParameterString.isEmpty()) {
            throw new MoneyTrackerException("The index is missing.");
        }
        String[] editParameters = commandParameterString.split("/n");
        for (int i = 0; i < editParameters.length; i++) {
            editParameters[i] = editParameters[i].trim();
        }
        return editParameters;
    }

    public static double getBudgetAmount(String fullCommand) throws MoneyTrackerException {
        String commandParameterString = fullCommand.replaceFirst("(?i)budget", "").trim();
        if (commandParameterString.isEmpty()) {
            throw new MoneyTrackerException("The amount is missing.");
        }
        double amount;
        try {
            amount = Double.parseDouble(commandParameterString);
        } catch (NumberFormatException e) {
            throw new MoneyTrackerException("Amount should be a number. E.g. 3000.00");
        }
        if (amount < 0) {
            throw new MoneyTrackerException("Amount should not be a negative number.");
        }
        return amount;
    }

    /**
     * Gets <code>Command</code> object from user's full input string.
     *
     * @param fullCommand User's full input string.
     * @return <code>Command</code> object.
     */
    public static Command parse(String fullCommand) {
        String command = getCommand(fullCommand).toLowerCase();
        switch (command) {
        case "help":
            return new HelpCommand();
        case "addcati":
            return new AddIncomeCategoryCommand(fullCommand);
        case "addcate":
            return new AddExpenseCategoryCommand(fullCommand);
        case "listcat":
            return new ListCategoryCommand(fullCommand);
        case "deletecat":
            return new DeleteCategoryCommand(fullCommand);
        case "editcat":
            return new EditCategoryCommand(fullCommand);
        case "addi":
            return new AddIncomeCommand(fullCommand);
        case "adde":
            return new AddExpenseCommand(fullCommand);
        case "list":
            return new ListCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
        case "edit":
            return new EditCommand(fullCommand);
        case "clear":
            return new ClearCommand();
        case "report":
            return new ReportCommand(fullCommand);
        case "budget":
            return new BudgetCommand(fullCommand);
        case "exit":
            return new ExitCommand();
        default:
            return new UnknownCommand();
        }
    }

    private static HashMap<String, String> getCommandParams(String commandParameterString) {
        assert !commandParameterString.isBlank() : "commandParameterString should not be blank";
        HashMap<String, String> commandParamsMap = new HashMap<>();
        String[] commandParamsArray = commandParameterString.split("/");
        for (String commandParam : commandParamsArray) {
            if (commandParam.startsWith("a")) {
                commandParamsMap.put("amount", commandParam.substring(1).trim());
            } else if (commandParam.startsWith("c")) {
                commandParamsMap.put("category", commandParam.substring(1).toUpperCase().trim());
            } else if (commandParam.startsWith("d")) {
                commandParamsMap.put("date", commandParam.substring(1).trim());
            } else if (commandParam.startsWith("e")) {
                commandParamsMap.put("description", commandParam.substring(1).trim());
            }
        }
        return commandParamsMap;
    }

    /**
     * Gets date from user's full input string.
     *
     * @param fullCommand User's full input string.
     * @return date from user's input string.
     * @throws MoneyTrackerException if date format incorrect.
     */
    public static String getDate(String fullCommand) throws MoneyTrackerException {
        assert !fullCommand.isBlank() : "fullCommand should not be blank";
        String date = fullCommand.replaceFirst("report", "").trim();
        if (date.isEmpty()) {
            throw new MoneyTrackerException("Date should be in yyyy-MM format. E.g. 2020-09");
        } else {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
            dateFormat.setLenient(false);
            try {
                dateFormat.parse(date);
            } catch (ParseException e) {
                throw new MoneyTrackerException("Date should be in yyyy-MM format. E.g. 2020-09");
            }
        }
        return date;
    }

    /**
     * Gets last six months name from system date.
     *
     * @return last six months name list.
     */
    public static List<String> getLastSixMon() {
        List<String> months = new ArrayList<>();
        for (int i = 5; i >= 0; i--) {
            months.add(LocalDate.now().minusMonths(i).format(DateTimeFormatter.ofPattern("yyyy-MM")));
        }
        return months;
    }

    /**
     * Gets days of month from user full command string input.
     *
     * @param date date month of <code>Transaction</code> objects.
     * @return days of month
     */
    public static int getDaysOfMonth(String date) {
        int year = Integer.parseInt(date.split("-")[0]) - 1900;
        int month = Integer.parseInt(date.split("-")[1]) - 1;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        return calendar.getActualMaximum(Calendar.DATE);
    }

    /**
     * Gets total month income transactions from user's full input string.
     *
     * @param transactions transactions List of <code>Transaction</code> objects.
     * @param date date date month of <code>Transaction</code> objects.
     * @return total month income of transactions
     */
    public static double getTotalIncome(TransactionList transactions, String date) {
        double totalIncome = 0.00;

        for (int i = 0; i < transactions.getSize(); i++) {
            Transaction temp = transactions.getTransaction(i);
            double amount = Double.parseDouble(transactions.getTransaction(i).getAmount());
            if (temp instanceof Income & transactions.getTransaction(i).setMonth().equals(date)) {
                totalIncome = totalIncome + amount;
            }
        }
        return round(totalIncome,2);
    }

    /**
     * Gets total month expense transactions from user's full input string.
     *
     * @param transactions transactions List of <code>Transaction</code> objects.
     * @param date date date month of <code>Transaction</code> objects.
     * @return total month expense of transactions
     */
    public static double getTotalExpense(TransactionList transactions, String date) {
        double totalExpense = 0.00;

        for (int i = 0; i < transactions.getSize(); i++) {
            Transaction temp = transactions.getTransaction(i);
            double amount = Double.parseDouble(transactions.getTransaction(i).getAmount());
            if (temp instanceof Expense & transactions.getTransaction(i).setMonth().equals(date)) {
                totalExpense = totalExpense + amount;
            }
        }
        return round(totalExpense,2);
    }

    /**
     * Gets round double to 2 decimal places.
     *
     * @param value double
     * @param places decimal places
     * @return double
     */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    /**
     * Gets highest month income transactions from user's full input string.
     *
     * @param transactions transactions List of <code>Transaction</code> objects.
     * @param date date date month of <code>Transaction</code> objects.
     * @return highest month income of transactions
     */
    public static String getHighestIncome(TransactionList transactions, String date) {
        double highestIncome = 0.00;
        String highestIncomes = null;

        for (int i = 0; i < transactions.getSize(); i++) {
            Transaction temp = transactions.getTransaction(i);
            double amount = Double.parseDouble(transactions.getTransaction(i).getAmount());
            if (temp instanceof Income & transactions.getTransaction(i).setMonth().equals(date)) {
                if (highestIncome < amount) {
                    highestIncomes = temp.toString();
                    highestIncome = amount;
                }
            }
        }

        if (highestIncomes == null || highestIncomes.isEmpty()) {
            return ("Sorry, Cannot find any Income record in this Month.");
        } else {
            return highestIncomes;
        }
    }

    /**
     * Gets highest month expense transactions from user's full input string.
     *
     * @param transactions transactions List of <code>Transaction</code> objects.
     * @param date date date month of <code>Transaction</code> objects.
     * @return highest month expense of transactions
     */
    public static String getHighestExpense(TransactionList transactions, String date) {
        double highestExpense = 0.00;
        String highestExpenses = null;

        for (int i = 0; i < transactions.getSize(); i++) {
            Transaction temp = transactions.getTransaction(i);
            double amount = Double.parseDouble(transactions.getTransaction(i).getAmount());
            if (temp instanceof Expense & transactions.getTransaction(i).setMonth().equals(date)) {
                if (highestExpense < amount) {
                    highestExpenses = temp.toString();
                    highestExpense = amount;
                }
            }
        }

        if (highestExpenses == null || highestExpenses.isEmpty()) {
            return ("Sorry, Cannot find any Expense record in this Month.");
        } else {
            return highestExpenses;
        }
    }

    /**
     * Gets frequent month income category from user's full input string.
     *
     * @param transactions transactions List of <code>Transaction</code> objects.
     * @param date date date month of <code>Transaction</code> objects.
     *
     */
    public static void getInCatFreq(TransactionList transactions, String date) {
        ArrayList<String> incomeCate = new ArrayList<>();

        for (int i = 0; i < transactions.getSize(); i++) {
            Transaction temp = transactions.getTransaction(i);
            if (temp instanceof Income & transactions.getTransaction(i).setMonth().equals(date)) {
                incomeCate.add(temp.getTypeName(temp.toString()));
            }
        }

        if (incomeCate.isEmpty()) {
            System.out.println("  Sorry, Cannot find any Income Category record in this Month.");
        } else {
            printInHelper(getFrequency(incomeCate));
        }
    }

    /**
     * Gets frequent month expense category from user's full input string.
     *
     * @param transactions transactions List of <code>Transaction</code> objects.
     * @param date date date month of <code>Transaction</code> objects.
     */
    public static void getExpCatFreq(TransactionList transactions, String date) {
        ArrayList<String> expCate = new ArrayList<>();

        for (int i = 0; i < transactions.getSize(); i++) {
            Transaction temp = transactions.getTransaction(i);
            if (temp instanceof Expense & transactions.getTransaction(i).setMonth().equals(date)) {
                expCate.add(temp.getTypeName(temp.toString()));
            }
        }

        if (expCate.isEmpty()) {
            System.out.println("  Sorry, Cannot find any Expense Category record in this Month.");
        } else {
            printExpHelper(getFrequency(expCate));
        }
    }

    /**
     * Gets list elements frequency with descending order.
     *
     * @param list arraylist
     * @return map object with list elements and frequency.
     */
    public static Map<String, Long> getFrequency(ArrayList<String> list) {
        Map<String, Long> unsortMap = list.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));
        return new TreeMap<>(unsortMap);
    }

    /**
     * Help slip frequency string to words.
     *
     * @param object income/expense category frequency.
     */
    public static void printInHelper(Object object) {
        String line = object.toString();
        String newline = line.replace("{","")
                .replace("}","").replace(",","");
        for (String word : newline.split(" ")) {
            word = word.replace("="," $");
            System.out.println("  [I] " + word);
        }
    }

    /**
     * Help slip frequency string to words.
     *
     * @param object income/expense category frequency.
     */
    public static void printExpHelper(Object object) {
        String line = object.toString();
        String newline = line.replace("{","")
                .replace("}","").replace(",","");
        for (String word : newline.split(" ")) {
            word = word.replace("="," $");
            System.out.println("  [E] " + word);
        }
    }

    /**
     * Get income category list elements amount with descending order.
     *
     * @param transactions transactions List of <code>Transaction</code> objects.
     * @param date date date month of <code>Transaction</code> objects.
     */
    public static void getInCatAmount(TransactionList transactions, String date) {
        Map<String, Double> incomeCate = new HashMap<>();
        double sum = 0;

        for (int i = 0; i < transactions.getSize(); i++) {
            Transaction temp = transactions.getTransaction(i);
            double amount = Double.parseDouble(transactions.getTransaction(i).getAmount());
            if (temp instanceof Income & transactions.getTransaction(i).setMonth().equals(date)) {
                incomeCate.merge(temp.getTypeName(temp.toString()), sum + amount, Double::sum);
            }
        }
        if (incomeCate.isEmpty()) {
            System.out.println("  Sorry, Cannot find any Income Category record in this Month.");
        } else {
            printInHelper(sortHelper(incomeCate));
        }
    }

    /**
     * Get expense list elements amount with descending order.
     *
     * @param transactions transactions List of <code>Transaction</code> objects.
     * @param date date date month of <code>Transaction</code> objects.
     */
    public static void getExpCatAmount(TransactionList transactions, String date) {
        Map<String, Double> expCate = new HashMap<>();
        double sum = 0;

        for (int i = 0; i < transactions.getSize(); i++) {
            Transaction temp = transactions.getTransaction(i);
            double amount = Double.parseDouble(transactions.getTransaction(i).getAmount());
            if (temp instanceof Expense & transactions.getTransaction(i).setMonth().equals(date)) {
                expCate.merge(temp.getTypeName(temp.toString()), sum + amount, Double::sum);
            }
        }

        if (expCate.isEmpty()) {
            System.out.println("  Sorry, Cannot find any Expense Category record in this Month.");
        } else {
            printExpHelper(sortHelper(expCate));
        }
    }

    /**
     * Help sort income/expense category Map object.
     *
     * @param object unsort income/expense category Map object.
     * @return sorted object Map
     */
    public static Object sortHelper(Map<String, Double> object) {
        return object.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                    (oldValue, newValue) -> oldValue, LinkedHashMap::new));
    }

    /**
     * Gets last six months income transactions with descent order.
     *
     * @param transactions transactions transactions List of <code>Transaction</code> objects.
     * @param dates last six months list
     * @return sorted object list
     */
    public static Object getSixMonIncome(TransactionList transactions, List<String> dates) {
        Map<String, Double> monthInAmount = new HashMap<>();

        for (String date:dates) {
            monthInAmount.put(date,Parser.getTotalIncome(transactions, date));
        }
        return Parser.sortHelper(monthInAmount);
    }

    /**
     * Gets last six months expense transactions with descent order.
     *
     * @param transactions transactions transactions List of <code>Transaction</code> objects.
     * @param dates last six months list
     * @return sorted object list
     */
    public static Object getSixMonExpense(TransactionList transactions, List<String> dates) {
        Map<String, Double> monthExpAmount = new HashMap<>();

        for (String date:dates) {
            monthExpAmount.put(date,Parser.getTotalExpense(transactions, date));
        }
        return Parser.sortHelper(monthExpAmount);
    }
}