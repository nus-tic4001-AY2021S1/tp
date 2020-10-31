package seedu.tracker.project;

public class NewProject extends Project {

    public NewProject(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return description;
    }
}
