package seedu.itlogger;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Defect {
    String title;
    String status;
    int severity;
    Date dateRaised;
    Date deadline;
    String owner;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    // Creating logger
    private static final Logger logger = Logger.getLogger(Defect.class.getName());

    public Defect(String title, String status, int severity, Date deadline, String owner) {
        this.title = title;
        this.status = status;
        this.severity = severity;
        this.dateRaised = Date.from(Instant.now());
        this.deadline = deadline;
        this.owner = owner;

        assert this.title != null : "title cannot be empty";
        assert this.status != null : "status cannot be empty";
        assert this.severity > 0 : "severity cannot be 0";
        assert this.dateRaised != null : "dateRaised cannot be empty";
        assert this.deadline != null : "deadline cannot be empty";
        assert this.owner != null : "owner cannot be empty";

        try {
            setupLogger();
            logger.info("created defect Object successfully");
        } catch (IOException e) {
            System.out.println("Issue creating Log file");
        }
    }

    /** Setup Logger for Defect class.
     * @throws IOException unable to create file
     */
    public static void setupLogger() throws IOException {
        // Remove the default console handler
        Logger parentLogger = Logger.getLogger("");
        Handler[] handlers = parentLogger.getHandlers();
        for (Handler handler : handlers) {
            parentLogger.removeHandler(handler);
        }

        Handler fh = new FileHandler("Defect.log", true);
        fh.setFormatter(new SimpleFormatter());
        logger.addHandler(fh);
        logger.setLevel(Level.FINE);
    }

    /**
     * toString method will convert Defect object into String
     * to print out.
     * @return String to print out for this Defect
     */
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);
        String strRep =  "Title: " + title + System.lineSeparator()
                        + "Status: " + status + System.lineSeparator()
                        + "Severity: Level " + severity + System.lineSeparator()
                        + "Date Raised: " + dateFormat.format(dateRaised) + System.lineSeparator()
                        + "Deadline: " + dateFormat.format(deadline) + System.lineSeparator()
                        + "Owner: " + owner;

        logger.info("printing object to string");
        assert strRep != null : "String representation of Defect cannot be empty";
        return strRep;
    }

}
