package main.scene;

import database.SqlRetrieveData;
import database.TaskDA;
import database.UserDA;
import entity.Calendar;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Created by hehef on 12/6/2016.
 * Updated by WK on the 15th of January 2017
 */
public class MainSceneController implements Initializable{
    // Create static FXMLLoader so that the MainSceneController does not need to keep creating new FXMLLoaders
    private static FXMLLoader fxmlLoader;

    @FXML
    private AnchorPane content;

    @FXML
    private Label taskCounter;

    @FXML
    private Label calendarCounter;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        // Set SceneSelector.fxml to a FXMLLoader and set a controller to the FXMLLoader
        fxmlLoader = new FXMLLoader(getClass().getResource("SceneSelector.fxml"));
        fxmlLoader.setController(this);
    }

    private Parent loadSceneFile(String fileName) throws  IOException{
        Parent p=FXMLLoader.load(getClass().getResource(fileName));
        return p;
    }

    @FXML
    void homeClick(ActionEvent event) throws IOException{
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SceneSelector.fxml"));
        //fxmlLoader.setController(this);
        content.getChildren().setAll((AnchorPane)fxmlLoader.load());
        updaterCounters();
    }

    @FXML
    void calendarClick(ActionEvent event) throws IOException{
        content.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("Calendar.fxml")));
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
        //content.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("email.fxml")));
    }

    @FXML
    void timerClick(ActionEvent event) throws IOException{
        content.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("PomodoroTimer.fxml")));
    }

    @FXML
    void profileClick(ActionEvent event) throws IOException {
        content.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("ProfilePage.fxml")));
    }

    @FXML
    void gpaClick(ActionEvent event) throws IOException {
        content.getChildren().setAll((AnchorPane)FXMLLoader.load(getClass().getResource("gpaCalculator.fxml")));
    }

    @FXML
    void logoutClick(ActionEvent event) throws IOException {
        //Wipe old user data
        new UserDA().userLogout();

        // Create a stage object and lock on the current stage
        Stage stage=(Stage)((Node)event.getTarget()).getScene().getWindow();

        // Close the stage
        stage.close();

        // Prepare stage
        Parent p = FXMLLoader.load(getClass().getResource("Login.fxml"));
        stage.setScene(new Scene(p));

        //Show the stage
        stage.show();
    }

    public void updaterCounters() {
        Calendar cal = new Calendar();
        TaskDA taskDA = new TaskDA();
        String date = cal.arrangeDate(cal.getCurrentYear(), cal.getCurrentMonth() + 1, cal.getCurrentDay());

        taskCounter.setText(taskDA.getTotalTasksCounter() + " tasks remaining");
        calendarCounter.setText(taskDA.getTodayTasksCounter(date) + " tasks due today");
    }
}
