package main.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import entity.Note;
import javafx.stage.Stage;

/**
 * Created by 2e3cr on 20/1/2017.
 */
public class NewNoteController implements Initializable{

    @FXML
    private Button saveButton;

    @FXML
    private TextField title;

    @FXML
    private TextArea content;

    private String currentGroup;




    @FXML
    void save(ActionEvent event) {
        Note n = new Note(currentGroup, title.getText(), content.getText(),false);
        n.storeData();
        Stage currentWindow = (Stage) saveButton.getScene().getWindow();
        currentWindow.close();
}


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setCurrentGroup(String currentGroup) {
        this.currentGroup = currentGroup;
    }
}
