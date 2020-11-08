package moneytracker.summary;

import moneytracker.transaction.Transaction;
import moneytracker.transaction.TransactionList;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Reminder {

    /**
     * Calculates the due date of the <code>transactions</code>.
     * @param transactions list used to store the transaction information.
     * @return Due Alarm.
     */

    public static String reminder(TransactionList transactions) {
        ArrayList<Transaction> dueTmr = new ArrayList<Transaction>();
        ArrayList<Transaction> dueTheDayAfterTmr = new ArrayList<Transaction>();
        ArrayList<Transaction> trans = transactions.getTransactions();
        LocalDate ld = LocalDate.now();
        for (Transaction t: trans) {
            Period period = Period.between(ld,t.getLocalDate());
            if (period.getDays() <= 2 & period.getDays() > 1) {
                dueTheDayAfterTmr.add(t);
            } else if (period.getDays() <= 1 & period.getDays() > 0) {
                dueTmr.add(t);
            }
        }

        String str = "";
        int n = 1;
        if (dueTmr.size() != 0) {
            str += "The following transactions are due tomorrow:\n";
            for (Transaction t: dueTmr) {
                str += n + "." + t.toString() + "\n";
            }
        }
        n = 1;
        if (dueTheDayAfterTmr.size() != 0) {
            str += "The following transactions are due the day after tomorrow:\n";
            for (Transaction t: dueTheDayAfterTmr) {
                str += n + "." + t.toString() + "\n";
            }
        }
        return str;
    }
}

