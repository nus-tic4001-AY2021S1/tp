package seedu.tracker.project;

/**
 * a newproject class where creating new project.
 */

public class NewProject extends Project {

    public NewProject(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return description;
    }
}
