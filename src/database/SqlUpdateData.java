package database;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by hehef on 12/18/2016.
 *  //for change data
 */
public class SqlUpdateData extends SqlAccess {
    public SqlUpdateData(){
        //SET


    }
    public void changeCellData(String tableName,String columnLabel,String value){
        Statement statement=getStatement();
        try {
            statement.executeUpdate( "UPDATE"+tableName+" SET "+columnLabel+"="+value);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    //for string data
    public void changeCellData(String tableName,String columnLabel,String value,String predicateColumn,String predicateValue){
        Statement statement=getStatement();
        try {
            System.out.println(" SET "+columnLabel+"="+value+" WHERE "+predicateColumn+"="+predicateValue);
            statement.executeUpdate( "UPDATE "+tableName+" SET "+columnLabel+"=\'"+value+"\' WHERE "+predicateColumn+"=\'"+predicateValue+"\'");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
