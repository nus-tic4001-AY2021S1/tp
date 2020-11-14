package seedu.itlogger;

import seedu.itlogger.exception.EmptyListException;
import seedu.itlogger.storage.StorageFile;
import java.io.IOException;
import java.text.ParseException;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static seedu.itlogger.InputHandler.getInput;
import static seedu.itlogger.InputHandler.hasNextLine;
import static seedu.itlogger.Interface.printFileToUser;
import static seedu.itlogger.Interface.printErrorMessageToUser;
import static seedu.itlogger.Interface.printLogo;
import static seedu.itlogger.Interface.programOpening;
import static seedu.itlogger.Interface.askName;
import static seedu.itlogger.Interface.greeter;
import static seedu.itlogger.Interface.keyWordIssue;
import static seedu.itlogger.Interface.emptyErrorMsg;
import static seedu.itlogger.Interface.displayIssues;
import static seedu.itlogger.Parser.parseDeadline;
import static seedu.itlogger.Parser.parseIndex;
import static seedu.itlogger.Parser.parseKeyWord;
import static seedu.itlogger.Parser.parseOwner;
import static seedu.itlogger.Parser.parseSearchTerm;
import static seedu.itlogger.Parser.parseSearchType;
import static seedu.itlogger.Parser.parseSeverity;
import static seedu.itlogger.Parser.parseStatus;
import static seedu.itlogger.Parser.parseTitle;
import static seedu.itlogger.Search.searchDeadline;
import static seedu.itlogger.Search.searchOwner;
import static seedu.itlogger.Search.searchSeverity;
import static seedu.itlogger.Search.searchStatus;
import static seedu.itlogger.Search.searchTitle;
import static seedu.itlogger.Parser.parseListType;
import static seedu.itlogger.SortList.sortList;

public class ItLogger {

    /**.
     * Logger for Main Class
     */

    private static final Logger logger = Logger.getLogger(ItLogger.class.getName());
    private static StorageFile storage = new StorageFile();



    /**
     * Main entry-point for the java.duke.Duke application.
     */

    public static void main(String[] args) throws IOException {

        // Logger setup:
        try {
            // Remove the default console handler
            Logger parentLogger = Logger.getLogger("");
            Handler[] handlers = parentLogger.getHandlers();
            for (Handler handler : handlers) {
                parentLogger.removeHandler(handler);
            }

            Handler fh = new FileHandler("ItLogger.log", true);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);
            logger.setLevel(Level.FINE);
        } catch (IOException e) {
            System.out.println("Issue creating Log file");
        }

        // Instantiating InputHandler only for logging purpose
        InputHandler inputHandler = new InputHandler();

        // Program starting point:

        IssueList issueList = new IssueList();
        assert issueList != null : "issueList should have been created";
        try {
            storage.load(issueList);
        } catch (StorageFile.StorageOperationException e) {
            Interface.printErrorMessageToUser(e);
        } //catch (FileNotFoundException e) {
        //System.out.println("File not found. No Tasks preloaded.");
        //}
        logger.info("Creating ITLogger issue list...");

        printLogo();
        askName();
        String userName = "";
        if (hasNextLine()) {
            logger.info("Getting username from user...");
            userName = getInput();
            logger.fine("completed the obtaining of username...");
            assert !userName.equals("") : "username should have been captured. Should not be empty";
            assert !userName.equals(null)  : "username should have been captured. Should not be null";
        }
        greeter(userName);
        boolean keepRun = true;
        while (keepRun) {
            logger.info("Beginning program run...");
            programOpening();
            String input = "";
            if (hasNextLine()) {
                logger.info("getting instruction for program...");
                input = getInput();
                logger.info("finished getting instruction for program...");
                assert !input.equals("") : "input should have been captured. Should not be empty";
            }
            KeyWord command = KeyWord.OTHERS;

            try {
                command = parseKeyWord(input.toUpperCase());
                logger.config("updated config for ItLogger command");
                assert command != KeyWord.OTHERS : "proper keyword should be captured";

            } catch (IllegalArgumentException e) {
                keyWordIssue();
                logger.log(Level.WARNING, "Non-existent keyword was captured! error is: " + e.getMessage(), e);
            }

            //todo -> build PARSER
            switch (command) {
            case ADD: // Jian Cheng
                // todo -> add Defect
                logger.info("Performing adding operation for ItLogger, add a new defect...");
                try {
                    Defect newIssue = new Defect(parseTitle(input), parseStatus(input),
                                        parseSeverity(input), parseDeadline(input), parseOwner(input));
                    //System.out.println(test.toString());
                    assert newIssue != null : "Issue in creating new issue";
                    issueList.addIssue(newIssue);
                } catch (ParseException e) {
                    printErrorMessageToUser(e);
                    logger.log(Level.WARNING, "Issue in parsing command: " + e.getMessage(), e);
                }

                break;

            case UPDATE: // Shijie
                logger.info("Performing updating operation for ItLogger, update an existing defect...");
                try {
                    Defect defect = issueList.getDefect(parseIndex(input,issueList.getSize()));
                    boolean isExit = false;
                    do {
                        String updateCommandContent;
                        System.out.println("You are modifying:");
                        System.out.println(defect.toString());
                        Interface.printUpdateContent();
                        if (hasNextLine()) {
                            updateCommandContent = getInput();
                            isExit = Parser.parseUpdateContent(updateCommandContent,defect);
                        }
                    } while (isExit);
                } catch (ParseException e) {
                    printErrorMessageToUser(e);
                    logger.log(Level.WARNING, "Issue in parsing command: " + e.getMessage(), e);
                } catch (Exception e) {
                    printErrorMessageToUser(e);
                    logger.log(Level.WARNING, "Update occurs unknown error: " + e.getMessage(), e);
                }

                break;

            case VIEW: // Shi Jie
                // todo -> view ONE Defect with INDEX NUMBER
                logger.info("Performing view operation for ItLogger, viewing specific defect...");
                try {

                    System.out.println(issueList.getDefect(parseIndex(input,issueList.getSize())).toString());
                    logger.info("Obtained the specific defect...");
                    int indexOfDefect = parseIndex(input,issueList.getSize());
                    assert indexOfDefect >= 0 : "Viewing index shall non-negative";
                    System.out.println(issueList.getDefect(indexOfDefect).toString());
                    logger.info("Finished obtaining the specific defect...");
                } catch (ArrayIndexOutOfBoundsException e) {
                    logger.log(Level.WARNING,"Problem viewing defect. error is: " + e.getMessage(), e);
                    System.out.println("Please check the index value and also the correct format: "
                            + "VIEW /index. Eg:VIEW /1");

                }
                break;

            case DELETE: // Jang
                // todo -> delete ONE Defect using INDEX NUMBER
                logger.info("Begin deleting a specific Defect...");
                try {
                    int indexDeletion = parseIndex(input,issueList.getSize());
                    assert indexDeletion >= 0 : "Deletion index must be non-negative";
                    assert indexDeletion <= issueList.getSize() : "Deletion index must "
                            + "be equal or lesser than array size";
                    issueList.deleteIssue(indexDeletion);
                    logger.info("Deletion of Defect successful");
                } catch (ArrayIndexOutOfBoundsException e) {
                    logger.log(Level.WARNING, "Pls check your index values. It is "
                            + "either greater than the largest value or a negative value.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    logger.log(Level.WARNING, "Some error have occurred. "
                            + "You must have entered an invalid index" + e.getMessage());
                }
                break;

            case LIST:  // Jun Wen
                // todo -> list ALL avaliable Defect in Issue List
                logger.info("Performing listing operation for ItLogger, showing all defect...");
                try {
                    int listKeyword = 0;
                    if (input.contains("/")) {
                        listKeyword = parseListType(input);
                    }
                    Vector toBeDisplayed = sortList(issueList, listKeyword);
                    logger.info("Obtained issueList...");
                    assert toBeDisplayed != null : "IT logger issue list should exists";
                    if (toBeDisplayed.size() == 0) {
                        logger.info("Problem displaying empty list...no item to show...");
                        assert toBeDisplayed.size() == 0 : "IT logger issue list should be empty here";
                        throw new EmptyListException("The IT Logger Defect list is empty");
                    } else {
                        logger.info("printing issues");
                        assert toBeDisplayed.size() > 0 : "There should be at least 1 issue to be displayed";
                        displayIssues(toBeDisplayed);
                    }
                } catch (EmptyListException e) {
                    logger.log(Level.WARNING,"Problem displaying list. error is: " + e.getMessage(), e);
                    emptyErrorMsg();
                } catch (ParseException e) {
                    logger.log(Level.WARNING,"Parsing keywords. error is: " + e.getMessage(), e);
                    emptyErrorMsg();
                }
                break;

            case SEARCH: // Jun Wen
                // todo -> Search (search by title, status, severity, deadline, owner)
                String searchBy = parseSearchType(input);
                String searchTerm = parseSearchTerm(input);
                Vector<Defect> issues = issueList.getIssue();

                if (searchBy.equals("TITLE")) {
                    displayIssues(searchTitle(searchTerm, issues));

                } else if (searchBy.equals("STATUS")) {
                    displayIssues(searchStatus(searchTerm, issues));

                } else if (searchBy.equals("SEVERITY")) {
                    displayIssues(searchSeverity(searchTerm, issues));

                } else if (searchBy.equals("OWNER")) {
                    displayIssues(searchOwner(searchTerm, issues));

                } else if (searchBy.equals("DEADLINE")) {
                    try {
                        displayIssues(searchDeadline(searchTerm, issues));
                    } catch (ParseException e) {
                        System.out.println("Issue parsing date searched");
                    }
                }

                break;
            case HELP:
                logger.info("help operation started");
                printFileToUser();
                break;
            case EXIT:
                try {
                    storage.save(issueList);
                } catch (StorageFile.StorageOperationException e) {
                    System.out.println("Issue saving file. Exiting Program");
                }

                logger.info("exiting program");
                logger.config("updating program config to quit");
                keepRun = false;
                break;

            default:
                logger.warning("unknown keyword sent to IT logger as command");
                System.out.println("Unknown KeyWord, please try again!");
                break;
            }
            logger.info("completed an iteration of ItLogger logic flow.");
        }
    }

}
