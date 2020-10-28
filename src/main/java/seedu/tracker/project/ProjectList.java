package seedu.tracker.project;

import java.util.ArrayList;

/**
 * Responsible for methods regarding the project list. This class will use an ArrayList inside it.
 */
public class ProjectList {
    private final ArrayList<Project> projects;

    public ProjectList() { this.projects = new ArrayList<>(); }

    public int size() {
        return projects.size();
    }

    public void add(Project project) {
        projects.add(project);
    }

    public void set(int number, Project obj){
        projects.set(number,obj);
    }

    public void remove(int index) {
        projects.remove(index);
    }

    public Project get(int index) { return projects.get(index); }
}