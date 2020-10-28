package seedu.tracker;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import seedu.tracker.parser.Parser;
import seedu.tracker.project.Project;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

public class DeleteTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDelete() {
        ProjectList projects = new ProjectList();
        Parser parser = new Parser();
        Ui ui = new Ui();
        Storage storage = new Storage("testProjects.txt", projects, ui);

        //Deleting when there's no projects in list
        parser.parseInput("--delete 1", ui, projects, storage).execute();

        //Adding new projects to list
        parser.parseInput("--project --name Project 1 --description regarding hospital task --involve " +
            "Tom, Lucy --client MOH --startdate 11/11/2020 --duedate 12/12/2020 --incharge Derek --email zhenquan38@gmail.com", ui, projects, storage).execute();
        parser.parseInput("--project --name Project 2 --description regarding hospital task --involve " +
            "Tom, Lucy --client MOH --startdate 11/11/2020 --duedate 12/12/2020 --incharge Derek --email zhenquan38@gmail.com", ui, projects, storage).execute();
        parser.parseInput("--project --name Project 3 --description regarding hospital task --involve " +
            "Tom, Lucy --client MOH --startdate 11/11/2020 --duedate 12/12/2020 --incharge Derek --email zhenquan38@gmail.com", ui, projects, storage).execute();

        thrown.expect(IndexOutOfBoundsException.class);
        parser.parseInput("--delete 0", ui, projects, storage).execute();
        parser.parseInput("--delete 4", ui, projects, storage).execute();

        thrown.expect(NumberFormatException.class);
        parser.parseInput("--delete   ", ui, projects, storage).execute();
        parser.parseInput("--delete a", ui, projects, storage).execute();
        parser.parseInput("--delete abc", ui, projects, storage).execute();

        //Delete 3rd project successfully
        parser.parseInput("--delete 3", ui, projects, storage).execute();

        //Delete all projects successfully
        parser.parseInput("--delete all", ui, projects, storage).execute();

    }
}
