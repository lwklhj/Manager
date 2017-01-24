package main.scene;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.shape.Arc;
import util.MusicPlayer;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;

/**
 * Created by Liu Woon Kit on 13/12/2016.
 * Rewritten by Liu Woon Kit on 20/1/2017
 */

public class PomodoroTimerController implements Initializable {
    private MusicPlayer musicPlayer;
    private Timer timer;
    private int minutes, seconds;
    private boolean isTimerRunning;

    @FXML
    private Arc arc;

    @FXML
    TextField minutesField, secondsField;

    @FXML
    Button timerButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    
    public void startTimer() {
        
        if (isTimerRunning == true) {
            isTimerRunning = false;
            timerButton.setText("Start");

            timer.cancel();
            
            return;
        }
        else if (isTimerRunning == false) {
            isTimerRunning = true;
            timerButton.setText("Stop");

            timer = new Timer();
        }

        
        minutes = parseInt("0" + minutesField.getText());
        seconds = parseInt("0" + secondsField.getText());

        
        
        if (seconds < 0)
            seconds = 60;

        timer.schedule(new TimerTask() {
            final int dueTime = minutes * 60 + seconds;
            int totalTimePassed = 0;
            int remainingTime;

            
            double arcPercentage;

            @Override
            public void run() {
                
                if (totalTimePassed == dueTime) {
                    isTimerRunning = false;
                    timerButton.setText("Start");
                    System.out.println("Done");

                    timer.cancel();
                    playSound();
                }

                
                remainingTime = dueTime - totalTimePassed;
                minutesField.setText(remainingTime / 60 + "");
                secondsField.setText(remainingTime % 60 + "");

                
                arcPercentage = ((double) totalTimePassed / dueTime) * 360;
                arc.setLength(arcPercentage);

                totalTimePassed++;

            }
        }, 0, 1000);

    }

    
    public void playSound() {
        musicPlayer = new MusicPlayer("Alarm.mp3");
    }

}
