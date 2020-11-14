package seedu.itlogger;

import java.util.Collections;
import java.util.Vector;

public class SortList {
    public static Vector sortList(IssueList list, int type) {
        Vector<Defect> sorted = list.getIssue();
        if (type == 0) {
            return sorted;
        } else {
            quickSort(sorted, 0, sorted.size() - 1, type);
        }
        return sorted;
    }

    public static void quickSort(Vector<Defect> toSort, int low, int high, int type) {

        if (low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(toSort, low, high, type);

            quickSort(toSort, low, pi - 1, type);  // Before pi
            quickSort(toSort, pi + 1, high, type); // After pi
        }
    }

    public static int partition(Vector<Defect> toSort, int low, int high, int type) {
        Defect pivot = toSort.get(high);

        int i = (low - 1);  // Index of smaller element

        for (int j = low; j <= high - 1; j++) {
            // If current element is smaller than the pivot
            if (compareList(toSort.get(j), pivot, type)) {
                i++;    // increment index of smaller element
                Collections.swap(toSort, i, j);
            }
        }
        Collections.swap(toSort, i + 1, high);
        return (i + 1);
    }

    public static boolean compareList(Defect a, Defect b, int type) {
        switch (type) {
        //compare severity ascending
        case 1:
            if (a.severity < b.severity) {
                return true;
            } else {
                return false;
            }
        //compare severity descending
        case 2:
            if (a.severity > b.severity) {
                return true;
            } else {
                return false;
            }
        //compare deadline ascending
        case 3:
            if (a.deadline.before(b.deadline)) {
                return true;
            } else {
                return false;
            }
        //compare deadline descending
        case 4:
            if (a.deadline.after(b.deadline)) {
                return true;
            } else {
                return false;
            }
        //compare dateRaised ascending
        case 5:
            if (a.dateRaised.before(b.dateRaised)) {
                return true;
            } else {
                return false;
            }
        //compare dateRaised descending
        case 6:
            if (a.dateRaised.after(b.dateRaised)) {
                return true;
            } else {
                return false;
            }
        default:
            return false;
        }
    }
}
