package seedu.tracker.common;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.ParseException;
import java.util.GregorianCalendar;
import java.util.Locale;


public class DateConverter {
    String startDate;
    String dueDate;
    String dateDiff;
    Boolean timeCheck = true;

    public DateConverter(String startDate, String dueDate) throws ParseException {
        this.startDate=startDate;
        this.dueDate=dueDate;
        setDateDiff();
    }

//    public void dayOfYear() throws ParseException {
//        dateConverter(startDate);
//        dateConverter(dueDate);
//        int day = dayOfYear(startDate) + 365 - dayOfYear(dueDate);
//        dateDiff=Integer.toString(day);
//    }

    public void setDateDiff() throws ParseException {
        dateConverter(startDate);
        dateConverter(dueDate);
        //String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
        Float day = calculateDateDiff(startDate,dueDate);
        dateDiff=Float.toString(day);
    }

    public boolean dateChecker(String dateInString){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
        try {
            Date date = formatter.parse(dateInString);
            System.out.println(date+ " is valid date format");
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public Date dateConverter(String dateInString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        Date date = formatter.parse(dateInString);
        return date;
    }

    public int dayOfYear(String dateInString) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy", Locale.ENGLISH);
        Date date = formatter.parse(dateInString);
        GregorianCalendar greg = new GregorianCalendar();
        greg.setTime(date);
        return greg.get(greg.DAY_OF_YEAR);
    }

    public float calculateDateDiff(String startDate,String endDate){
        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        float daysBetween=0;
        try {
            Date dateBefore = myFormat.parse(startDate);
            Date dateAfter = myFormat.parse(endDate);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            daysBetween = (difference / (1000*60*60*24));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return daysBetween;
    }
    public String getDateDiff() {
        return dateDiff;
    }
}
