package main.scene;

import database.SqlRetrieveData;
import entity.Note;
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
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Created by 2e3cr on 11/1/2017.
 */
public class NoteController implements Initializable{

    private ObservableList<Note> othersArr = FXCollections.observableArrayList();
    //private ArrayList<String> groupArr = new ArrayList<String>();
    private ObservableList<String> groupArr= FXCollections.observableArrayList();

    private String currentGroup;

    @FXML
    private AnchorPane others;

    @FXML
    private VBox groupList;

    @FXML
    private Button addGroup;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        retrieveGroupFolder();
        retrieveNote(currentGroup);

        currentGroup=groupArr.get(0);
        System.out.println(currentGroup);
    }



    @FXML
    void addNewNote(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewNote.fxml"));
        Parent root=loader.load();
        NewNoteController controller=loader.<NewNoteController>getController();
        controller.setCurrentGroup(currentGroup);


        Stage stage = new Stage();

        stage.setScene(new Scene(root));

        stage.showAndWait();
        retrieveNote(currentGroup);
    }

    @FXML
    void addNewGroup(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewGroup.fxml"));
        Parent root=loader.load();
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.showAndWait();
        retrieveGroupFolder();



    }
    private void retrieveGroupFolder(){
        SqlRetrieveData r = new SqlRetrieveData();
        r.openConnection();

        ResultSet rs = r.retriveWholeTable("groupFolder");
        groupArr.clear();
        try {
            if(rs.next()) {
                while (rs.next()) {
                    groupArr.add(rs.getString("groupName"));
                }
                displayGroup();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        r.closeConnection();


    }

    private void displayGroup(){
        groupList.getChildren().clear();
        for(int i=0; i<groupArr.size(); i++){
            Button button = new Button(groupArr.get(i));
            button.setPrefSize(133, 56);
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    currentGroup=button.getText();
                    retrieveNote(currentGroup);
                }
            });
            groupList.getChildren().add(button);
        }
    }

    private void retrieveNote(String groupName){
        othersArr.clear();

        SqlRetrieveData retrieve = new SqlRetrieveData();
        retrieve.openConnection();

        ResultSet rs = retrieve.retriveData("SELECT * FROM note WHERE groupName=\""+groupName+"\"");
        try {
            while(rs.next()){
                boolean isPined=false;
                if(rs.getInt("isPined")>0) isPined=true;
                othersArr.add(new Note(rs.getString("groupName"), rs.getString("title"), rs.getString("content"),isPined));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        retrieve.closeConnection();
        displayNote();



    }
    private void displayNote(){
        others.getChildren().clear();
        double width=120;
        double height=120;
        int row=0;
        int column=0;
        int maxPerRow=7;
        //int index=0;
        for(int i = 0; i< othersArr.size(); i++){

            others.getChildren().removeAll(); //clear all button
            Button button=new Button(othersArr.get(i).getTitle());
            button.setMinWidth(100);
            button.setMaxWidth(100);
            button.setMinHeight(100);
            button.setMaxHeight(200);


            button.setOnAction(e -> {
                try {
                    openNote(others.getChildren().indexOf(button));
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            //System.out.println(String.format("posX %s posY %s",row*width,column*height));
            button.relocate(row*width,column*height);
            row++;
            if(row>=maxPerRow){
                row=0;
                column++;
            }

            others.getChildren().add(button);

        }
    }

    private void openNote(int i)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NotePage.fxml"));
        Parent root=loader.load();
        NotePageController controller = loader.<NotePageController>getController();
        controller.setNote(othersArr.get(i));
        Scene scene = new Scene(root);

        Stage stage = new Stage();

        stage.setScene(scene);

        stage.show();

    }


}
