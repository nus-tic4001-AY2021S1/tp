package seedu.tracker.command;

import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

/**
 * An abstract class that is inherited by every command.
 */
public abstract class Command {
    public String line;
    public ProjectList projects;
    public Storage storage;
    public Ui ui;

    protected Command(String line, ProjectList projects, Ui ui, Storage storage) {
        this.line = line;
        this.storage = storage;
        this.projects = projects;
        this.ui = ui;
    }

    protected Command(String line, ProjectList projects, Ui ui) {
        this.line = line;
        this.projects = projects;
        this.ui = ui;
    }

    protected Command(ProjectList projects, Ui ui) {
        this.projects = projects;
        this.ui = ui;
    }

    protected Command(Ui ui) {
        this.ui = ui;
    }



    public abstract void execute();
}