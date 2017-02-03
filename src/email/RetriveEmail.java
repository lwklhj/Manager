package email;

import javax.mail.*;
import java.io.IOException;
import java.util.Properties;
import javafx.scene.control.Alert;

/**
 * Created by hehef on 12/22/2016.
 * get email from microsoft exchange server
 */

public class RetriveEmail extends Email {
    //pop3
    private static String popServerName="outlook.office365.com";
    private static String popPort="995";

    //IMAP
    private static String imapServerName="outlook.office365.com";
    private static String imapPort="993";

    public enum ConnectionType{
        POP,IMAP
    }
    private Session session;
    private ConnectionType type;
    public RetriveEmail(String userName,String password){
        super(userName, password);
        type=ConnectionType.POP;
    }
    public RetriveEmail(String userName,String password,ConnectionType typeOfConnection){
        super(userName, password);
        type=typeOfConnection;

    }

    @Override
    public void openConnection() {
        Properties props=new Properties();
        switch(type){
            case POP:
                props.put("mail.pop3.starttls.enable","true");
                props.put("mail.pop3.host",popServerName);
                props.put("mail.pop3.port",popPort);
                break;
            case IMAP:
                System.out.println("undefine");

                break;
        }

        session=Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getUserName(),getPassword());
            }
        });


    }
    public Message[] retriveEmail(){
        //Message[] emailList;

        try{
            Store store=session.getStore("pop3s");
            store.connect(popServerName,getUserName(),getPassword());

            Folder inbox=store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);
            return inbox.getMessages();
            //return emailList;

        }catch (AuthenticationFailedException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("You enter wrong password or user name\nARE YOU DUMBASS\nMAYBE you school block me :(");
            alert.setHeaderText("WTF " + e.getMessage());
            alert.showAndWait();
        } catch (NoSuchProviderException e){
            System.out.println("No Such Provider");

        }catch (MessagingException e){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("No internet or school block me :(");
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();

        }
        return null;
    }
}
