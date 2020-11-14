package seedu.itlogger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.itlogger.Parser.parseTitle;
import static seedu.itlogger.Parser.parseStatus;
import static seedu.itlogger.Parser.parseSeverity;
import static seedu.itlogger.Parser.parseIndex;
import static seedu.itlogger.Parser.parseDeadline;
import static seedu.itlogger.Parser.parseOwner;

public class ViewFunctionTest {

    IssueList issueList = new IssueList();
    SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy", Locale.ENGLISH);

    String input1;
    Defect defect1;

    String input2;
    Defect defect2;

    String input3;
    Defect defect3;

    @BeforeEach
    public void viewFunctionTestSetup() throws ParseException {
        input1 = "ADD t/cannot launch s/new sv/1 dl/20-10-2020 o/Jack";
        defect1 = new Defect(parseTitle(input1), parseStatus(input1),
                parseSeverity(input1), parseDeadline(input1), parseOwner(input1));

        input2 = "ADD t/cannot login s/new sv/1 dl/20-10-2020 o/Jack";
        defect2 = new Defect(parseTitle(input2), parseStatus(input2),
                parseSeverity(input2), parseDeadline(input2), parseOwner(input2));

        input3 = "ADD t/cannot exit s/new sv/1 dl/20-10-2020 o/Jack";
        defect3 = new Defect(parseTitle(input3), parseStatus(input3),
                parseSeverity(input3), parseDeadline(input3), parseOwner(input3));

        issueList.addIssue(defect1);
        issueList.addIssue(defect2);
        issueList.addIssue(defect3);
    }

    @Test
    public void viewFunctionTest() {
        assertEquals(issueList.getDefect(parseIndex("/0", issueList.getSize())).toString(),
                "Title: " + "cannot launch" + System.lineSeparator()
                + "Status: " + "new" + System.lineSeparator()
                + "Severity: Level " + "1" + System.lineSeparator()
                + "Date Raised: " + dateFormat.format(Date.from(Instant.now())) + System.lineSeparator()
                + "Deadline: " + "Oct 20 2020" + System.lineSeparator()
                + "Owner: " + "Jack");

        assertEquals(issueList.getDefect(parseIndex("/1", issueList.getSize())).toString(),
                "Title: " + "cannot login" + System.lineSeparator()
                + "Status: " + "new" + System.lineSeparator()
                + "Severity: Level " + "1" + System.lineSeparator()
                + "Date Raised: " + dateFormat.format(Date.from(Instant.now())) + System.lineSeparator()
                + "Deadline: " + "Oct 20 2020" + System.lineSeparator()
                + "Owner: " + "Jack");

        assertEquals(issueList.getDefect(parseIndex("/2", issueList.getSize())).toString(),
                "Title: " + "cannot exit" + System.lineSeparator()
                + "Status: " + "new" + System.lineSeparator()
                + "Severity: Level " + "1" + System.lineSeparator()
                + "Date Raised: " + dateFormat.format(Date.from(Instant.now())) + System.lineSeparator()
                + "Deadline: " + "Oct 20 2020" + System.lineSeparator()
                + "Owner: " + "Jack");
    }

    @Test
    public void viewFunctionExceptionTest() {
        Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            issueList.getDefect(parseIndex("0", issueList.getSize())).toString();
        });

        String expectedMessage = "Index 1 out of bounds for length 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
