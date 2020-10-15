package seedu.tracker.command;

import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

public class Find extends Command{
    public static final String word = "--find";

    public Find(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {
        String searchWord = line.trim();
        if (projects.size() == 0) {
            ui.printBorderline("It appears that you have no project! Perhaps you should start creating one?");
            return;
        }
        if (searchWord.isEmpty()) {
            ui.printBorderline("Please type in the 'find KEYWORD' format!");
            return;
        }
        String matches = "Here are the project that matches '" + searchWord + "'!\n";
        boolean hasMatch = false;
        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getDescription().toLowerCase().contains(searchWord)) {
                matches = matches.concat((i + 1) + ". " + projects.get(i).getDescription()) + "\n";
                hasMatch = true;
            }

        }
        if (!hasMatch) {
            matches = "It appears that are no matches for '" + searchWord + "'!\n" +
                    "Perhaps you could try searching another word?";
        }
        ui.printBorderline(matches);

    }

}
