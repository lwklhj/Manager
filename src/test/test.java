package test;

import database.SqlDDL;
import database.SqlRetrieveData;
import database.SqlUpdateData;
import email.RetriveEmail;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.Calendar;
import util.MusicPlayer;
import util.Util;

/**
 * Created by hehef on 12/18/2016.
 */
public class test {
    private static String[] daysName={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};;
    public static void main(String[] args) {
        getDays();


        //dayof week
        //today yesterday 1 more day 1 more day Last week,older
    }
    private static void getDays(){
        //today yesterday this week,last week ,older
        ArrayList<Date> dates=new ArrayList<Date>();
        Calendar calendar=Calendar.getInstance();

        for(int i=calendar.get(Calendar.DAY_OF_WEEK);i>=0;i--){
            System.out.println("index "+calendar.get(Calendar.DAY_OF_WEEK)+"  "+daysName[calendar.get(Calendar.DAY_OF_WEEK)-1]);
            calendar.add(Calendar.DATE,-1);

        }

    }

}
