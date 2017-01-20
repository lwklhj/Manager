package util;

import javafx.animation.AnimationTimer;

/**
 * Created by hehef on 12/8/2016.
 */
public class PomodoroTimer extends AnimationTimer {
    private long currTime=0;
    private int elapseTime=0;



    @Override
    public void handle(long now) {
        if(currTime-now<0){
            currTime+=now;
            System.out.println(currTime);
        }



    }

}
