package seedu.tracker.command;

        import seedu.tracker.project.ProjectList;
        import seedu.tracker.ui.Ui;

/**
 * A command to print out all projects in the list.
 */
public class List extends Command {
    public static final String word = "--list";
    public static String PROJECT_SPLITER = "--";

    public List(ProjectList projects, Ui ui) {
        super(projects, ui);
    }

    @Override
    public void execute() {
        if (projects.size() == 0) {
            ui.printBorderline("It appears that you have no projects! Perhaps you should start creating one?");
            return;
        } else {
            ui.printString("Here are the projects that you currently have!\n");
            for (int i = 0; i < projects.size(); i++) {
                String[] projectDetail = projects.get(i).getDescription().split(PROJECT_SPLITER);
                ui.printString(i+1+".\n"+"Project Name:" + projectDetail[1].substring(4)+"\n"
                        + "Project description:" + projectDetail[2].substring(11)+"\n"
                        + "Project Team Members:" + projectDetail[3].substring(7)+"\n"
                        +"Start Date:" + projectDetail[5].substring(9)+"\n"
                        +"Due Date:" + projectDetail[4].substring(7)+"\n");
            }
        }
        ui.printBorderline("");
    }
}
