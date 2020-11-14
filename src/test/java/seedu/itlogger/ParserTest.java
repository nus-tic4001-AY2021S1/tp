package seedu.itlogger;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.itlogger.Parser.parseKeyWord;

public class ParserTest {
    @Test
    public void simpleTest() {
        String testText = "ADD t/Test s/New sv/1 dl/20-10-2020 o/Tester";
        assertEquals(Parser.parseKeyWord(testText),KeyWord.ADD);
        assertEquals(Parser.parseStatus(testText),"New");
        assertEquals(Parser.parseTitle(testText),"Test");
        try {
            assertEquals(Parser.parseSeverity(testText),1);
            assertEquals(Parser.parseDeadline(testText), new SimpleDateFormat("dd/MM/yyyy").parse("20/10/2020"));
        } catch (Exception e) {
            System.out.println(e);
        }
        assertEquals(Parser.parseOwner(testText),"Tester");
    }

    @Test
    public void viewKeyWordTest() {
        String testText = "VIEW v/10";
        assertEquals(Parser.parseKeyWord(testText),KeyWord.VIEW);
        assertEquals(Parser.parseIndex(testText,10),10);
    }

    @Test
    public void deleteKeyWordTest() {
        String testText = "DELETE d/11";
        assertEquals(Parser.parseKeyWord(testText),KeyWord.DELETE);
        assertEquals(Parser.parseIndex(testText,11),11);
    }
}
