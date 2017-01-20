package main.scene;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import database.SqlAccess;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import entity.Task;

public class AddTaskController implements Initializable{
    @FXML
    private JFXComboBox<String> priority;
    @FXML
    private JFXButton save;

    @FXML
    private JFXTextField title;
    @FXML
    private JFXDatePicker dueDate;
    @FXML
    private JFXDatePicker dueTime;
    @FXML
    private JFXTextField location;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priority.getItems().addAll("Very urgent","Urgent","Normal");
    }
    @FXML private javafx.scene.control.Button saveButton;

    @FXML
    private void saveButtonAction(ActionEvent event){

        LocalTime time=dueDate.getTime();

       // System.out.print(dueTime.getTime());
        //Task task = new Task(title.getText(),dueDate.g,dueTime.getTime(),location.getText(),priority.getPromptText());
        Task task = new Task(title.getText(),new Date(12345),123,location.getText(),priority.getSelectionModel().getSelectedItem(),"160244J".toUpperCase());
        task.storeData();
        // get a handle to the stage
        Stage stage = (Stage) saveButton.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    private void setTask() {
        SqlAccess sqlAccess = new SqlAccess();
        sqlAccess.openConnection();
    }
}
