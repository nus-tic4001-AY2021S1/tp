package seedu.itlogger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Vector;

public class Search {

    public static Vector searchTitle(String searchTerm, Vector<Defect> issueList) {
        Iterator iter = issueList.iterator();
        Vector<Defect> result = new Vector<Defect>();
        while (iter.hasNext()) {
            Defect curr = (Defect)iter.next();
            if (curr.getTitle().contains(searchTerm)) {
                result.add(curr);
            }
        }

        return result;
    }

    public static Vector searchStatus(String status, Vector<Defect> issueList) {
        Iterator iter = issueList.iterator();
        Vector<Defect> result = new Vector<Defect>();
        while (iter.hasNext()) {
            Defect curr = (Defect)iter.next();
            if (curr.getStatus().contains(status)) {
                result.add(curr);
            }
        }

        return result;
    }

    public static Vector searchSeverity(String severity, Vector<Defect> issueList) {
        int parsedSeverity = Integer.parseInt(severity);
        Iterator iter = issueList.iterator();
        Vector<Defect> result = new Vector<Defect>();
        while (iter.hasNext()) {
            Defect curr = (Defect)iter.next();
            if (curr.getSeverity() == parsedSeverity) {
                result.add(curr);
            }
        }

        return result;
    }

    public static Vector searchDeadline(String deadline, Vector<Defect> issueList) throws ParseException {
        Date date = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH).parse(deadline);
        Iterator iter = issueList.iterator();
        Vector<Defect> result = new Vector<Defect>();
        while (iter.hasNext()) {
            Defect curr = (Defect)iter.next();
            if (curr.getDeadline().compareTo(date) == 0) {
                result.add(curr);
            }
        }

        return result;
    }

    public static Vector searchOwner(String owner, Vector<Defect> issueList) {
        Iterator iter = issueList.iterator();
        Vector<Defect> result = new Vector<Defect>();
        while (iter.hasNext()) {
            Defect curr = (Defect)iter.next();
            if (curr.getOwner().contains(owner)) {
                result.add(curr);
            }
        }

        return result;
    }
}
