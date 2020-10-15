package seedu.tracker.command;

import seedu.tracker.exception.TrackerException;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

import java.io.IOException;

/**
 * A command to delete a project from the project list.
 */
public class Delete extends Command {
    public static final String word = "--delete";

    public Delete(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {
        try {
            if (projects.size() == 0) {
                ui.printBorderline("It appears that you have no projects yet, so you can't delete any!\n"
                    + "Perhaps you should start creating one?");
                return;
            }
            if (line.isEmpty()) {
                throw new TrackerException(
                    "You almost typed a proper delete command, but you missed out the number!\n"
                        + "Please type in the '--delete INDEX' format.");
            }
            int index = Integer.parseInt(line);
            ui.printProjectRemoved(projects, index);
            projects.remove(index);
            storage.updateStorage(projects);

        } catch (TrackerException | IOException e) {
            ui.printBorderline(e.getMessage());
        } catch (NumberFormatException e) {
            ui.printBorderline(
                "I'm sorry, but the list goes numerically.\nPerhaps you could type a number for the index?");
        } catch (IndexOutOfBoundsException e) {
            ui.printBorderline("It appears that you only have " + projects.size() + " project(s) in your list,\n"
                + "perhaps you might want to try typing an index number from 1 to " + projects.size() + " instead?");
        }
    }
}