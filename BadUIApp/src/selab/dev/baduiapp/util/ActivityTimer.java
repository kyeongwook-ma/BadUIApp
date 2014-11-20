package selab.dev.baduiapp.util;


import java.util.Timer;
import java.util.TimerTask;

public class ActivityTimer extends TimerTask {

    private static ActivityTimer instance;

    static {
        instance = new ActivityTimer();
    }

    private ActivityTimer() {
        /* activity 실행시부터 msec단위 기록 */
        Timer t = new Timer();
        t.schedule(this, 0, 1);
    }

    private int times = 0;

    public static ActivityTimer getInstance() {
        if(instance != null) {
            return instance;
        } else
            return new ActivityTimer();
    }

    @Override
    public void run() {
            ++times;
    }

    public int getElapsedTime() {
        return times;
    }


}
