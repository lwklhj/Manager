package main.scene;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.Loader;
import database.SqlAccess;
import database.SqlRetrieveData;
import database.SqlUpdateData;
import database.UserDA;
import entity.Note;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by 2e3cr on 11/1/2017.
 */
public class NotePageController implements Initializable{
    private String adminNo = new UserDA().getUser().getAdminNo();

    private ArrayList<Note> noteArr = new ArrayList<Note>();

    @FXML
    private Label noteTitle;

    @FXML
    private ImageView pinImage;

    @FXML
    private Button backAndSaveButt;

    private Note note;

    private boolean isPined;

    private Image unpinnedImage;

    private Image pinnedImage;

    @FXML
    private TextArea noteContent;

    @FXML
    void backAndSave(ActionEvent event) throws IOException {
        String control = null;
        FXMLLoader loader;
        loader = new FXMLLoader(getClass().getResource("ConfirmSave.fxml"));
        Parent root=loader.load();
        ConfirmSaveController controller = loader.getController();
        //controller.setControl(control);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        control=controller.getControl();
        if(control == null){


        }else if(control.equals("no save")){

            closeStage(backAndSaveButt);

        }else if(control.equals("save")){


            //SqlUpdateData update = new SqlUpdateData();
            SqlUpdateData update = new SqlUpdateData();
            update.openConnection();
            note.setPined(isPined);
            String sqlQuery = "UPDATE note SET content= '"+noteContent.getText()+"' WHERE title='"+noteTitle.getText()+"' AND adminNo='"+adminNo+"' ";
            util.Util.prln(sqlQuery);
            update.update(sqlQuery);
            int i=0;
            if(isPined) i=1;
            //util.Util.prln("UPDATE note SET isPined="+i+" WHERE title='"+noteTitle.getText()+"'");
            update.update("UPDATE note SET isPined="+i+" WHERE title='"+noteTitle.getText()+"' AND adminNo='"+adminNo+"' ");
            //update.changeCellData(" note","content","'"+noteContent.getText()+"'");
            closeStage(backAndSaveButt);

            update.closeConnection();

        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isPined = false;
        pinnedImage=new Image(getClass().getResourceAsStream("../../image/pinned.png"));
        unpinnedImage=new Image(getClass().getResourceAsStream("../../image/pin.png"));
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
        noteTitle.setText(note.getTitle());
        noteContent.setText(note.getContent());
        isPined=note.isPined();
        displayPin();
    }

    public Label getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(Label noteTitle) {
        this.noteTitle = noteTitle;
    }

    public TextArea getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(TextArea noteContent) {
        this.noteContent = noteContent;
    }

    private void closeStage(Button btn){

        Stage currentWindow = (Stage)btn.getScene().getWindow();
        currentWindow.close();
    }

    @FXML
    void pinPressed(ActionEvent event) {
        isPined=!isPined;
        displayPin();
    }

    public void displayPin(){
        if(isPined){
            pinImage.setImage(pinnedImage);
        }else{
            pinImage.setImage(unpinnedImage);
        }
    }
}
