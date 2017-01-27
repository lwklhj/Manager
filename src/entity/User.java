package entity;

import database.SqlStoreData;

import java.sql.Date;

/**
 * Created by hehef on 12/16/2016.
 * Updated by Woon Kit on Friday the 13th on January 2016
 */
public class User {
    private String adminNo, school, name, email, gender, password;
    Date birthDate;

    public User() {

    }

    public User(String adminNo, String school, String name, String email, String gender){
        this.adminNo = adminNo;
        this.school = school;
        this.name=name;
        this.email=email;
        this.gender=gender;
    }

    public User(String name, String gender, Date birthDate, String password, String adminNo, String email, String school){
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
        this.password = password;
        this.adminNo = adminNo;
        this.email = email;
        this.school = school;
    }

    public String getAdminNo() {
        return adminNo;
    }

    public void setAdminNo(String adminNo) {
        this.adminNo = adminNo;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void storeData(){
        SqlStoreData update=new SqlStoreData();
        update.openConnection();

        update.insertData("user","\""+name+"\",\""+gender+"\",\""+birthDate.toString()+"\",\""+adminNo+"\",\""+email+"\",\""+password+"\",\""+school+"\" ");
        update.closeConnection();

    }
}
