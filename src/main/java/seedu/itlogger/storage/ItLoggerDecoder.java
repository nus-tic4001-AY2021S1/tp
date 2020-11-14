package seedu.itlogger.storage;

import seedu.itlogger.Defect;
import seedu.itlogger.IssueList;
import seedu.itlogger.Parser;


import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class ItLoggerDecoder {

    /**
     * Decodes {@code encodedIssueList} into an {@code Vector of Defects } containing the decoded defects.
     *
     * @throws StorageFile.StorageOperationException if any of the fields in any encoded defect string is invalid.
     */
    public static Vector<Defect> decodeIssueList(List<String> encodedIssueList)
            throws StorageFile.StorageOperationException {
        final Vector<Defect> decodedDefects = new Vector<>();
        for (String encodedDefects : encodedIssueList) {
            decodedDefects.add(decodedDefectsFromSting(encodedDefects));
        }
        return decodedDefects;
    }

    /**
     * Decodes {@code encodedDefects } into a {@code Defect}.
     *
     * @throws StorageFile.StorageOperationException if any field in the {@code encodedDefect} is invalid.
     */
    private static Defect decodedDefectsFromSting(String encodedDefects)
            throws StorageFile.StorageOperationException {

        final Parser parser = new Parser();
        String title = null;
        String status = null;
        String owner = null;
        int severity = 0;
        Date deadLine = null;

        try {
            title = parser.parseTitle(encodedDefects);
            status = parser.parseStatus(encodedDefects);
            severity = parser.parseSeverity(encodedDefects);
            deadLine = parser.parseDeadline(encodedDefects);
            owner = parser.parseOwner(encodedDefects);
        } catch (ParseException e) {
            throw new StorageFile.StorageOperationException("File contents in wrong format");
        } catch (StringIndexOutOfBoundsException e) {
            throw new StorageFile.StorageOperationException("File contents in wrong format."
                    + "Pls check the formatting in txt file ");
        }
        return new Defect(title, status, severity, deadLine, owner);
    }

}
