package database;

import entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Liu Woon Kit on 12/1/2017.
 */
public class UserDA {
    private SqlRetrieveData sqlRetriveData = new SqlRetrieveData();
    private static User user;
    private ResultSet rs;

    
    public boolean checkLogin(String adminNo, String password) {
        sqlRetriveData.openConnection();
        String sqlQuery = "SELECT school, name, email, gender FROM user WHERE adminNo='"+adminNo+"' AND password='"+password+"' ";
        rs = sqlRetriveData.retriveData(sqlQuery);
        try {
            if(!rs.next()) {
                sqlRetriveData.closeConnection();
                System.out.println("Login unsuccessful");
                return false;
            }
            else{
                user = new User(adminNo, rs.getString("school"), rs.getString("name"), rs.getString("email"), rs.getString("gender"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlRetriveData.closeConnection();
        System.out.println("Login successful");
        return true;
    }

    
    public User getUser() {
        return user;
    }


    
    public void userLogout() {
        user.setAdminNo(null);
        user.setSchool(null);
        user.setName(null);
        user.setEmail(null);
        user.setGender(null);
    }
}
