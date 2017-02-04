package main.scene;

import database.SqlRetrieveData;
import entity.Note;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by 2e3cr on 11/1/2017.
 */
public class NotePageController implements Initializable{

    private ArrayList<Note> noteArr = new ArrayList<Note>();

    @FXML
    private Label noteTitle;

    private Note note;

    @FXML
    private TextArea noteContent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
        noteTitle.setText(note.getTitle());
        noteContent.setText(note.getContent());
    }
}
