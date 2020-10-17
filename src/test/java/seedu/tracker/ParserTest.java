package seedu.tracker;

import org.junit.jupiter.api.Test;
import seedu.tracker.command.Command;
import seedu.tracker.command.Delete;
import seedu.tracker.parser.Parser;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {

    @Test
    public void testParseInput(){
        Ui ui = new Ui();
        Parser parser = new Parser();
        ProjectList projects = new ProjectList();
        Storage storage = new Storage(null, projects, ui);

        Command first = parser.parseInput("--delete 123", ui, projects, storage);
        Delete second = new Delete("--delete", projects, ui, storage);
        assertEquals(Delete.class.isAssignableFrom(first.getClass()), Delete.class.isAssignableFrom(second.getClass()));
    }



}
