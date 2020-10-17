package seedu.tracker.project;

public class Project {
    String description;
    protected boolean isDone;

    public Project(String description) {
        this.description = description;
        this.isDone = false;
    }
    public String getStatusIcon() {
        return (isDone ? "Completed" : "incomplete");
    }
    public Project markAsDone() {
        this.isDone = true;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public String toString(){
        return "[" + getStatusIcon() + "] " + description;
    }

}