package entity;

import database.DataBase;
import database.SqlStoreData;

import java.sql.SQLException;

/**
 * Created by 2e3cr on 20/1/2017.
 */
public class Note implements DataBase{

    private String group;
    private String title;
    private String content;
    private boolean isPined;

    public Note(String group, String title, String content,boolean isPined) {
        this.group = group;
        this.title = title;
        this.content = content;
        this.isPined=isPined;
    }

    public boolean isPined() {
        return isPined;
    }

    public void setPined(boolean pined) {
        isPined = pined;
    }

    public String getGroup() {return group;}

    public void setGroup(String group) {
        this.group = group;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public void updateData() {

    }

    @Override
    public void storeData() {
        SqlStoreData update=new SqlStoreData();
        update.openConnection();
        int val=0;
        if(isPined) val=1;
        try {
            update.insertData(String.format("insert into note value(\"%s\",\"%s\",\"%s\",%d)",group,title,content,val));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        update.closeConnection();
    }

    @Override
    public void retriveData() {

    }

    @Override
    public void deleteData() {

    }

}
