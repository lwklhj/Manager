package entity;

import java.util.GregorianCalendar;

/**
 * Created by Liu Woon Kit on 23/11/2016.
 */
public class Calendar {
    private GregorianCalendar date = new GregorianCalendar();

    private final String[] dayOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private final String[] monthOfYear = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private final int currentDay = date.get(GregorianCalendar.DAY_OF_MONTH);
    private final int currentMonth = date.get(GregorianCalendar.MONTH);
    private final int currentYear = date.get(GregorianCalendar.YEAR);

    
    private int selectedDay = date.get(GregorianCalendar.DAY_OF_MONTH);
    private int selectedMonth = date.get(GregorianCalendar.MONTH);
    private int selectedYear = date.get(GregorianCalendar.YEAR);

    private int firstDay;
    private int lastDayOfMonth;

    
    public int getCurrentDay() {
        return currentDay;
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public int getSelectedDay() {
        return date.get(GregorianCalendar.DAY_OF_MONTH);
    }

    public void setSelectedDay(int selectedDay) {
        date.set(GregorianCalendar.DAY_OF_MONTH, selectedDay);
    }

    public int getSelectedMonth() {
        
        
        return date.get(GregorianCalendar.MONTH);
    }

    public void setSelectedMonth(int selectedMonth) {
        date.set(GregorianCalendar.MONTH, selectedMonth);
    }

    public int getSelectedYear() {
        return date.get(GregorianCalendar.YEAR);
    }

    public void setSelectedYear(int selectedYear) {
        date.set(GregorianCalendar.YEAR, selectedYear);
    }

    
    public String[] getDayOfWeek() {
        return dayOfWeek;
    }

    public String[] getMonthOfYear() {
        return  monthOfYear;
    }

    public int getFirstDay() {
        date.set(GregorianCalendar.DAY_OF_MONTH, 1);
        return date.get(GregorianCalendar.DAY_OF_WEEK);
    }

    public int getLastDayOfMonth() {
        return lastDayOfMonth = date.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    }

    public String getStringDate() {
        String x = monthOfYear[date.get(GregorianCalendar.MONTH)];
        String StringDate = x + " " + date.get(GregorianCalendar.YEAR);
        return StringDate;
    }

    public String arrangeDate (int year, int month,  int day) {
        String dayTemp = day + "";
        if(day < 10)
            dayTemp = "0" + day;

        String monthTemp = month + "";
        if(month < 10)
            monthTemp = "0" + month;

        String date = year + "-" + monthTemp + "-" + dayTemp;
        return date;
    }

}