package seedu.tracker.project;

import java.security.PublicKey;

public class NewProject extends Project {

    public NewProject(String Description){
        super(Description);
    }

    @Override
    public String toString() {
       return description;
    }
}
