package seedu.tracker.storage;

import seedu.tracker.command.List;
import seedu.tracker.project.ProjectList;
import seedu.tracker.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for all interactions between the in-memory projects and the projects file.
 */
public class Storage {
    String fileName;

    public Storage(String fileName, ProjectList projects, Ui ui) {
        this.fileName = fileName;
    }

    public void updateStorage(ProjectList projects) throws IOException {
        FileWriter fw = new FileWriter("./" + fileName, false);
        for (int i = 0; i < projects.size(); i++) {
            String line = (i + 1) + ". " + projects.get(i).getDescription() + "\n";
            fw.write(line);
        }
        fw.close();
    }

    public void readStorage(ProjectList projects, Ui ui, Storage storage) {
        try {
            ArrayList<String> lines = getLines(fileName);
            projects = extractProjects(lines, projects, ui, storage);
            ui.printFileExists();
            new List(projects, ui).execute();

        } catch (FileNotFoundException e) {
            ui.printNoFileFound();
        } catch (IOException e) {
            ui.printBorderline("IOException encountered: " + e.getMessage());
        }
    }

    private ArrayList<String> getLines(String fileName) throws FileNotFoundException {
        Scanner s = new Scanner(new File(fileName));
        ArrayList<String> list = new ArrayList<>();
        while (s.hasNextLine()) {
            list.add(s.nextLine());
        }
        s.close();
        return list;
    }

    public ProjectList extractProjects(ArrayList<String> lines, ProjectList projects, Ui ui, Storage storage)
        throws IOException {
        for (String line : lines) {
            FileWriter fw = new FileWriter(fileName, true);
            // Format of a project line has to be implemented
            // before we can extract from projects.txt,
            // hence the lines belows are commented.
            /*String taskStatus = line.split("]")[0].substring(line.indexOf("[") + 1);
            String taskType = line.split("]")[1].substring(line.indexOf("[") - 1);
            String taskDescription = line.split("]")[2].trim();

            switch (taskType) {
                case "Deadline" -> projects.add(new Deadline("[Deadline] " + taskDescription));
                case "Event" -> projects.add(new Event("[Event]    " + taskDescription));
            }
            if (taskStatus.equals("\u2713")) {
                projects.get(projects.size() - 1).setDone();
            }*/
        }
        return projects;
    }
}