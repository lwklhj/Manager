package main.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by hehef on 5/2/2017.
 */
public class MusicPlayerController implements Initializable {
    @FXML
    private Label songNameText;
    @FXML
    private Slider slider;

    @FXML
    private ListView MusicList;

    private enum STATE{PLAY,PAUSE};
    private STATE curentState=STATE.PAUSE;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void addSong(ActionEvent event) {

    }
    @FXML
    void controlButton(ActionEvent event) {


    }
}
