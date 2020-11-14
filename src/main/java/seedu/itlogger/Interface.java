package seedu.itlogger;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Logger;

public class Interface {
    private static final Logger logger = Logger.getLogger(InputHandler.class.getName());
    private static String LOGO =  " ____        _\n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    private static String ASK_NAME = "What is your name?";
    private static String GREETING_MSG = "Hello ";
    private static String PROGRAM_OPENING = "What would you like to do?";
    private static String EMPTY_ERROR_MSG = "There are no items now";
    private static String KEYWORD_ISSUE = "Keyword does not exists";
    private static String UPDATECONTENT_MSG = "Please specify the updating content. "
            + "Enter \"update e/exit\" to exit updating status.";
    private static String HELP_MSG = "ITLogger\n"
            + "\n"
            + "Introduction:\n"
            + "This is a logging software which allows tracking"
            + " of software issues & assign them to the development team.\n"
            + "\n"
            + "Usage:\n"
            + "  help\n"
            + "    -- Print out help information\n"
            + "\n"
            + "  exit\n"
            + "    -- Exit from the program\n"
            + "\n"
            + "  add t/<TITLE> s/<STATUS> sv/<SEVERITY in integer from 0 to 10>"
            + " dl/<DEADLINE in dd-mm-yyyy format> o/<OWNER>\n"
            + "    -- Add a defect into the list\n"
            + "\n"
            + "  view v/<TICKET in integer>\n"
            + "    -- View a defect which is indicated\n"
            + "\n"
            + "  delete d/<TICKET in integer>\n"
            + "    -- Delete a defect which is indicated\n"
            + "\n"
            + "  list\n"
            + "    -- List all defects in the list\n"
            + "    \n"
            + "  list sv/a\n"
            + "    -- List all defects in the list in ascending severity order\n"
            + "\n"
            + "  list sv/d\n"
            + "    -- List all defects in the list in descending severity order\n"
            + "    \n"
            + "  list dl/a\n"
            + "    -- List all defects in the list in ascending deadline order\n"
            + "    \n"
            + "  list dl/d\n"
            + "    -- List all defects in the list in descending deadline order\n"
            + "    \n"
            + "  list dr/a\n"
            + "    -- List all defects in the list in ascending date raised order\n"
            + "    \n"
            + "  list dl/d\n"
            + "    -- List all defects in the list in descending date raised order\n"
            + "    \n"
            + "  update u/<TICKET in integer>\n"
            + "    -- Go into update mode for a defect which is indicated\n"
            + "\n"
            + "  update t/<NEW TITLE>\n"
            + "    -- When in update mode, update the title of the defect\n"
            + "\n"
            + "  update sv/<NEW SEVERITY in integer from 0 to 10>\n"
            + "    -- When in update mode, update the severity of the defect\n"
            + "    \n"
            + "  search /title /<title>\n"
            + "    -- Search the defects match the <title> in the issue list\n"
            + "    \n"
            + "  search /status /<status>\n"
            + "    -- Search the defects match the <status> in the issue list\n"
            + "    \n"
            + "  search /severity /<severityNum>\n"
            + "    -- Search the defects match the <severityNum> in the issue list\n"
            + "    \n"
            + "  search /owner /<owner>\n"
            + "    -- Search the defects match the <owner> in the issue list\n"
            + "    \n"
            + "  search /deadline /<deadlineDateString(dd-MM-yyyy)>\n"
            + "    -- Search the defects match the <deadline> in the issue list\n";

    public static void printUpdateContent() {
        System.out.println(UPDATECONTENT_MSG);
    }

    public static void printLogo() {
        System.out.println(LOGO);
    }

    public static void askName() {
        System.out.println(ASK_NAME);
    }

    public static void greeter(String name) {
        System.out.println(GREETING_MSG + name);
    }

    public static void programOpening() {
        System.out.println(PROGRAM_OPENING);
    }

    public static void keyWordIssue() {
        System.out.println(KEYWORD_ISSUE);
    }

    public static void displayIssues(Vector toBeDisplayed) {
        Iterator iter = toBeDisplayed.iterator();
        while (iter.hasNext()) {
            Defect issue = (Defect) iter.next();
            System.out.println(issue + System.lineSeparator());
        }
    }

    public static void printFileToUser() throws IOException {
        printLogo();
        System.out.println(HELP_MSG);
    }

    public static void printErrorMessageToUser(Exception errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }

    public static void emptyErrorMsg() {
        System.out.println(EMPTY_ERROR_MSG);
    }
}
