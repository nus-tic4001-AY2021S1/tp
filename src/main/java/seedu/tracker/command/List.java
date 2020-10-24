package seedu.tracker.command;

import seedu.tracker.project.ProjectList;
import seedu.tracker.ui.Ui;

/**
 * A command to print out all projects in the list.
 */
public class List extends Command {
    public static final String word = "--list";

    public List(ProjectList projects, Ui ui) {
        super(projects, ui);
    }

    @Override
    public void execute() {
        if (projects.size() == 0) {
            ui.printBorderline("It appears that you have no projects! Perhaps you should start creating one?");
            return;
        }
        String message = "Here are the projects that you currently have!\n";

        for (int i = 0; i < projects.size(); i++) {
            message = message.concat(i + 1 + ".\n" + ui.displayProject(projects.get(i)));
        }
        ui.printBorderline(message);
    }
}