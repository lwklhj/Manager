package main.scene;

import database.SqlStoreData;
import entity.Email;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hehef on 2017/1/10.
 */
public class EmailContentController implements Initializable {
    private Email email;

    @FXML
    private Label cc;

    @FXML
    private Label sender;

    @FXML
    private Label subject;



    @FXML
    private WebView webView;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //no scroll to hroizontal;



    }
    public void setEmail(Email email) {
        this.email=email;
        displayContent();
        Rectangle rect=new Rectangle(0,0,100,100);
        Tooltip tp=new Tooltip(email.getCc());
        tp.setPrefWidth(500);
        tp.setWrapText(true);
        //Tooltip tp= TooltipBuilder.create().text(email.getCc()).prefWidth(600).wrapText(true).build();
        cc.setTooltip(tp);
    }
    public void displayContent() {
        cc.setText(email.getCc());
        sender.setText(email.getSender()+"\t"+email.getSentDate().toString());
        subject.setText(email.getSubject());

        WebEngine webEngine=webView.getEngine();
        webEngine.loadContent(email.getContent());

    }
}





