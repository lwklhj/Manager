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
    private boolean lockConnectionStatus;

    public void checkConnectionStatus() {
        if (lockConnectionStatus == false) {
            sqlRetrieveData.openConnection();
            lockConnectionStatus = true;
        }
    }

    public boolean checkHoliday(String date) {
        checkConnectionStatus();
        String sqlQuery = "SELECT startDate AND endDate FROM holiday WHERE school='"+user.getSchool()+"' AND '"+date+"' BETWEEN startDate AND endDate";
        ResultSet rs = sqlRetrieveData.retriveData(sqlQuery);

        try {
            if(!rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }

    public boolean checkGotTask(String date) {
        checkConnectionStatus();
        String sqlQuery = "SELECT * FROM task WHERE adminNo = '"+user.getAdminNo()+"' && dueDate = '"+date+"' ";
        ResultSet rs = sqlRetrieveData.retriveData(sqlQuery);

        try {
            if(!rs.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;
    }
}
