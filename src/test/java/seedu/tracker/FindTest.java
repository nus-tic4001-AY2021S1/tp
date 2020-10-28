package seedu.tracker;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import seedu.tracker.parser.Parser;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

public class FindTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testFindAndReplace() {
        ProjectList projects = new ProjectList();
        Parser parser = new Parser();
        Ui ui = new Ui();
        Storage storage = new Storage("testProjects.txt", projects, ui);

        //Searching word when there's no projects in list
        parser.parseInput("--find nothing", ui, projects, storage).execute();

        //Adding new project to list
        parser.parseInput("--project --name Project 1 --description regarding hospital task --involve " +
            "Tom, Lucy --client MOH --startdate 11/11/2020 --duedate 12/12/2020 --incharge Derek --email zhenquan38@gmail.com", ui, projects, storage).execute();

        //No matches found
        parser.parseInput("--find something", ui, projects, storage).execute();

        thrown.expect(ArrayIndexOutOfBoundsException.class);
        parser.parseInput("--find", ui, projects, storage).execute();
        parser.parseInput("--find   ", ui, projects, storage).execute();

        //Empty find or replace words
        parser.parseInput("--find --replace", ui, projects, storage).execute();
        parser.parseInput("--find --replace abc", ui, projects, storage).execute();
        parser.parseInput("--find Lucy --replace", ui, projects, storage).execute();

        //Find successfully
        parser.parseInput("--find Lucy", ui, projects, storage).execute();

        //Find and replace successfully
        parser.parseInput("--find Lucy --replace Lilian", ui, projects, storage).execute();
    }
}
