package seedu.tracker.command;

import seedu.tracker.exception.TrackerException;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

import java.io.IOException;

public class Done extends Command{
    public static final String word = "--done";

    public Done(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }
    @Override
    public void execute() {
        try {
            if (projects.size() == 0) {
                ui.printBorderline("It appears that you have no tasks yet, so you can't complete any!\n" +
                        "Perhaps you should start creating one?");
                return;
            }
            else if (line.isEmpty()) {
                throw new TrackerException(
                        "You almost typed a proper delete command, but you missed out the number!\n"
                                + "Please type in the '--done INDEX' format.");
            }
            else {
                int index = Integer.parseInt(line);
                projects.get(index - 1).markAsDone();
                ui.printTaskCompleted(index, projects);
            }
            storage.updateStorage(projects);

        } catch (TrackerException | IOException e) {
            ui.printBorderline(e.getMessage());
        } catch (NumberFormatException e) {
            ui.printBorderline("I'm sorry, but the list goes numerically.\nPerhaps you could type a number for the index?");
        } catch (IndexOutOfBoundsException e) {
            ui.printBorderline("It appears that you only have " + projects.size() + " task(s) in your list,\n" +
                    "perhaps you might want to try typing an index number from 1 to " + projects.size() + " instead?");
        }


    }
}
