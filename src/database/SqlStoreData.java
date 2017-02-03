package database;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by hehef on 12/18/2016.
 * this class use to insert data to sql
 */
public class SqlStoreData extends SqlAccess{

    public SqlStoreData(){

    }
    public void insertData(String tableName,String ...values) {
        //for all string value
        Statement statement=getStatement();

        if(statement!=null){
            try {
                for (String v : values) {
                    statement.executeUpdate("INSERT INTO "+tableName+" VALUES("+v+")");

                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
        }
        else{
            System.out.println("Open connection first");
        }
    }
    public void insertData(String tableName,String values) {
        Statement statement=getStatement();
        if(statement!=null){
            //System.out.println("INSERT INTO "+tableName+" VALUES("+values+")");
            try {
                statement.executeUpdate("INSERT INTO "+tableName+" VALUES("+values+")");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Open connection first");
        }
    }
    public void insertData(String query) {
        Statement statement=getStatement();
        if(statement!=null){
            try {
                statement.executeUpdate(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Open connection first");
        }
    }
}