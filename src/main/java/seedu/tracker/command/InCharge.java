package seedu.tracker.command;
import seedu.tracker.exception.TrackerException;
import seedu.tracker.project.Project;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

import java.io.IOException;

public class InCharge extends Command {
    public static final String word = "--inCharge";

    public InCharge(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {
        try {
            if (line.isEmpty()) {
                throw new TrackerException("It seems that you missed out the task description!\n" +
                        "Please type in the '--inCharge <something>' format.");
            }
            line = "[InCharge]     " + line;
            projects.add(new Project(line));
        } catch (TrackerException e) {
            ui.printBorderline(e.getMessage());
        }
    }

}

