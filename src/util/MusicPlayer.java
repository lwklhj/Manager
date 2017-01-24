package util;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;

/**
 * Created by hehef on 2017/1/12.
 */
public class MusicPlayer {
    public MusicPlayer(){
        String path=new File("src/media/xiao.mp3").getAbsolutePath();
        System.out.print(path);


        Media media=new Media(new File(path).toURI().toString());


        MediaPlayer mediaPlayer=new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mediaView=new MediaView(mediaPlayer);



        Stage stage=new Stage();
        Pane pane=new Pane();
        pane.getChildren().add(mediaView);
        pane.setPrefWidth(mediaPlayer.getMedia().getWidth());
        pane.setPrefHeight(mediaPlayer.getMedia().getHeight());
        BorderPane bp=new BorderPane();
        bp.setCenter(pane);
        bp.setStyle("-fx-background-color: Black");
        //bp.setBottom();
        Scene scene=new Scene(bp);
        scene.setFill(Color.BLACK);

        stage.setScene(scene);
        stage.setTitle("music");
        stage.show();

    }

    public MusicPlayer(String fileName) {
        String path = new File("src/media/" +fileName).getAbsolutePath();
        System.out.print(path);


        Media media = new Media(new File(path).toURI().toString());


        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        MediaView mediaView = new MediaView(mediaPlayer);
    }

}
