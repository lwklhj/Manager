import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("/main/scene/Login.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/main/scene/email.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/main/scene/PomodoroTimer.fxml"));
        primaryStage.setTitle("Personal Manager");
        primaryStage.setScene(new Scene(root));
        //primaryStage.initStyle(StageStyle.UNDECORATED);
        //primaryStage.setResizable(false);
        //primaryStage.sizeToScene();
        //primaryStage.setWidth(root.getScene().getWidth());
        primaryStage.getIcons().add(new Image("/image/icon.png"));
        Font.loadFont(getClass().getResourceAsStream("/fonts/AMDRTG.ttf"), 20);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Light.ttf"), 15);
        primaryStage.show();
    }


    public static void main(String[] args) {

        //PomodoroTimerController timer=new PomodoroTimerController();
        //timer.start();
        launch(args);
    }
}
