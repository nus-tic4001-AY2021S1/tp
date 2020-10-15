package seedu.tracker.command;

import seedu.tracker.exception.TrackerException;
import seedu.tracker.project.Project;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

import java.io.IOException;

public class Add extends Command {
    public static final String word = "--add";

    public Add(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {
        try {
            String[] splits = line.split("--");
            String commandWithIndex = splits[1];
            String commandWithAdditionalWord = splits[2];

            String projectIndex = commandWithIndex.split(" ", 2)[1];
            String commandWord = commandWithAdditionalWord.split(" ", 2)[0];
            String additionalWord = commandWithAdditionalWord.split(" ", 2)[1];
            if (projectIndex.isEmpty()) {
                throw new TrackerException("It seems that you did not type in the correct format!\n" +
                    "Please type in the '--add INDEX --commandName INPUT' format.");
            }

            int index = Integer.parseInt(projectIndex.trim()) - 1;
            String line = projects.get(index).toString();

            String[] selectedProject = line.split("--");
            String newData = "";

            for (int i = 1; i < selectedProject.length; i++) {
                String currentCommandWord = selectedProject[i].split(" ", 2)[0];
                String currentDescription = selectedProject[i].split(" ", 2)[1];

                if (commandWord.equalsIgnoreCase(currentCommandWord)) {
                    String temp = currentDescription + ", " + additionalWord;
                    newData = newData.concat("--" + selectedProject[i].replace(currentDescription, temp) + " ");
                } else {
                    newData = newData.concat("--" + selectedProject[i]);
                }
            }
            projects.set(index, new Project(newData));
            ui.printAddMessage(projects);
            storage.updateStorage(projects);

        } catch (TrackerException | IOException e) {
            ui.printBorderline(e.getMessage());
        }
    }
}
