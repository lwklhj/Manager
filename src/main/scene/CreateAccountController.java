package main.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import entity.User;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    private String[] monthInNumber;

    @FXML
    private ComboBox<String> month;

    @FXML
    private TextField yearField;

    @FXML
    private TextField adminNoField;

    @FXML
    private Text adminNoError;

    @FXML
    private ComboBox school;

    @FXML
    private ToggleGroup genderGroup;

    @FXML
    private TextField dayField;

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private Text emailError;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Text passMatchError;

    @FXML
    private Text passMissing;

    @FXML
    private RadioButton radioButMale;

    @FXML
    private RadioButton radioButOther;

    @FXML
    private RadioButton radioButFemale;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        month.getItems().addAll("January","Febuary","March","April","May","June","July","August","September","October","November","December");
        monthInNumber=new String[]{"01","02","03","04","05","06","07","08","09","10","11","12"};
        school.getItems().addAll("SBM", "SCL", "SDN", "SEG", "SHS", "SIT", "SIDM");
        radioButFemale.setUserData("Female");
        radioButMale.setUserData("Male");
        radioButOther.setUserData("Other");


    }
    @FXML
    void signUp(ActionEvent event) {
        //convert date to string format
        String mth="01";//set default
        try {
             mth= monthInNumber[month.selectionModelProperty().get().getSelectedIndex()];
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e);

        }
        String birthDateText=yearField.getText()+"-"+mth+"-"+dayField.getText();


            if(!String.valueOf(confirmPasswordField.getText()).equals(String.valueOf(passwordField.getText()))){
                passMatchError.setVisible(true);
                return;
            }
        System.out.println("check Password");



        if(isValidEmailAddress(emailField.getText()) == false){
            emailError.setVisible(true);
        }
        System.out.println("check Email");

        if(!(adminNoField.getText().length() == 7)) {
            adminNoError.setVisible(true);
            return;
        }
        else if (!adminNoField.getText().substring(0, 6).matches("[0-9]+")) {
                adminNoError.setVisible(true);
                return;
        }

        else if (!(Character.isLetter(adminNoField.getText().charAt(6)))) {
                adminNoError.setVisible(true);
                return;
        }

        System.out.println("check Admin Number");

        User user=new User(
                nameField.getText(),                                               //name
                genderGroup.getSelectedToggle().getUserData().toString(),         //gender
                Date.valueOf(birthDateText),                            //date in sql.Date format
                passwordField.getText(),                                        //pass
                adminNoField.getText(),                                        //adminNo
                emailField.getText());                                        //email
        user.storeData();

        try{
            Stage stage=(Stage)((Node)event.getTarget()).getScene().getWindow();
            Parent parent= FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene=new Scene(parent);
            stage.hide();

            stage.setScene(scene);
            //stage.sizeToScene();
            stage.show();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }
    @FXML
    void cancel(ActionEvent event){

    }
}