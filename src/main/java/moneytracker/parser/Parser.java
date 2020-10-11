package moneytracker.parser;

import moneytracker.command.Command;
import moneytracker.command.AddExpenseCategoryCommand;
import moneytracker.command.AddIncomeCategoryCommand;
import moneytracker.command.HelpCommand;
import moneytracker.command.ListCategoryCommand;
import moneytracker.command.DeleteCategoryCommand;
import moneytracker.command.AddIncomeCommand;
import moneytracker.command.AddExpenseCommand;
import moneytracker.command.ListCommand;
import moneytracker.command.DeleteCommand;
import moneytracker.command.ClearCommand;
import moneytracker.command.ReportCommand;
import moneytracker.command.ExitCommand;
import moneytracker.command.UnknownCommand;
import moneytracker.exception.MoneyTrackerException;
import moneytracker.transaction.Income;
import moneytracker.transaction.Expense;

import java.util.HashMap;

public class Parser {
    /**
     * Gets the command word from user's input string.
     * @param fullCommand User's full input string.
     * @return Command word from user's input string.
     */
    public static String getCommand(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    public static String getExpenseCategory(String fullCommand) throws MoneyTrackerException {
        String commandParameterString = fullCommand.replaceFirst("(?i)addcate", "").trim();
        if (commandParameterString.isEmpty()) {
            throw new MoneyTrackerException("The expense category parameter is missing.");
        }
        return commandParameterString;
    }

    public static String getIncomeCategory(String fullCommand) throws MoneyTrackerException {
        String commandParameterString = fullCommand.replaceFirst("(?i)addcati", "").trim();
        if (commandParameterString.isEmpty()) {
            throw new MoneyTrackerException("The income category parameter is missing.");
        }
        return commandParameterString;
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
        HashMap<String, String> commandParameters = getCommandParameters(commandParameterString);
        if (!(commandParameters.containsKey("amount"))) {
            throw new MoneyTrackerException("The amount parameter is missing.");
        }
        if (!(commandParameters.containsKey("category"))) {
            throw new MoneyTrackerException("The income category parameter is missing.");
        }
        String amount = commandParameters.get("amount");
        String description = commandParameters.get("description");
        String date = commandParameters.get("date");
        String incomeCategory = commandParameters.get("category");
        try {
            if (commandParameters.containsKey("date")) {
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
        HashMap<String, String> commandParameters = getCommandParameters(commandParameterString);
        if (!(commandParameters.containsKey("amount"))) {
            throw new MoneyTrackerException("The amount parameter is missing.");
        }
        if (!(commandParameters.containsKey("category"))) {
            throw new MoneyTrackerException("The expense category parameter is missing.");
        }
        String amount = commandParameters.get("amount");
        String description = commandParameters.get("description");
        String date = commandParameters.get("date");
        String expenseCategory = commandParameters.get("category");
        try {
            if (commandParameters.containsKey("date")) {
                return new Expense(Double.parseDouble(amount), description, date, expenseCategory);
            } else {
                return new Expense(Double.parseDouble(amount), description, expenseCategory);
            }
        } catch (NumberFormatException e) {
            throw new MoneyTrackerException("The amount must be a decimal value. E.g. 30.50");
        }
    }

    /**
     * Gets transaction index from user's full input string.
     *
     * @param fullCommand User's full input string.
     * @return Transaction index.
     * @throws MoneyTrackerException If task index is missing or invalid.
     */
    public static int getTransactionIndex(String fullCommand) throws MoneyTrackerException {
        String commandParameterString = fullCommand.replaceFirst("(?i)delete", "").trim();
        if (commandParameterString.isEmpty()) {
            throw new MoneyTrackerException("The transaction index is missing.");
        }
        int transactionIndex;
        try {
            transactionIndex = Integer.parseInt(commandParameterString);
        } catch (NumberFormatException e) {
            throw new MoneyTrackerException("The transaction index is invalid.");
        }
        return transactionIndex - 1;
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
        case "addi":
            return new AddIncomeCommand(fullCommand);
        case "adde":
            return new AddExpenseCommand(fullCommand);
        case "list":
            return new ListCommand(fullCommand);
        case "delete":
            return new DeleteCommand(fullCommand);
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

    private static HashMap<String, String> getCommandParameters(String commandParameterString) {
        assert !commandParameterString.isBlank() : "commandParameterString should not be blank";
        HashMap<String, String> commandParametersMap = new HashMap<>();
        String[] commandParametersArray = commandParameterString.split("/");
        for (String commandParameter : commandParametersArray) {
            if (commandParameter.startsWith("a")) {
                commandParametersMap.put("amount", commandParameter.substring(1).trim());
            } else if (commandParameter.startsWith("c")) {
                commandParametersMap.put("category", commandParameter.substring(1).toUpperCase().trim());
            } else if (commandParameter.startsWith("d")) {
                commandParametersMap.put("date", commandParameter.substring(1).trim());
            } else if (commandParameter.startsWith("e")) {
                commandParametersMap.put("description", commandParameter.substring(1).trim());
            }
        }
        commandParametersMap.putIfAbsent("description", "");
        return commandParametersMap;
    }
}