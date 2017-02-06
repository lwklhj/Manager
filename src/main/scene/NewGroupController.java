package main.scene;

import database.SqlRetrieveData;
import database.SqlStoreData;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by 2e3cr on 24/1/2017.
 */
public class NewGroupController implements Initializable {

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;

    @FXML
    private TextField enteredGroupName;

    @FXML
    void confirm(ActionEvent event) {
        SqlStoreData update = new SqlStoreData();
        update.openConnection();

        System.out.println(String.format("insert into group values(\"%s\")",enteredGroupName.getText()));
        try {
            update.insertData(String.format("INSERT INTO groupFolder(groupName) VALUES(\"%s\")",enteredGroupName.getText()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        update.closeConnection();

        Stage currentWindow = (Stage) cancel.getScene().getWindow();
        currentWindow.close();
    }

    @FXML
    void cancel(ActionEvent event) {
        Stage currentWindow = (Stage) cancel.getScene().getWindow();
        currentWindow.close();
    }
}

