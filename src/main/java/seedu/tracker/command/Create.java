package seedu.tracker.command;

import seedu.tracker.exception.TrackerException;
import seedu.tracker.project.NewProject;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

import java.io.IOException;
import java.util.ArrayList;

public class Create extends Command {
    public static final String word = "--project";

    public Create(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {
        try {
            ArrayList<String> newProject = new ArrayList<>();
            newProject.add("name");
            newProject.add("description");
            newProject.add("involve");
            newProject.add("startdate");
            newProject.add("duedate");
            newProject.add("incharge");

            String[] splits = line.split("--");
            Boolean hasMistake = false;

            for (String command : newProject) {
                if (!line.contains("--" + command)) {
                    System.out.println("The command line is missing for --" + command);
                    hasMistake = true;
                }
            }
            if (hasMistake) {
                System.out.println("Please create the project in the correct format:\n"
                    + "--project --name INPUT --description INPUT --involve INPUT --startdate dd/mm/yyyy --duedate dd/mm/yyyy --incharge INPUT");
                return;
            }

            String newData = "";
            String temp;

            for (String command : newProject) {
                for (int num = 1; num < splits.length; num++) {
                    if (splits[num].contains(command)) {
                        temp = "--" + splits[num];
                        newData = newData.concat(temp);
                    }
                }
            }
            projects.add(new NewProject(newData));
            ui.printProjectCreated(projects);
            storage.updateStorage(projects);

        } catch (IOException e) {
            ui.printBorderline(e.getMessage());
        }
    }
}
