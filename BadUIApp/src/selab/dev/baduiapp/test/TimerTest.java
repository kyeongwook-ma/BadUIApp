package selab.dev.baduiapp.test;

import junit.framework.TestCase;

import selab.dev.baduiapp.util.ActivityTimer;

/**
 * Created by makyungjae on 2014. 10. 14..
 */
public class TimerTest extends TestCase {

    public void testTimer() {
        ActivityTimer timer = new ActivityTimer();
        timer.startTimer();
    }

}
