package seedu.tracker.command;

import seedu.tracker.project.Project;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

import java.io.IOException;

public class Find extends Command {
    public static final String word = "--find";

    public Find(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {
        String searchWord = line.split("--replace")[0].trim();

        if (projects.size() == 0) {
            ui.printBorderline("It appears that you have no project! Perhaps you should start creating one?");
            return;
        }
        if (line.isEmpty()) {
            ui.printBorderline("Please type in the '--find KEYWORD' or '--find KEYWORD --replace KEYWORD' format!");
            return;
        }

        String matches = "Here are the project(s) that matches '" + searchWord + "'!\n";
        boolean hasMatch = false;

        for (int i = 0; i < projects.size(); i++) {
            if (projects.get(i).getDescription().toLowerCase().contains(searchWord.toLowerCase())) {
                matches = matches.concat(i + 1 + ".\n" + ui.displayProject(projects.get(i)));
                hasMatch = true;
            }
        }
        if (!hasMatch) {
            matches = "It appears that are no matches for '" + searchWord + "'!\n" +
                "Perhaps you could try searching another word?";
        }
        if (hasMatch && line.contains("--replace")) {
            findAndReplace();
            return;
        }
        ui.printBorderline(matches);
    }

    private void findAndReplace() {
        try {
            String searchWord = line.split("--replace")[0].trim();
            String replaceWord = line.split("--replace")[1].trim();
            String changes = "Here are the project(s) that has the word '" + searchWord
                + "' replaced with '" + replaceWord + "'!\n";

            for (int i = 0; i < projects.size(); i++) {
                String line = projects.get(i).toString();
                String[] currentProject = line.split("--");
                String newData = "";


                for (int j = 1; j < currentProject.length; j++) {
                    String currentDescription = currentProject[j].split(" ", 2)[1];

                    if (currentDescription.toLowerCase().contains(searchWord.toLowerCase())) {
                        currentDescription = currentProject[j].replace(searchWord, replaceWord);
                        newData = newData.concat("--" + currentDescription + " ").trim();
                    } else {
                        newData = newData.concat("--" + currentProject[j].trim());
                    }
                }
                projects.set(i, new Project(newData));
                changes = changes.concat(i + 1 + ".\n" + ui.displayProject(projects.get(i)));
            }
            storage.updateStorage(projects);
            ui.printBorderline(changes);

        } catch (IOException e) {
            ui.printBorderline(e.getMessage());
        }
    }
}
