package database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hehef on 12/18/2016.
 * this class retrive data from sql
 */
public class SqlRetrieveData extends SqlAccess{
    public SqlRetrieveData(){

    }
    public ResultSet retriveData(String query){
        //get the super class rs so it can close in super class
        //openConnection();
        ResultSet rs=getRs();

        try {
             rs= getStatement().executeQuery(query);

        }
        catch (NullPointerException e){
            

        }
        catch(SQLException e){
            System.out.println("Error:"+e.getMessage());
        }
        //closeConnection();
        return rs;
    }
    public ResultSet retriveWholeTable(String tableName){
        ResultSet rs=getRs();
        try{
            rs=getStatement().executeQuery("SELECT * FROM "+tableName);
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return rs;
    }
}
