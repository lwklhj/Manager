package entity;

import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import java.util.Date;

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
    public void storeData(){

    }
}
