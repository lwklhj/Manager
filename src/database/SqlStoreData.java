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
    public void insertData(String tableName,String ...values){
        Statement statement=getStatement();
        if(statement!=null){
            try{
                for(String v : values){
                    //print out sql syntax
                    System.out.println("INSERT INTO "+tableName+" VALUES("+v+")");
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
}