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
import moneytracker.command.ExitCommand;
import moneytracker.command.UnknownCommand;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Category;
import moneytracker.transaction.Income;
import moneytracker.transaction.Expense;

import java.util.HashMap;

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
        String amount = commandParams.get("amount");
        String description = commandParams.get("description");
        String date = commandParams.get("date");
        String incomeCategory = commandParams.get("category");
        try {
            if (commandParams.containsKey("date")) {
                return new Income(Double.parseDouble(amount), description, date, incomeCategory);
            } else {
                return new Income(Double.parseDouble(amount), description, incomeCategory);
            }
        } catch (NumberFormatException e) {
            throw new MoneyTrackerException("The amount must be a decimal value. E.g. 30.50");
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
        String amount = commandParams.get("amount");
        String description = commandParams.get("description");
        String date = commandParams.get("date");
        String expenseCategory = commandParams.get("category");
        try {
            if (commandParams.containsKey("date")) {
                return new Expense(Double.parseDouble(amount), description, date, expenseCategory);
            } else {
                return new Expense(Double.parseDouble(amount), description, expenseCategory);
            }
        } catch (NumberFormatException e) {
            throw new MoneyTrackerException("The amount must be a decimal value. E.g. 30.50");
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
}