package moneytracker.parser;

import moneytracker.command.*;

public class Parser {
    /**
     * Gets the moneytracker.command word from user's input string.
     * @param fullCommand User's full input string.
     * @return Command word from user's input string.
     */
    public static String getCommand(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    /**
     * Gets <code>Command</code> object from user's full input string.
     *
     * @param fullCommand User's full input string.
     * @return <code>Command<Task></code> object.
     */
    public static Command parse(String fullCommand) {
        String command = getCommand(fullCommand).toLowerCase();
        switch(command) {
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
}
