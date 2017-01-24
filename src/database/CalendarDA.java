package database;

import entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Liu Woon Kit on 24/1/2017.
 */
public class CalendarDA {
    private SqlRetrieveData sqlRetrieveData = new SqlRetrieveData();
    private User user = new UserDA().getUser();

    public boolean checkHoliday(String date) {
        sqlRetrieveData.openConnection();
        String sqlQuery = "SELECT startDate AND endDate FROM holiday WHERE school='"+user.getSchool()+"' AND '"+date+"' BETWEEN startDate AND endDate";
        ResultSet rs = sqlRetrieveData.retriveData(sqlQuery);

        try {
            if(!rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        sqlRetrieveData.closeConnection();
        return true;
    }
}
