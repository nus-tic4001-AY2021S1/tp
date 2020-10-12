package seedu.tracker.project;

public class Project {
    String description;

    public Project(String input) {
        description = input;
    }

    public String getDescription() {
        return description;
    }
}