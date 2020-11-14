package seedu.itlogger;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class InputHandler {

    // Creating logger for InputHandler
    private static final Logger logger = Logger.getLogger(InputHandler.class.getName());
    private static Scanner inputStream = new Scanner(System.in);

    public InputHandler() {
        try {
            setupLogger();
            logger.info("created InputHandler object successfully");
        } catch (IOException e) {
            System.out.println("Issue creating Log file");
        }
    }

    public static void setupLogger() throws IOException {
        // Remove the default console handler
        Logger parentLogger = Logger.getLogger("");
        Handler[] handlers = parentLogger.getHandlers();
        for (Handler handler : handlers) {
            parentLogger.removeHandler(handler);
        }

        Handler fh = new FileHandler("InputHandler.log", true);
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        logger.setLevel(Level.FINE);
    }

    public static String getInput() {
        logger.info("getting user input");
        return inputStream.nextLine();
    }

    public static boolean hasNextLine() {
        logger.info("checking if have inputStream has next line");
        return inputStream.hasNextLine();
    }
}
