package seedu.itlogger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.itlogger.Parser.parseDeadline;
import static seedu.itlogger.Parser.parseOwner;
import static seedu.itlogger.Parser.parseSeverity;
import static seedu.itlogger.Parser.parseStatus;
import static seedu.itlogger.Parser.parseTitle;

public class DefectTest {

    String input;
    Defect defect;

    @BeforeEach
    public void defectTestSetup() throws ParseException {
        input = "ADD t/Test s/New sv/1 dl/20-10-2020 o/Tester";
        defect = new Defect(parseTitle(input), parseStatus(input),
                parseSeverity(input), parseDeadline(input), parseOwner(input));
    }

    @Test
    public void printingDefectTest() {
        // Todo -> Write Test Here
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy",Locale.ENGLISH);
        String result = "Title: " + "Test" + System.lineSeparator()
                + "Status: " + "New" + System.lineSeparator()
                + "Severity: Level " + "1" + System.lineSeparator()
                + "Date Raised: " + dateFormat.format(Date.from(Instant.now())) + System.lineSeparator()
                + "Deadline: " + "Oct 20 2020" + System.lineSeparator()
                + "Owner: " + "Tester";
        assertEquals(defect.toString(), result);
    }
}
