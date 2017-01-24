package database;

import java.sql.SQLException;

/**
 * Created by Liu Woon Kit on 22/12/2016.
 */
public class LoginAccess {
    //Insecure connection af but its a project, so who cares?

    SqlStoreData sqlStoreData = new SqlStoreData();
    //Sign up method
    public void signUp(String name, String gender, String adminNo, String email, String password) {
        sqlStoreData.openConnection();

        sqlStoreData.insertData("user", name, gender, adminNo, email, password);
        System.out.println("Successful Registration!");

        sqlStoreData.closeConnection();
    }

    SqlRetrieveData sqlRetrieveData = new SqlRetrieveData();
    //Login check method, return true or false
    public boolean checkLogin(String username, String password) throws SQLException {
        sqlRetrieveData.openConnection();

        //default to true because why not? Should be false though, right? Halp Miss Phoon
        boolean loginCheck = true;

        //Create statement to give to SQL server
        String SQL = "SELECT * FROM user WHERE name='"+username+"' && password='"+password+"' " ;

        //give statement to hhff's method and get set
        //set false if set is empty
        if(!sqlRetrieveData.retriveData(SQL).next()) {
            loginCheck = false;
        }
        sqlRetrieveData.closeConnection();
        return loginCheck;
    }
}