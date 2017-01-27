package main.scene;

import database.SqlDeleteData;
import database.SqlRetrieveData;

import email.RetriveEmail;
import entity.Email;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.*;

/**
 * Created by hehef on 12/6/2016.
 */
public class EmailController implements Initializable{
    User user;
    @FXML
    private ListView<String> listView;//for display in ui
    ////for email from inbox of official server,only display title
    private ObservableList<String> displayList=FXCollections.observableArrayList();//for message main body

    private ObservableList<Message> inboxMessages;
    //list for important email from own server,only for title
    private ObservableList<Email> importantMessages=FXCollections.observableArrayList();//for message main body

    // to check wether sychrom
    private boolean isSynchronize=false;

    private enum State{INBOX,IMPORTANT}
    private State currentState;

    private String[] daysName={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};

    private final ToggleGroup toolBarGroup=new ToggleGroup();

    //today yesterday,2 more day last week(start end) older==last week enddate
    private ArrayList<Date> dateForListRetrive=new ArrayList<Date>();
    private ArrayList<Integer> dateStartIndex=new ArrayList<Integer>();
    private int currButIndex;

    @FXML
    private ToolBar dateSelectorBar;

    @FXML
    private ToggleButton inboxButton;

    @FXML
    private ToggleButton  importantButton;

    @FXML
    private ToggleButton startSynchron;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        user=new User();
        user.setAdminNo("160244J");
        listView.setOnMouseClicked(events -> setIndex());


        /*importantMessages.addListener(new ListChangeListener<Email>() {
            @Override
            public void onChanged(Change<? extends Email> c) {
                displayImportantListContent(importantMessages);
            }
        });*/
        important(new ActionEvent());
        currentState=State.IMPORTANT;

    }
    //get different index in diffent list
    private void setIndex(){
        int index=0;
        switch(currentState){
            case INBOX:
                index=inboxMessages.size()-1-listView.getSelectionModel().getSelectedIndex();
               break;
            case IMPORTANT:
                index=importantMessages.size()-1-listView.getSelectionModel().getSelectedIndex();

                break;
        }
        openContextMenu(index);
    }
    private void openContextMenu(int index){
        ContextMenu contextMenu=new ContextMenu();
        //after show
        contextMenu.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

            }
        });

        switch(currentState){
            case INBOX:
                MenuItem open=new MenuItem("open");
                open.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        openMail(getEmailObject(inboxMessages.get(index)));


                    }
                });
                MenuItem add=new MenuItem("add to Important");
                add.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        Email email=getEmailObject(inboxMessages.get(index));
                        email.storeData(user.getAdminNo());
                        importantMessages.add(email);
                        //save to data base

                    }
                });
                contextMenu.getItems().addAll(open,add);

                break;
            case IMPORTANT:
                MenuItem open2=new MenuItem("open");
                open2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        openMail(importantMessages.get(index));


                    }
                });
                MenuItem delete=new MenuItem("Delete");
                delete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        //delete data
                        Email email=importantMessages.get(index);
                        SqlDeleteData sql=new SqlDeleteData();
                        sql.openConnection();
                        sql.deleteTableRow(String.format("DELETE FROM email WHERE adminNo=\"%s\" AND subject=\"%s\"",user.getAdminNo(),email.getSubject()));
                        sql.closeConnection();
                        importantMessages.remove(email);
                        displayImportantListContent(importantMessages);

                    }
                });
                contextMenu.getItems().addAll(open2,delete);
                break;
        }


        listView.setContextMenu(contextMenu);

    }


    @FXML
    void inbox(ActionEvent event) {

        retriveMail();
        displayListContent(inboxMessages);
        if(!inboxButton.isSelected())
            inboxButton.setSelected(true);
        currentState=State.INBOX;
    }

    @FXML
    void important(ActionEvent event) {
        //load date from data base
        importantMessages.clear();
        SqlRetrieveData sql=new SqlRetrieveData();
        sql.openConnection();
        ResultSet rs=sql.retriveData("SELECT subject,sender,sentDate,cc,content FROM email WHERE adminNo=\""+user.getAdminNo()+"\"");
        sql.closeConnection();
        try {

            while(rs.next()){

                Email email=new Email(
                        rs.getString("subject"),
                        rs.getString("sender"),
                        rs.getTimestamp("sentDate"),
                        rs.getString("cc"),
                        rs.getString("content"));
                importantMessages.add(email);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        displayImportantListContent(importantMessages);
        importantButton.setSelected(true);
        currentState=State.IMPORTANT;
        //listView.getContextMenu().getItems().remove(1);
    }

    @FXML
    void startSynchron(ActionEvent event) {
        //set to fasle so it can syn chron
        isSynchronize=false;
        if(inboxMessages!=null){
            inboxMessages.clear();//clear all so no need change
        }

        retriveMail();
        inboxButton.setSelected(true);
        currentState= State.IMPORTANT;

     }


    //get all message subject from message to list and display it
    private void displayListContent(ObservableList<Message> message){
        displayList.clear();
        if(message!=null) {
            for (int i = message.size() - 1; i >= 0; i--) {
                try {
                    displayList.add(message.get(i).getSubject());
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
            listView.setItems(displayList);
        }
    }
    private void displayImportantListContent(ObservableList<Email> emails){
        displayList.clear();
        if(emails!=null) {
            for (int i = emails.size() - 1; i >= 0; i--) {
                    displayList.add(emails.get(i).getSubject());
            }
            listView.setItems(displayList);
        }
    }

    //email get and init button bar
    private void retriveMail(){
        if(isSynchronize==false) {
            //to get user id and password
            TextField nameField=new TextField();
            nameField.setPromptText("Name");
            PasswordField passField=new PasswordField();
            passField.setPromptText("Password");
            passField.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    if(event.getCode()== KeyCode.ENTER) {
                        //get current window
                        Stage stage = (Stage) passField.getScene().getWindow();
                        stage.close();
                    }
                }
            });
            Button btn=new Button("ok");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    Stage stage=(Stage)btn.getScene().getWindow();
                    stage.close();

                }
            });
            //tempory solution as i alway
            //get mail username and pass
            VBox vb=new VBox();
            vb.getChildren().addAll(nameField,passField,btn);
            Scene scene=new Scene(vb);
            Stage stage=new Stage();
            stage.setScene(scene);
            stage.showAndWait();
            RetriveEmail re = new RetriveEmail(nameField.getText().toUpperCase(), passField.getText());
            re.openConnection();
            try {
                inboxMessages = FXCollections.observableArrayList(re.retriveEmail());//wrap it in observable arraylist
            }catch (NullPointerException e){
                util.Util.prln(e.getMessage());

            }
            isSynchronize=true;
        }
        initDateSelectBar();

    }

    //open email object in new window
    private void openMail(Email email){
        try {
            Stage emailContentStage=new Stage();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("emailContent.fxml"));
            Parent root=loader.load();//call initiable method during load
            EmailContentController ctl=loader.<EmailContentController>getController();

            ctl.setEmail(email);//pass message to another stage

            Scene scene=new Scene(root);
            emailContentStage.setScene(scene);
            emailContentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Email getEmailObject(Message message){
        Email email=new Email();


        try {
            //for header
            email.setSubject(message.getSubject().toString());
            Address[] froms=message.getFrom();
            String address=froms==null?null:((InternetAddress)froms[0]).getAddress();
            email.setSender(address);
            Address[] receipients=message.getAllRecipients();
            String receipientText="";
            for(Address add:receipients){

                receipientText+=add.toString().replace("\"","")+",";
            }
            email.setCc(receipientText);
            email.setSentDate(message.getSentDate());
            StringBuffer buffer=new StringBuffer();

            //for email body
            Object messageContent=message.getContent();
            if(messageContent instanceof String){
               buffer.append(messageContent.toString());

            }else if(messageContent instanceof Multipart) {
                Multipart multipart=(Multipart)messageContent;
                for(int i=0;i<multipart.getCount();i++) {
                    getMailContent(multipart.getBodyPart(i),buffer);

                }
                //getMailContent((Part) message.getContent());
            }
            email.setContent(buffer.toString());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }
    private void getMailContent(Part part,StringBuffer buffer) throws Exception{
        String contentType=part.getContentType();

        int nameIndex=contentType.indexOf("name");

        System.out.println("type content "+contentType);
        if(part.isMimeType("text/plain")){
           // System.out.println("text/plain");
            buffer.append(part.getContent().toString());

        }else if(part.isMimeType("text/html")){
            //System.out.println("text/html");
            buffer.append(part.getContent().toString());

        }else if(part.isMimeType("multipart/*")){
           // System.out.println("multipart/*");
            Multipart multipart=(Multipart)part.getContent();
            for(int i=0;i<multipart.getCount();i++){
                getMailContent(multipart.getBodyPart(i),buffer);
            }
        }else if(part.isMimeType("message/rfc822")) {
            //System.out.println("message/rfc822");
            getMailContent((Part) part.getContent(),buffer);

        }

    }

    //date button
    private void initDateSelectBar() {
        //remove all button
        dateSelectorBar.getItems().clear();
        int index = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        for(int i=inboxMessages.size()-1;i>=0;i--){
            //=inboxMessages
        }
        ToggleButton today = new ToggleButton("Today");
        today.setToggleGroup(toolBarGroup);
        today.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(10), new BorderWidths(1))));
        dateSelectorBar.getItems().add(today);
        dateForListRetrive.add(calendar.getTime());
        calendar.add(Calendar.DATE, -1);

        //if today button is monday ,then no need this button
        if (calendar.get(Calendar.DAY_OF_WEEK) != 1) {
            ToggleButton yesterday = new ToggleButton("Yesterday");
            yesterday.setToggleGroup(toolBarGroup);
            yesterday.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(1))));
            dateSelectorBar.getItems().add(yesterday);
            dateForListRetrive.add(calendar.getTime());
            calendar.add(Calendar.DATE, -1);
        }


        for (int i = calendar.get(Calendar.DAY_OF_WEEK) - 1; i >= 1; i--) {
            ToggleButton tb = new ToggleButton(daysName[calendar.get(Calendar.DAY_OF_WEEK) - 1]);
            tb.setToggleGroup(toolBarGroup);
            tb.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(1))));
            dateSelectorBar.getItems().add(tb);
            dateForListRetrive.add(calendar.getTime());
            calendar.add(Calendar.DATE, -1);
        }

        ToggleButton lastWeek = new ToggleButton("Last week");
        lastWeek.setToggleGroup(toolBarGroup);
        lastWeek.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(1))));
        dateSelectorBar.getItems().add(lastWeek);
        dateForListRetrive.add(calendar.getTime());//add last week start date
        calendar.add(Calendar.DATE, -7);
        dateForListRetrive.add(calendar.getTime());//add last week end date

        ToggleButton older = new ToggleButton("Older");
        older.setToggleGroup(toolBarGroup);
        older.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(1))));
        dateSelectorBar.getItems().add(older);
        for (Date d : dateForListRetrive) util.Util.prln(d.toString());


        //parse inboxlist to get each day or week date in index
    }


    public void setUser(User user){
        this.user=user;
    }


}
