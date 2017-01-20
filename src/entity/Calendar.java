package entity;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.GregorianCalendar;

/**
 * Created by Liu Woon Kit on 23/11/2016.
 */
public class Calendar {
    private GregorianCalendar date = new GregorianCalendar();

    private final String[] monthOfYear = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private final int currentDay = date.get(GregorianCalendar.DAY_OF_MONTH);
    private final int currentMonth = date.get(GregorianCalendar.MONTH);
    private final int currentYear = date.get(GregorianCalendar.YEAR);

    private int selectedDay = date.get(GregorianCalendar.DAY_OF_MONTH);
    private int selectedMonth = date.get(GregorianCalendar.MONTH);
    private int selectedYear = date.get(GregorianCalendar.YEAR);

    private int firstDay;
    private int lastDayOfMonth;

    private String currentMode = "DayPicker";

    //Accessor & Mutator methods from here
    public int getSelectedDay() {
        return date.get(GregorianCalendar.DAY_OF_MONTH);
    }

    public void setSelectedDay(int selectedDay) {
        date.set(GregorianCalendar.DAY_OF_MONTH, selectedDay);
    }

    public int getSelectedMonth() {
        return (date.get(GregorianCalendar.MONTH) + 1);
    }

    public void setSelectedMonth(int selectedMonth) {
        date.set(GregorianCalendar.MONTH, selectedMonth - 1);
    }

    public int getSelectedYear() {
        return date.get(GregorianCalendar.YEAR);
    }

    public void setSelectedYear(int selectedYear) {
        date.set(GregorianCalendar.YEAR, selectedYear);
    }
    //end here

    public int getFirstDay() {
        date.set(GregorianCalendar.DAY_OF_MONTH, 1);
        return date.get(GregorianCalendar.DAY_OF_WEEK);
    }

    public int getLastDayOfMonth() {
        return lastDayOfMonth = date.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
    }

    public void setCurrentMode(String currentMode) {
        this.currentMode = currentMode;
    }

    public String getCurrentMode() {
        return currentMode;
    }

    public String getStringDate() {
        String x = monthOfYear[date.get(GregorianCalendar.MONTH)];
        String StringDate = x + " " + date.get(GregorianCalendar.YEAR);
        return StringDate;
    }

    public void setSelectedButton (int tempNum) {
        if(currentMode == "DayPicker") {
            int x = date.get(GregorianCalendar.MONTH);
            x += tempNum;
            date.set(GregorianCalendar.MONTH, x);
        }

        else if(currentMode == "MonthPicker") {
            int x = date.get(GregorianCalendar.YEAR);
            x += tempNum;
            date.set(GregorianCalendar.YEAR, x);
        }
    }
}