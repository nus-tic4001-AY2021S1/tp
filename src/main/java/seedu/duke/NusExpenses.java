package seedu.duke;

import java.util.Scanner;


public class NusExpenses {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        //Start the Expense Tracker.
        new NusExpenses().run();

    }

    public void run() {


        boolean isExit = false;
        while (isExit) {
            try {
                switch ("") {
                case "EXIT":
                    isExit = true;
                    break;
                case "ADD":
                    break;
                case "VIEW":
                    break;
                case "DELETE":
                    break;
                case "SEARCH":
                    break;
                case "TOTAL":
                    break;
                default:
                    break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

        }
        System.exit(0);
    }
}