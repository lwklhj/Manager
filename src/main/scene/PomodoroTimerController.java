package main.scene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toString;

/**
 * Created by Liu Woon Kit on 13/12/2016.
 */
public class PomodoroTimerController implements Initializable {
    private Timer timer;
    private int minutes = 0, seconds = 0;
    private boolean timerRunning;

    @FXML
    TextField minsField, secsField;

    @FXML
    Button timerButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void startTimer() {
        if (timerRunning == false) {
            //re-instantiate object
            timer = new Timer();

            //set state to true
            timerRunning = true;
            timerButton.setText("Stop");


            //get values
            minutes = Integer.valueOf(minsField.getText());
            seconds = Integer.valueOf(secsField.getText());

            //convert time to seconds
            int totalTime = minutes * 60 + seconds;

            //set the task to be run every 1 second (1000 millisecond)
            timer.schedule(new TimerTask() {
                int timeRemaining = totalTime;

                public void run() {
                    if (timeRemaining <= 0) {
                        System.out.println("Done");
                        timer.cancel();
                        playSound();
                        timerRunning = false;
                        timerButton.setText("Start");
                    }
                    else{
                        timeRemaining--;
                        minsField.setText(String.valueOf(timeRemaining / 60));
                        secsField.setText(String.valueOf(timeRemaining % 60));

                        System.out.println("Time remaining: " + (timeRemaining / 60) + " Mins " + (timeRemaining % 60) + " Secs");
                    }
                }
            }, 0, 1000);
        }
        else {
            timer.cancel();
            timerRunning = false;
            timerButton.setText("Start");
        }
    }
    public void playSound() {
        //sound things lol
    }

}
