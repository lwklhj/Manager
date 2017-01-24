package test;

import database.SqlRetrieveData;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Liu Woon Kit on 16/12/2016.
 */
public class poro {
    public static void main(String[] args) {
        String s="hello my butt'y";
        s=s.replace("'","''");
        System.out.println("SELECT subject,sender,sentDate,cc,content FROM email WHERE adminNo="+"160244j");

    }
}
