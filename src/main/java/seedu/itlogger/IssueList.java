package seedu.itlogger;

import java.io.IOException;
import java.util.Vector;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class IssueList {

    // Creating Logger for IssueList
    private static final Logger logger = Logger.getLogger(IssueList.class.getName());
    private Vector<Defect> issueList;

    public IssueList() {
        issueList = new Vector<>();
        assert issueList != null : "issueList should not be null after creation";

        try {
            setupLogger();
            logger.info("created IssueList Object successfully");
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

        Handler fh = new FileHandler("IssueList.log", true);
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        logger.setLevel(Level.FINE);
    }

    public Vector<Defect> getIssue() {
        logger.info("fetching all issues from issueList...");
        assert issueList != null : "issueList returned should not be null";
        return issueList;
    }

    public void loadIssues(Vector<Defect> defectsList) {
        issueList.addAll(defectsList);
    }

    public void addIssue(Defect defect) {
        logger.info("adding an issue to issueList...");
        issueList.add(defect);
        assert issueList.size() > 0 : "issueList should not be empty";
    }

    public void deleteIssue(int index) {
        logger.info("deleting an issue from issueList...");
        issueList.remove(index);
    }

    public Defect getDefect(int index) {
        logger.info("extracting an issue in issueList...");
        assert issueList.get(index) != null : "defect " + index + " retrieved should not be null";
        return issueList.get(index);
    }

    public int getSize() {
        logger.info("getting total number of issues in issueList...");
        assert issueList.size() >= 0 : "issueList should be at least 0 size large";
        return issueList.size();
    }
}
