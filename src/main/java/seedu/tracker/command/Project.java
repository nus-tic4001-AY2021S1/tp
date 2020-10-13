package seedu.tracker.command;

import seedu.tracker.project.NewProject;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

import java.util.ArrayList;

public class Project extends Command {
    public static final String word = "--project";

    public Project(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {

        //String newData = "";
        String temp;

        ArrayList newProject = new ArrayList();
        newProject.add("name");
        newProject.add("description");
        newProject.add("involve");
        newProject.add("duedate");
        newProject.add("startdate");
        newProject.add("incharge");

        String arr[] = line.split("--");
        if (arr.length == 8) {
            String newData = "";
            for(Object obj: newProject){
                for(int num = 1; num < arr.length; num++){

                    if(arr[num].contains(obj.toString())){
                        temp = "--" + arr[num];
                        newData = newData + temp;
                    }

                }
            }
            System.out.println(newData);
            projects.add(new NewProject(newData));
        }
        else{

            for (Object obj : newProject) {
                if (line.contains("--"+obj.toString())) {

                } else {
                    System.out.println("command line is missing " + obj.toString());
                }
            }
        }


    }
}
