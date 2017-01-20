package test;

import database.SqlRetrieveData;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Liu Woon Kit on 16/12/2016.
 */
public class poro {
    public static void main(String[] args) {
        SqlRetrieveData sqlRetrieveData = new SqlRetrieveData();
        sqlRetrieveData.openConnection();
        //sqlAccess.insertData("user", "Hefei", 'M', "1720199L", "lol@malwao.com");
        //sqlAccess.closeConnection();
        ResultSet rs=sqlRetrieveData.retriveData("SELECT name,gender,adminNo,email FROM User");


        try {
            while(rs.next()){
                System.out.println(rs.getString("name")+"\t"+rs.getString("gender") +"\t" + rs.getString("adminNo") + "\t" + rs.getString("email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
