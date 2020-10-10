package seedu.tracker.project;

import java.util.ArrayList;

/**
 * Responsible for methods regarding the project list. This class will use an ArrayList inside it.
 */
public class ProjectList {
    private final ArrayList<Project> projects;

    ProjectList(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ProjectList() {
        projects = new ArrayList<>();
    }

    public int size() {
        return projects.size();
    }

    public void add(Project task) {
        projects.add(task);
    }

    public void remove(int index) {
        projects.remove(index - 1);
    }

    public Project get(int index) {
        return projects.get(index);
    }
}