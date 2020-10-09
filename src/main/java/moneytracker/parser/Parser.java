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

public class Parser {
    /**
     * Gets the command word from user's input string.
     * @param fullCommand User's full input string.
     * @return Command word from user's input string.
     */
    public static String getCommand(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    public static Income createIncome(String fullCommand) throws MoneyTrackerException {
        String commandParameterString = getCommandParameterString(fullCommand, "addi");
        if (commandParameterString.isEmpty()) {
            throw new MoneyTrackerException("The parameters of the command are missing.");
        }
        String amount = "";
        String incomeCategory = "";
        String date = "";
        String description = "";
        String[] commandParameters = commandParameterString.split("/");
        for (String parameter : commandParameters) {
            if (parameter.startsWith("a")) {
                amount = parameter.substring(1).trim();
            } else if (parameter.startsWith("c")) {
                incomeCategory = parameter.substring(1).trim();
            } else if (parameter.startsWith("d")) {
                date = parameter.substring(1).trim();
            } else if (parameter.startsWith("e")) {
                description = parameter.substring(1).trim();
            }
        }
        if (amount.isEmpty()) {
            throw new MoneyTrackerException("The amount cannot be empty.");
        }
        if (incomeCategory.isEmpty()) {
            throw new MoneyTrackerException("The income category cannot be empty.");
        }
        if (date.isEmpty()) {
            return new Income(Double.parseDouble(amount), description, incomeCategory);
        } else {
            return new Income(Double.parseDouble(amount), description, date, incomeCategory);
        }
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

    private static String getCommandParameterString(String fullCommand, String command) {
        return fullCommand.replaceFirst("(?i)" + command, "").trim();
    }
}