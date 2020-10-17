package seedu.tracker.common;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.Locale;


public class TimeConverter {

    String startDate;
    String dueDate;
    String dateDiff;
    Boolean timeCheck = true;
    public TimeConverter(String startDate, String dueDate) throws ParseException {
        this.startDate=startDate;
        this.dueDate=dueDate;
        dayOfYear();
    }

    public void dayOfYear() throws ParseException {
        timeConverter(startDate);
        timeConverter(dueDate);
        int day = dayOfYear(startDate) + 365 - dayOfYear(dueDate);
        dateDiff=Integer.toString(day);

    }

    public boolean timeChecker(String time){

        String dateInString = time;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            Date date = formatter.parse(dateInString);
            System.out.println(date + " is valid date format");
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public Date timeConverter(String time) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        String dateInString = time;
        Date date = formatter.parse(dateInString);
        return date;
    }

    public int dayOfYear(String time) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        String dateInString = time;
        Date date = formatter.parse(dateInString);
        GregorianCalendar greg = new GregorianCalendar();
        greg.setTime(date);
        return greg.get(greg.DAY_OF_YEAR);
    }

    public String getDateDiff() {
        return dateDiff;
    }
}
