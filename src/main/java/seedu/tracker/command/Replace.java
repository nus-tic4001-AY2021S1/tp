package seedu.tracker.command;

import seedu.tracker.project.Project;
import seedu.tracker.project.ProjectList;

public class Replace extends Command{
    public static final String word = "--replace";

    public Replace(String[] arr, String[] arr2, ProjectList projects){
        super(arr, arr2, projects);


    }

    @Override
    public void execute() {
        int he = Integer.parseInt(arr[1].trim())-1;
        String line = projects.getProject().get(he).toString();

        String arr3[] = line.split("--");
        String haha ;
        String temp2 = "";
        for(int num = 1; num < arr2.length; num++){
            String arr4[] = arr3[num].split(" ",2);
            if (arr2[0].equalsIgnoreCase(arr4[0])){
                String temp = arr3[num].replace(arr4[1], arr2[1]);
                System.out.println(temp);
                temp2 = temp2 + temp;
            }else{
                haha = "--" + arr3[num];
                temp2 = temp2 + haha;
            }
        }

        projects.set(he,new Project(temp2));

    }
}
