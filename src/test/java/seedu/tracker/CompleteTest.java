package seedu.tracker;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import seedu.tracker.parser.Parser;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

public class CompleteTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testDelete() {
        ProjectList projects = new ProjectList();
        Parser parser = new Parser();
        Ui ui = new Ui();
        Storage storage = new Storage("testProjects.txt", projects, ui);

        parser.parseInput("--complete 1", ui, projects, storage).execute();

        //Adding new projects to list
        parser.parseInput("--project --name Project 1 --description regarding hospital task --involve " +
                "Tom, Lucy --client MOH --startdate 11/11/2020 --duedate 12/12/2020 --incharge Derek --email linqing4267@gmail.com", ui, projects, storage).execute();
        parser.parseInput("--project --name Project 2 --description regarding hospital task --involve " +
                "Tom, Lucy --client MOH --startdate 11/11/2020 --duedate 12/12/2020 --incharge Derek --email linqing4267@gmail.com", ui, projects, storage).execute();
        parser.parseInput("--project --name Project 3 --description regarding hospital task --involve " +
                "Tom, Lucy --client MOH --startdate 11/11/2020 --duedate 12/12/2020 --incharge Derek --email linqing4267@gmail.com", ui, projects, storage).execute();

        thrown.expect(IndexOutOfBoundsException.class);
        parser.parseInput("--complete 0", ui, projects, storage).execute();
        parser.parseInput("--complete 4", ui, projects, storage).execute();

        thrown.expect(NumberFormatException.class);
        parser.parseInput("--complete   ", ui, projects, storage).execute();
        parser.parseInput("--complete a", ui, projects, storage).execute();
        parser.parseInput("--complete abc", ui, projects, storage).execute();

        //complete 3rd project successfully
        parser.parseInput("--complete 3", ui, projects, storage).execute();

    }
}


