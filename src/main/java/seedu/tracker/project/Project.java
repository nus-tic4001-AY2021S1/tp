package seedu.tracker.project;

public class Project {
    String description;
    private boolean isComplete;

    public Project(String description) {
        this.description = description;
    }

    public void setComplete() {
        this.isComplete =true;
    }

    public String getDescription() {
        return getStatusIcon() + description;
    }

    public String toString() { return description; }

    public String getStatusIcon() {
        return "[" + (isComplete ? "Complete" : "Incomplete") + "] ";
    }
}