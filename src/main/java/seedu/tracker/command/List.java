package seedu.tracker.command;

import seedu.tracker.project.ProjectList;
import seedu.tracker.ui.Ui;

/**
 * A command to print out all tasks in the list.
 */
public class List extends Command {
    public static final String word = "--list";

    public List(ProjectList tasks, Ui ui) {
        super(tasks, ui);
    }

    @Override
    public void execute() {
        if (projects.size() == 0) {
            ui.printBorderline("It appears that you have no projects! Perhaps you should start creating one?");
            return;
        }
        String total = "Here are the projects that you currently have!\n";
        for (int i = 0; i < projects.size(); i++) {
            total = total.concat((i + 1) + ". " + projects.get(i).getDescription()) + "\n";
            //total = total.concat((i + 1) + ". " + projects.getProject().get(i).toString()) + "\n";
        }
        ui.printBorderline(total);
    }
}
