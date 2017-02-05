package test;

import database.SqlRetrieveData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Liu Woon Kit on 16/12/2016.
 */
public class poro {
    public static void main(String[] args) {
        // create a new calendar
        Calendar cal = Calendar.getInstance();

        // print the first day of the week
        System.out.println("First day is :" + cal.getFirstDayOfWeek()+1);
        int day = cal.getFirstDayOfWeek()+1;
        switch (day) {
            case (1):
                System.out.println("Sunday");
                break;
            case (2):
                System.out.println("Monday");
                break;
            case 3:
                System.out.println("Tuesday");
                break;
            case 4:
                System.out.println("Wednesday");
                break;
            case 5:
                System.out.println("Thrusday");
                break;
            case 6:
                System.out.println("Friday");
                break;
            case 7:
                System.out.println("Saturday");
                break;
        }




    }
}
