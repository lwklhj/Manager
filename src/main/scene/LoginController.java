package main.scene;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import database.UserDA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController implements Initializable {

    private UserDA userDA = new UserDA();

    private String password="";
    private String username="";

    @FXML
    private TextField nameTextField;

    @FXML
    private Label wrongText;

    @FXML
    private PasswordField passTextField;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    @FXML
    void login(ActionEvent event) throws SQLException {
        if(userDA.checkLogin(nameTextField.getText(), passTextField.getText()) == true){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("MainScene.fxml"));
            try {
                Parent root=loader.load();
                MainSceneController controller=loader.<MainSceneController>getController();
                FXMLLoader sceneSelectLoader=new FXMLLoader(getClass().getResource("SceneSelector.fxml"));
                sceneSelectLoader.setController(controller);
                controller.setContent((AnchorPane)sceneSelectLoader.load());
                Stage stage=(Stage)((Node)event.getTarget()).getScene().getWindow();
                controller.updaterCounters();
                Scene scene=new Scene(root);
                stage.hide();
                stage.setScene(scene);
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        else {
            wrongText.setText("Wrong username or password");
        }


    }

    @FXML
    void signUp(ActionEvent event) {



        try {
            Parent p = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));//load controller
            //use original window
           // Stage stage=(Stage)((Node)event.getTarget()).getScene().getWindow();
            Stage stage=new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Scene scene=new Scene(p);
            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.print(e);

        }




    }

    @FXML
    void cancelLogin(ActionEvent event) {
        System.exit(0);
    }

}