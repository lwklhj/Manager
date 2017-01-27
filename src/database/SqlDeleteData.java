package database;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by hehef on 12/18/2016.
 * For data definition and data manipulation
 */
public class SqlDeleteData extends SqlAccess{
    public SqlDeleteData(){

    }
    public void deleteTableColumn(){


    }
    // operator > = <
    public void deleteTableRow(String tableName,String columnLabel,String operator,String value){
        Statement statement=getStatement();
        try{
            System.out.println("DELETE FROM "+tableName+" WHERE "+columnLabel+" "+operator+" "+value);
            statement.executeUpdate("DELETE FROM "+tableName+" WHERE "+columnLabel+" "+operator+" "+value );

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public void deleteTableRow(String query){
        Statement statement=getStatement();
        try{
            System.out.println(query);
            statement.executeUpdate(query);

        }catch(SQLException e){
            System.out.println(e.getMessage());
        }

    }

}
