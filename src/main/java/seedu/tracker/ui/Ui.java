package seedu.tracker.ui;

import seedu.tracker.project.ProjectList;

/**
 * Responsible for displaying all messages and its format to the user except for error-related messages.
 */
public class Ui {
    public String colorRed(String input) {
        return "\033[31m" + input + "\033[0m";
    }


    public String colorGreen(String input) {
        return "\033[33m" + input + "\033[0m";
    }

    /**
     * Prints the input string with borderlines.
     */
    public void printBorderline(String input) {
        System.out.println(input + "\n⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢⬡⬡⬢⬢\n");
    }

    public void printGreeting() {
        String greeting = "Hello! I'm Tracker.\n"
            + "I can help you manage a list of projects!\n";
        printBorderline("");
        System.out.print(greeting);
    }

    public void printHelp() {
        String help = "What you can tell me to do is listed below:\n"
            + "    -See all commands          | " + colorGreen("--help\n")
            + "    -Delete a project          | " + colorGreen("--delete < project index number>\n")
            + "    -List down all projects    | " + colorGreen("--list\n")
            + "    -Find word in projects     | " + colorGreen("--find<word>\n")
            + "    -Exit my program           | " + colorGreen("--exit or hit Enter");
        printBorderline(help);
    }

    public void printFarewell() {
        String farewell = "Bye. Hope to see you again soon!";
        printBorderline(farewell);
    }

    public void printTaskRemoved(ProjectList projects, int index) {
        String added = "Okay! We have removed the project as shown below:\n"
            + colorRed(projects.get(index - 1).getDescription()
            //    + colorRed(projects.getProject().get(index - 1).toString()
                + colorGreen("\nWe now have " + (projects.size() - 1) + " project(s) in your list!"));
        printBorderline(added);
    }

    public void printInvalidCommand() {
        String message = "It seems that you have typed something out of my unfortunately\n"
            + "limited vocabulary. Can you try again?";
        printBorderline(message);
    }

    public void printNoFileFound() {
        String message = "It looks like it's your first time as I could not find any existing project file.\n"
            + "Get started by creating a project!";
        printBorderline(message);
    }

    public void printFileExists() {
        String message = "An existing project file is found!";
        printBorderline(message);
    }


}