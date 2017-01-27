package entity;

import database.SqlStoreData;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;


/**
 * Created by hehef on 2017/1/13.
 */
public class Email  {

    private String subject;
    private String sender;
    private Date sentDate;
    private String cc;
    private String content;
    public Email(){}
    public Email(String subject,String sender,Date sentDate,String cc,String content){
        this.subject=subject;
        this.sender=sender;
        this.sentDate=sentDate;
        //System.out.println(sentDate.toString());

        this.cc=cc;
        this.content=content;

    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public void storeData(String adminNo){
        SqlStoreData sql=new SqlStoreData();
        sql.openConnection();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        content=content.replace("'","''");//replce single quote with 2 sqingle quote so can insert into database
        sql.insertData("email",String.format("\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\'%s\'",adminNo,subject,sender,simpleDateFormat.format(sentDate),cc.toString(),content));//content enclose in single quote
        sql.closeConnection();

    }
    private java.sql.Date parseDate(Date date)  {
        Calendar cal=Calendar.getInstance();
        cal.setTime(date);
        int year=cal.get(Calendar.YEAR);
        int mth=cal.get(Calendar.MONTH)+1;
        int day=cal.get(Calendar.DATE);
        String dateText=year+"-"+mth+"-"+day;

        return java.sql.Date.valueOf(dateText);


    }
}
