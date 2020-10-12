package seedu.duke.utilities;

import java.util.Scanner;

/**
 * Display / Show to user stuffs.
 */
public class Ui {
    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Read in the next input line.
     * @return
     */
    public String readUserCommand() {

        return in.nextLine().trim();
    }

    /**
     * Close the inputstream.
     *
     */
    public void endInputFeed() {
        in.close();
    }


    public static void showToUser(String message) {
        System.out.println(message);
    }
}
