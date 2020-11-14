package seedu.itlogger;

// Jian Cheng

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 * This Parser Class handles the String input.
 * It will parse the string into respective output in
 * correct format and type.
 */
public class Parser {


    private static final Logger logger = Logger.getLogger(IssueList.class.getName());


    public Parser() {
        try {
            // Remove the default console handler
            Logger parentLogger = Logger.getLogger("");
            Handler[] handlers = parentLogger.getHandlers();
            for (Handler handler : handlers) {
                parentLogger.removeHandler(handler);
            }

            Handler fh = new FileHandler("Parser.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setLevel(Level.FINE);
            logger.info("Created Parser Object successfully");
        } catch (IOException e) {
            System.out.println("Issue in creating Log file");
        }
    }

    /**
     * parseKeyWord method will parse the fullInput
     * and get KeyWord from fullInput.
     * @param fullInput -> user's full command
     * @return keyword provided by user and in KeyWord enum
     */

    public static KeyWord parseKeyWord(String fullInput) throws IllegalArgumentException {
        logger.info("parsing keyword from user input...");
        String possibleKeyWord;
        if (fullInput.contains(" ")) {
            possibleKeyWord = fullInput.split(" ")[0];
        } else {
            possibleKeyWord = fullInput;
        }
        assert possibleKeyWord != null : "Empty command found from input";
        return KeyWord.valueOf(possibleKeyWord);
    }

    /**
     * parseInputElement method will parse the input given
     * and return the element as String that is between startNotation
     * and endNotation.
     * @param input -> input String to parse
     * @param startNotation -> notation to start the element
     * @param endNotation -> notation to end the element
     * @return element that is between start and end notation as String type
     */
    protected static String parseInputElement(String input, String startNotation, String endNotation) {
        logger.info("parsing element from index " + startNotation + " to " + endNotation + " in user input...");
        int elementStartIndex = input.indexOf(startNotation) + startNotation.length();
        int elementEndIndex = input.indexOf(endNotation);
        String parsedResult = input.substring(elementStartIndex,elementEndIndex);
        assert parsedResult != "" : "Issue in parsing command";
        return parsedResult;
    }

    /**
     * parseTitle method will parse the fullInput
     * and return Title of Defect for ADD Command.
     * @param fullInput -> user's full command
     * @return Defect title provided by user and in String type
     */
    public static String parseTitle(String fullInput) {
        logger.info("parsing title from user input...");
        String titleStartNotation = "t/";
        String titleEndNotation = " s/";
        return parseInputElement(fullInput,titleStartNotation,titleEndNotation);
    }

    /**
     * parseStatus method will parse the fullInput
     * and return Status of Defect for ADD Command.
     * @param fullInput -> user's full command
     * @return Defect status provided by user and in String type
     */
    public static String parseStatus(String fullInput) {
        logger.info("parsing status from user input...");
        String statusStartNotation = "s/";
        String statusEndNotation = " sv/";
        return parseInputElement(fullInput,statusStartNotation,statusEndNotation);
    }

    /**
     * parseSeverity method will parse the fullInput
     * and return Severity of Defect for ADD Command.
     * @param fullInput -> user's full command
     * @return Defect severity provided by user and in int type
     */
    public static int parseSeverity(String fullInput) throws ParseException {
        logger.info("parsing severity from user input...");
        String severityStartNotation = "sv/";
        String severityEndNotation = " dl/";
        String severity = parseInputElement(fullInput,severityStartNotation,severityEndNotation);
        int parsedResult;
        try {
            parsedResult = Integer.parseInt(severity);
        } catch (NumberFormatException e) {
            throw new ParseException("Severity should be integer from 0 to 10",0);
        }
        assert parsedResult <= 10 && parsedResult >= 0 : "Severity should be integer from 0 to 10";
        return parsedResult;
    }

    /**
     * parseDeadline method will parse the fullInput
     * and return Deadline of Defect for ADD Command.
     * @param fullInput -> user's full command
     * @return Defect deadline provided by user and in Date type
     */
    public static Date parseDeadline(String fullInput) throws ParseException {
        logger.info("parsing deadline from user input...");
        String deadlineStartNotation = "dl/";
        String deadlineEndNotation = " o/";
        String deadline = parseInputElement(fullInput,deadlineStartNotation, deadlineEndNotation);
        return new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(deadline);
    }

    /**
     * parseOwner method will parse the fullInput
     * and return Owner of Defect for ADD Command.
     * @param fullInput -> user's full command
     * @return Defect owner provided by user and in String type
     */
    public static String parseOwner(String fullInput) {
        logger.info("parsing owner from user input...");
        int ownerStartIndex = fullInput.indexOf("o/") + 2;
        String parsedResult = fullInput.substring(ownerStartIndex);
        assert parsedResult != "" : "Issue in parsing command";
        return parsedResult;
    }

    /**
     * parseIndex method will parse the fullInput and return
     * user indicated index for VIEW and DELETE Command.
     * @param fullInput -> user's full command
     * @return Defect index provided by user and in int type
     */
    public static int parseIndex(String fullInput, int size) {
        logger.info("parsing index from user input...");
        String possibleIndex = fullInput.split("/")[1];
        int parsedResult = Integer.parseInt(possibleIndex);
        assert parsedResult <= size && parsedResult >= 0 : "Index should be integer from 0 to " + size;
        return parsedResult;
    }

    public static int parseListType(String fullInput) throws ParseException {
        logger.info("parsing view type from user input...");
        String possibleListType;
        String possibleOrder;
        try {
            possibleListType = fullInput.split("/")[0].substring(5);
            possibleOrder = fullInput.split("/")[1];
        } catch (Exception e) {
            throw new ParseException("Command has error.Please check.",1);
        }
        switch (possibleListType) {
        case "sv":
            if (possibleOrder.equals("d")) {
                return 2;
            } else {
                return 1;
            }
        case "dl":
            if (possibleOrder.equals("d")) {
                return 4;
            } else {
                return 3;
            }
        case "dr":
            if (possibleOrder.equals("d")) {
                return 6;
            } else {
                return 5;
            }
        default:
            return 0;
        }
    }

    public static String parseSearchType(String fullInput) {
        logger.info("parsing search type from user input...");
        String possibleSearchType = fullInput.split("/")[1];
        return possibleSearchType.toUpperCase().trim();
    }

    public static String parseSearchTerm(String fullInput) {
        logger.info("parsing search term from user input...");
        String possibleSearchTerm = fullInput.split("/")[2];
        return possibleSearchTerm;
    }

    public static boolean parseUpdateContent(String fullInput,Defect defect) throws ParseException {
        logger.info("parsing UpdateContent from user input...");
        String cmd = "";
        String content = "";

        try {
            cmd = fullInput.split("/")[0].substring(7);
            content = fullInput.split("/")[1];
        } catch (Exception e) {
            throw new ParseException("Command has error.Please check.",1);
        }
        switch (cmd) {
        case "t":
            defect.setTitle(content);
            return true;
        case "s":
            defect.setStatus(content);
            return true;
        case "sv":
            defect.setSeverity(Integer.parseInt(content));
            return true;
        case "dl":
            defect.setDeadline(new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(content));
            return true;
        case "o":
            defect.setOwner(content);
            return true;
        case "e":
            return false;
        default:
            return true;
        }
    }

}
