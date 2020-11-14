package seedu.tracker;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import seedu.tracker.parser.Parser;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

public class EditTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testEdit() {
        ProjectList projects = new ProjectList();
        Parser parser = new Parser();
        Ui ui = new Ui();
        Storage storage = new Storage("testProjects.txt", projects, ui);

        //Editing when there's no projects in list
        parser.parseInput("--edit 1 --name No Projects", ui, projects, storage).execute();

        //Adding new project to list
        parser.parseInput("--project --name Project 1 --description regarding hospital task --involve "
                + "Tom, Lucy --client MOH --startdate 11/11/2020 --duedate 12/12/2020 --incharge Derek"
                + " --email zhenquan38@gmail.com", ui, projects, storage).execute();

        thrown.expect(IndexOutOfBoundsException.class);
        parser.parseInput("--edit 0 --name Project 0", ui, projects, storage).execute();
        parser.parseInput("--edit 2 --name Project 2", ui, projects, storage).execute();

        thrown.expect(NumberFormatException.class);
        parser.parseInput("--edit a --name Project a", ui, projects, storage).execute();
        parser.parseInput("--edit abc --name Project abc", ui, projects, storage).execute();

        //Edit successfully
        parser.parseInput("--edit 1 --name Project 1", ui, projects, storage).execute();
    }
}
