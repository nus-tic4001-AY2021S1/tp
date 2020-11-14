package seedu.itlogger.storage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import seedu.itlogger.Defect;
import seedu.itlogger.IssueList;

public class ItLoggerEncoder {

    //private static Date dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    /**
     * Encodes all the {@code Defects} in the {@code toSave} into a list of decodable and readable string presentation
     * for storage.
     */
    public static List<String> encodeItLogger(IssueList toSave) {
        final List<String> encodedDefects = new ArrayList<>();
        toSave.getIssue().forEach(defect -> encodedDefects.add(encodeDefectToString(defect)));
        return encodedDefects;
    }

    private static String encodeDefectToString(Defect defect) {
        final StringBuilder encodedDefectBuilder = new StringBuilder();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(defect.getDeadline());
        encodedDefectBuilder.append("t/").append(defect.getTitle()).append(" ");
        encodedDefectBuilder.append("s/").append(defect.getStatus()).append(" ");
        encodedDefectBuilder.append("sv/").append(defect.getSeverity()).append(" ");
        encodedDefectBuilder.append("dl/").append(strDate).append(" ");
        encodedDefectBuilder.append("o/").append(defect.getOwner());
        return encodedDefectBuilder.toString();
    }


}
