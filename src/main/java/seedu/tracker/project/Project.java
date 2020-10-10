package seedu.tracker.project;

public abstract class Project {
    String description;

    Project(String input) {
        description = input;
    }

    public String getDescription() {
        return description;
    }
}