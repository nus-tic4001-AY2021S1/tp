package seedu.tracker.project;


/**
 * a abstract class for all the project
 */
public class Project {
    String description;
    private boolean isComplete;

    public Project(String description) {
        this.description = description;
    }

    public void setComplete() {
        this.isComplete = true;
    }

    public String getDescription() {
        return getStatus() + description;
    }

    public String toString() {
        return description;
    }

    public String getStatus() {
        return "[" + (isComplete ? "Complete" : "Incomplete") + "] ";
    }
}