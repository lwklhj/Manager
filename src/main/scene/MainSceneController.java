package main.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import util.MusicPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hehef on 12/6/2016.
 */
public class MainSceneController implements Initializable{
    @FXML
    private Pane content;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        //MusicPlayer mp=new MusicPlayer();

    }

    @FXML
    void homeClick(ActionEvent event) throws IOException{
        content.getChildren().setAll((VBox)FXMLLoader.load(getClass().getResource("home.fxml")));
    }
    private Parent loadSceneFile(String fileName) throws  IOException{
        Parent p=FXMLLoader.load(getClass().getResource(fileName));
        return p;
    }


    @FXML
    void calendarClick(ActionEvent event) throws IOException{
        content.getChildren().setAll((BorderPane)FXMLLoader.load(getClass().getResource("Calendar.fxml")));
    }

    @FXML
    void taskListClick(ActionEvent event) throws IOException {
        content.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("Tasklist.fxml")));
    }

    @FXML
    void noteClick(ActionEvent event) throws IOException{
        content.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("Note.fxml")));
    }

    @FXML
    void emailClick(ActionEvent event) throws IOException {
        content.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("email.fxml")));

    }

    @FXML
    void timerClick(ActionEvent event) throws IOException{
        content.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("PomodoroTimer.fxml")));

    }
}
