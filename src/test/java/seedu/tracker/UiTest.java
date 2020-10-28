package seedu.tracker;

import org.junit.jupiter.api.Test;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;
import seedu.tracker.project.Project;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    Ui ui = new Ui();
    ProjectList projects = new ProjectList();
    Storage storage = new Storage("projects.txt", projects, ui);

    @Test
    public void testDisplayProject() {

        Project project1=new Project("--name testing project 1 --description this is a testing project --involve Anna, Tim and Zak --startdate 10/10/2020 --duedate 12/12/2020 --incharge Alex");
        ProjectList projects= new ProjectList();
        projects.add(project1);

        String projectDetail1 = ui.displayProject(projects.get(0));


        String projectDetail2 =
                  "Project Name: testing project 1 " + "\n"
                + "Project Description: this is a testing project " + "\n"
                + "Project Team Members: Anna, Tim and Zak " + "\n"
                + "Start Date: 10/10/2020 " + "\n"
                + "Due Date: 12/12/2020 " + "\n"
                + "Person in Charge: Alex" + "\n";
        assertEquals(projectDetail1, projectDetail2);
    }

}
