package game.base;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * Created by hehef on 2017/1/21.
 */
public abstract class GameScreen extends Canvas{
    protected Scene currentScene;
    private Timeline timeline;
    private KeyFrame keyFrame;
    private Duration duration=Duration.seconds(0.017);//1 sec =1000 millis ,for 1sec 60 frame 16 appro 17
                                                        //0.03update every 30ms
    //AnimationTimer loop;

    public GameScreen(double width,double height){
        super(width,height);
        loadContent();
        initTimeLine();

        //initiation();
    }
    private void initTimeLine(){
        timeline=new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);

        keyFrame=new KeyFrame(duration, new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                getGraphicsContext2D().clearRect(0,0,getWidth(),getHeight());
                draw(getGraphicsContext2D());
                update(System.nanoTime());
            }
        });
        timeline.getKeyFrames().add(keyFrame);

        /*loop=new AnimationTimer() {
            @Override
            public void handle(long now) {
                getGraphicsContext2D().clearRect(0,0,getWidth(),getHeight());
                draw(getGraphicsContext2D());
                update(now);
            }
        };*/
    }
    protected abstract void initiation();
    protected abstract void loadContent();
    protected abstract void draw(GraphicsContext gc);
    protected abstract void update(long currentTime);
    public void start(){
        timeline.play();

    }
    public void pause(){timeline.pause();}
    public void stop(){timeline.stop();}

    public Scene getCurrentScene() {
        return currentScene;
    }

    public void setCurrentScene(Scene currentScene) {
        this.currentScene = currentScene;
    }
}
