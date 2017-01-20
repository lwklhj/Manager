package email;
import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;
/**
 * Created by hehef on 12/22/2016.
 */
public abstract class Email {


    private String userName;
    private String password;



    public Email(){

    }
    public Email(String userName,String password){
        this.userName=userName+"@mymail.nyp.edu.sg";
        this.password=password;

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

    public abstract void openConnection();
}

