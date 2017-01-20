package database;

import java.sql.*;
//not tested
/**
 * Created by hehef on 12/15/2016.
 */
public class SqlAccess {
    private final String DRIVER="com.mysql.jdbc.Driver";

    private final String DEFAULT_DATABASE_URL="jdbc:mysql://localhost:3306/manager?useSSL=false";

    private String databaseURL;
    private Connection connection=null;



    private Statement statement=null;
    private ResultSet rs;

    private String userName;
    private String password;


    public SqlAccess(){

        userName="user";
        password="12345";
        setDefaultURL();

        //register driver
        try{
            Class.forName(DRIVER);
        }catch(ClassNotFoundException e){
            System.out.println("Driver not fount :"+e.getMessage());
        }
    }
    public void setDefaultURL(){
        databaseURL=DEFAULT_DATABASE_URL;
    }
    public void setDataBaseURL(String hostIP){
        databaseURL="jdbc:mysql://"+hostIP+":3306/manager?useSSL=false";
    }
    public void setDataBaseURL(String hostIP,String port){
        databaseURL="jdbc:mysql://"+hostIP+":"+port+"/manager?useSSL=false";
    }
    public void setDataBaseURL(String hostIP,String port,String database){
        databaseURL="jdbc:mysql://"+hostIP+":"+port+"/+"+database+"?useSSL=false";
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Statement getStatement() {
        return statement;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public ResultSet getRs() {
        return rs;
    }

    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void openConnection(){
        try{
            connection= DriverManager.getConnection(databaseURL,userName,password);
            statement=connection.createStatement();

        }catch (SQLException e){
            System.out.println("Connection fail "+e.getMessage()+" either wrong password or username");
        }
    }
    public void openConnection(String userName,String password){
        try{
            connection= DriverManager.getConnection(databaseURL,userName,password);
            statement=connection.createStatement();

        }catch (SQLException e){
            System.out.println("Connection fail "+e.getMessage()+" either wrong password or username");
        }
    }
    public void openConnection(String userName,String password,String hostIP){
        databaseURL="jdbc:mysql://"+hostIP+":3306/manager?useSSL=false";
        try{
            connection= DriverManager.getConnection(databaseURL,userName,password);
            statement=connection.createStatement();

        }catch (SQLException e){
            System.out.println("Connection fail "+e.getMessage()+" either wrong password or username");
        }
    }


    public void closeConnection(){
        if(rs!=null){
            try{
                rs.close();
            } catch (Exception e){ /*do nothing*/}
        }
        if(statement!=null){
            try{
                rs.close();
            }catch (Exception e){/*do nothing*/}
        }


    }

}
