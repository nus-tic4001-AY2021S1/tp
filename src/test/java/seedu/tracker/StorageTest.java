package seedu.tracker;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.project.Project;
import seedu.tracker.ui.Ui;

public class StorageTest {
    @Test
    public void testExtractTasks() throws IOException {
        Ui ui = new Ui();
        ProjectList projects = new ProjectList();
        Storage storage = new Storage("projects.txt", projects, ui);
        ArrayList<String> lines = new ArrayList<>();

        projects.add(new Project("--name testing project 1 --description this is a testing project "
                + "--involve Anna, Tim and Zak --client Lucy --startdate 10/10/2020 "
                + "--duedate 12/12/2020 --incharge Alex --email linqing4267@gmail.com"));
        storage.updateStorage(projects);
        storage.extractProjects(lines, projects, ui, storage);
        assertEquals(projects.get(0).getDescription(), "[Incomplete] --name testing project 1 "
                + "--description this is a testing project --involve Anna, Tim and Zak "
                + "--client Lucy --startdate 10/10/2020 --duedate 12/12/2020 --incharge Alex "
                + "--email linqing4267@gmail.com");
    }
}