package main.scene;

import database.SqlRetrieveData;
import database.SqlUpdateData;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by 2e3cr on 5/2/2017.
 */
public class ConfirmSaveController extends NotePageController implements Initializable {

    @FXML
    private Button doNotSaveButt;

    @FXML
    private Button saveButt;

    @FXML
    private Button cancelButt;

    private String control;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void doNotSave(ActionEvent event) throws IOException {

        control = "no save";
        closeStage(doNotSaveButt);
    }

    @FXML
    void save(ActionEvent event) throws IOException {
        control = "save";
        closeStage(saveButt);
        SqlRetrieveData retrieve = new SqlRetrieveData();
        retrieve.openConnection();



        retrieve.closeConnection();
    }

    @FXML
    void cancel(ActionEvent event) throws IOException {
        closeStage(cancelButt);

    }

    public void setControl(String control) {
        this.control = control;
    }

    private void closeStage(Button btn){
        Stage currentWindow = (Stage)btn.getScene().getWindow();
        currentWindow.close();

    }

    public String getControl() {
        return control;
    }
}
