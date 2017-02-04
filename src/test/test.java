package test;

import database.SqlDDL;
import database.SqlRetrieveData;
import database.SqlUpdateData;
import email.RetriveEmail;

import javax.mail.Message;
import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import java.util.Calendar;

import game.screen.MainScreen;
import game.util.SystemConfiguration;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import util.MusicPlayer;
import util.Util;

/**
 * Created by hehef on 12/18/2016.
 */
public class test extends Application{


    @Override
    public void start(Stage primaryStage) throws Exception {

        Group group=new Group();
        Scene  scene=new Scene(group,SystemConfiguration.getStageWidth(),SystemConfiguration.getStageHeight());
        MainScreen ms=new MainScreen(SystemConfiguration.getStageWidth(),SystemConfiguration.getStageHeight());
        group.getChildren().add(ms);

        ms.start();
        ms.initiation();



        scene.setFill(Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
