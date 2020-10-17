package seedu.tracker.common;

import java.util.ArrayList;

public class utils {

    private static ArrayList<String> newProject;
    //ArrayList<String> newProject = new ArrayList<>();
    public utils(){
        newProject.add("name");
        newProject.add("description");
        newProject.add("involve");
        newProject.add("client");
        newProject.add("startdate");
        newProject.add("duedate");
        newProject.add("incharge");
        newProject.add("email");
    }


    public static ArrayList<String> getNewProject() {
        return newProject;
    }

}
