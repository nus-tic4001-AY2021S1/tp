package seedu.itlogger;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeleteFunctionTest {
    @Test
    public void deleteFunctionTest() {
        IssueList issueList = new IssueList();
        try {
            issueList.addIssue(new Defect("cannot launch", "new", 1,
                    new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2020"),
                    "Jack"));
            issueList.addIssue(new Defect("cannot login", "new", 1,
                    new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2020"),
                    "Jack"));
            issueList.addIssue(new Defect("cannot exit", "new", 1,
                    new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2020"),
                    "Jack"));
        } catch (Exception e) {
            System.out.println(e);
        }
        //checks number of tasks created.
        assertEquals(3, issueList.getSize());

        //deletes the first item
        issueList.deleteIssue(0);
        //check that title of the next item
        assertEquals("cannot login", issueList.getDefect(0).title);
        //deletes the next item which is now index 0
        issueList.deleteIssue(0);
        //check that title of the next item
        assertEquals("cannot exit", issueList.getDefect(0).title);
        //deletes the next item which is now index 0
        issueList.deleteIssue(0);
        //checks number of tasks left.
        assertEquals(0, issueList.getSize());

    }
}

