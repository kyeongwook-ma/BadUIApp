package selab.dev.baduiapp.util;


import java.util.TimerTask;

public class ActivityTimer extends TimerTask {

    private int times = 0;
    private boolean isRunning = false;

    public ActivityTimer() {
        isRunning = true;
    }

    @Override
    public void run() {
        if(isRunning)
            ++times;
    }

    public void startTimer() {
        isRunning = true;
    }

    public void stopTimer() {
        isRunning = false;
    }

    public int getElapsedTime() {
        return times;
    }


}
