package database;

import entity.Task;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Liu Woon Kit on 20/1/2017.
 */
public class TaskDA {
    private String adminNo = new UserDA().getUser().getAdminNo();

    private int totalTasksCounter;
    private int todayTasksCounter;

    private SqlRetrieveData sqlRetrieveData = new SqlRetrieveData();
    private ResultSet rs;

    public int getTotalTasksCounter() {
        sqlRetrieveData.openConnection();
        rs = sqlRetrieveData.retriveData("SELECT * FROM task WHERE adminNO='"+adminNo+"' ");
        try {
            while(rs.next()) {
                totalTasksCounter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlRetrieveData.closeConnection();

        return totalTasksCounter;
    }

    public int getTodayTasksCounter(String date) {
        sqlRetrieveData.openConnection();
        rs = sqlRetrieveData.retriveData("SELECT * FROM task WHERE adminNO='"+adminNo+"' AND dueDate='"+date+"' ");
        try {
            while(rs.next()) {
                todayTasksCounter++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlRetrieveData.closeConnection();

        return todayTasksCounter;
    }

    public ArrayList<Task> getCustomTasks(String date) {
        ArrayList<Task> taskArrayList = new ArrayList<Task>();

        sqlRetrieveData.openConnection();
        String sqlQuery = "SELECT title, dueTime, location FROM task WHERE adminNo='"+adminNo+"' AND dueDate='"+date+"'";
        ResultSet rs = sqlRetrieveData.retriveData(sqlQuery);
        try {
            while(rs.next()) {
                String customTitle = rs.getString("title") + "@" + rs.getString("location");
                taskArrayList.add(new Task(customTitle, null, rs.getTime("dueTime"), null, null, null, null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlRetrieveData.closeConnection();
        return taskArrayList;
    }
}