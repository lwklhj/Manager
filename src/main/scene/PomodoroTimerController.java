package main.scene;

import javafx.application.Platform;
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

    // Do the timer
    public void startTimer() {
        // Check if there is already a running instance of timer
        if (isTimerRunning == true) {
            isTimerRunning = false;
            timerButton.setText("Start");

            timer.cancel();
            // Do not continue with the rest of the method
            return;
        }
        else if (isTimerRunning == false) {
            isTimerRunning = true;
            timerButton.setText("Stop");

            timer = new Timer();
        }

        // Placed "0" to prevent empty field
        minutes = parseInt("0" + minutesField.getText());
        seconds = parseInt("0" + secondsField.getText());

        // Why no work?
        // Cause the value I am entering is not less than 0, LOL
        if (seconds < 0)
            seconds = 60;

        timer.schedule(new TimerTask() {
            final int dueTime = minutes * 60 + seconds;
            int totalTimePassed = 0;
            int remainingTime;

            // Float is accurate right?
            double arcPercentage;

            @Override
            public void run() {
                Platform.runLater(() -> {
                    // When to stop timer
                    if (totalTimePassed == dueTime) {
                        isTimerRunning = false;
                        timerButton.setText("Start");
                        System.out.println("Done");

                        timer.cancel();
                        playSound();
                    }

                    // Set display timer
                    remainingTime = dueTime - totalTimePassed;
                    minutesField.setText(remainingTime / 60 + "");
                    secondsField.setText(remainingTime % 60 + "");

                    // Calculate angle
                    arcPercentage = ((double) totalTimePassed / dueTime) * 360;
                    arc.setLength(arcPercentage);

                    totalTimePassed++;

                });
            };
        }, 0, 1000);

    }

    // Play Alarm
    public void playSound() {

        Timer soundTimer = new Timer();

        soundTimer.schedule(new TimerTask() {
            int soundCounter = 0;
            boolean soundPlaying = false;

            @Override
            public void run() {
                if(soundPlaying == false) {
                    musicPlayer = new MusicPlayer("Alarm.mp3");
                    soundPlaying = true;
                }

                if(soundCounter == 4) {
                    musicPlayer.stopMusicPlayer();
                    soundTimer.cancel();
                }

                soundCounter++;
                System.out.println(soundCounter);
            }
        }, 0,1000);
    }

}
