package seedu.itlogger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.itlogger.Parser.parseStatus;
import static seedu.itlogger.Parser.parseTitle;
import static seedu.itlogger.Parser.parseSeverity;
import static seedu.itlogger.Parser.parseDeadline;
import static seedu.itlogger.Parser.parseOwner;

public class IssueListTest {

    private IssueList issueList;
    private Defect defect;
    private String input;
    private int defectNumber;

    @BeforeEach
    public void issueListTestSetup() throws Exception {
        defectNumber = 0;
        input = "ADD t/Test s/New sv/1 dl/20-10-2020 o/Tester";
        issueList = new IssueList();
        defect = new Defect(parseTitle(input), parseStatus(input),
                parseSeverity(input), parseDeadline(input), parseOwner(input));
    }

    @Test
    public void getIssueTest() {
        Vector<Defect> result = issueList.getIssue();
        assertEquals(result.size(), 0);
    }

    @Test
    public void addIssueTest() {
        issueList.addIssue(defect);
        assertEquals(issueList.getIssue().size(), 1);
    }

    @Test
    public void getDefectTest() {
        addIssueTest();
        Defect result = issueList.getDefect(defectNumber);
        assertEquals(result, defect);
    }

    @Test
    public void deleteIssueTest() {
        addIssueTest();
        issueList.deleteIssue(0);
        assertEquals(issueList.getIssue().size(), 0);
    }
}