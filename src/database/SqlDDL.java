package database;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by hehef on 12/19/2016.
 * use to create table database,add delete column
 * for test onlys
 */
public class SqlDDL extends SqlAccess{
    public SqlDDL(){

    }
    public void createTable(String tableName,String ...columsDefination){
        String columns="";
        for(int i=0;i<columsDefination.length-2;i++){
            columns+=columsDefination[i]+",";
        }
        columns+=columsDefination[columsDefination.length-1];

        String query="CREATE TABLE "+tableName+" ("+columns+");";
        System.out.println(query);

    }
    public void deleteTable(String tableName){
        Statement statement=getStatement();
        try {
            statement.execute("DROP "+tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
