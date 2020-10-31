package seedu.tracker.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class DateConverter {
    String startDate;
    String dueDate;
    String dateDiff;
    String daysLeft;

    public DateConverter(String startDate, String dueDate) throws ParseException {
        this.startDate = startDate;
        this.dueDate = dueDate;
        setDateDiff();
    }

    public DateConverter(String dueDate) throws ParseException {
        this.dueDate = dueDate;
        setDaysLeft();
    }

    public void setDateDiff() throws ParseException {
        dateConverter(startDate);
        dateConverter(dueDate);
        Float day = calculateDateDiff(startDate, dueDate);
        dateDiff = String.format("%.0f", day);
    }

    public void setDaysLeft() throws ParseException {
        String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        dateConverter(currentDate);
        dateConverter(dueDate);
        Float day = calculateDateDiff(currentDate, dueDate);
        daysLeft = String.format("%.0f", day);
    }


    public boolean dateChecker(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            Date date = formatter.parse(dateInString);
            System.out.println(date + " is valid date format");
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public void dateConverter(String dateInString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        formatter.parse(dateInString);
    }

    public float calculateDateDiff(String startDate, String endDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        float daysBetween = 0;
        try {
            Date dateBefore = formatter.parse(startDate);
            Date dateAfter = formatter.parse(endDate);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            daysBetween = (float) (difference / (1000 * 60 * 60 * 24));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daysBetween;
    }

    public String getDateDiff() {
        return dateDiff;
    }

    public String getDaysLeft() {
        return daysLeft;
    }
}
