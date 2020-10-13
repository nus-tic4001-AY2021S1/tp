package seedu.tracker.command;

import seedu.tracker.exception.TrackerException;
import seedu.tracker.project.Project;
import seedu.tracker.project.ProjectList;
import seedu.tracker.storage.Storage;
import seedu.tracker.ui.Ui;

public class Add extends Command {

    public static final String word = "--add";

    public Add(String line, ProjectList projects, Ui ui, Storage storage) {
        super(line, projects, ui, storage);
    }

    @Override
    public void execute() {
        try {

            String arr[]=line.split( "--");
            String firstWord = arr[1];
            String secWord = arr[2];

            String arr2[]=firstWord.split(" ",2);
            String arr3[]=secWord.split(" ", 2);
            if (arr2[1].isEmpty()) {
                throw new TrackerException("It seems that you missed out the task description!\n" +
                        "Please type in the '--inCharge <something>' format.");
            }
            else {

                // new Replace(arr2[], arr3[], projects);
                int he = Integer.parseInt(arr2[1].trim())-1;
                String line = projects.getProject().get(he).toString();

                String arr4[] = line.split("--");
                String temp2 = "";
                String temp3 ;
                for(int num = 1; num < arr4.length; num++){
                    String arr5[] = arr4[num].split(" ",2);
                    if (arr3[0].equalsIgnoreCase(arr5[0])){
                        String temp = arr5[1] + arr3[1];
                        System.out.println(temp);
                        temp2 = temp2 + temp+ " ";
                    }else{
                        temp3 = "--" + arr4[num];
                        temp2 = temp2 + temp3;
                    }
                }

                projects.set(he,new Project(temp2));

            }
        } catch (TrackerException e) {
            ui.printBorderline(e.getMessage());
        }
    }
}
