package seedu.duke.utilities;

import seedu.duke.commands.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
//            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

            case AddCommand.COMMAND_WORD:
                return createAddCommand(arguments);

            case DeleteCommand.COMMAND_WORD:
                return createDeleteCommand(arguments);

            case SearchCommand.COMMAND_WORD:
                return  createSearchCommand(arguments);

            case TotalCommand.COMMAND_WORD:
                return createTotalCommand(arguments);

            case ViewCommand.COMMAND_WORD:
                return createViewCommand(arguments);

            case HelpCommand.COMMAND_WORD: // Fallthrough
            default:
                return new HelpCommand();
        }
    }
    private Command createAddCommand(String args){
        Command finalCommand;
        try{
            finalCommand = new AddCommand();
        }catch (Exception e){
            finalCommand = new IncorrectCommand();
        }
        return finalCommand;
    }

    private Command createDeleteCommand(String args){
        Command finalCommand;
        try{
            finalCommand = new DeleteCommand();
        }catch (Exception e){
            finalCommand = new IncorrectCommand();
        }
        return finalCommand;
    }

    private Command createSearchCommand(String args){
        Command finalCommand;
        try{
            finalCommand = new SearchCommand();
        }catch (Exception e){
            finalCommand = new IncorrectCommand();
        }
        return finalCommand;
    }

    private Command createTotalCommand(String args){
        Command finalCommand;
        try{
            finalCommand = new TotalCommand();
        }catch (Exception e){
            finalCommand = new IncorrectCommand();
        }
        return finalCommand;
    }

    private Command createViewCommand(String args){
        Command finalCommand;
        try{
            finalCommand = new ViewCommand();
        }catch (Exception e){
            finalCommand = new IncorrectCommand();
        }
        return finalCommand;
    }

}
