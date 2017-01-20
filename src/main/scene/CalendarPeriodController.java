package main.scene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Liu Woon Kit on 16/12/2016.
 */
public class CalendarPeriodController extends CalendarController implements Initializable {
    private int selectedDay;
    private int selectedMonth;
    private int selectedYear;

    @FXML
    Label dateLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Current date now seeing: " + selectedDay + "/" + selectedMonth + "/" + selectedYear);
        dateLabel.setText(selectedDay + " " + selectedMonth + " " + selectedYear);
    }

    public void setDate() {
        //selectedDay = arr[0];

    }

    public void addTask() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AddTask.fxml"));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.setTitle("Add Task");
        stage.setScene(scene);
        stage.show();
    }
}
