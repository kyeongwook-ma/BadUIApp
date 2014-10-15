package selab.dev.baduiapp.activity;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by makyungjae on 2014. 10. 14..
 */
public class MissionMonitor {

    private List<Object> expectedValue;
    private List<Object> value;
    private MonitorThr monitorThread;

    public MissionMonitor(List<Object> expectedValue) {
        this.expectedValue = expectedValue;
        this.value = new ArrayList<Object>();
    }

    public void addValue(Object o) {
        this.value.add(o);
    }

    public void destoryMonitor() {
        if(monitorThread != null)
            monitorThread.interrupt();
    }

    public class MonitorThr extends Thread {

        private Activity from;
        private Class to;

        MonitorThr(final Activity from, final Class to) {
            this.from = from; this.to = to;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(expectedValue.equals(value)) {
                    from.startActivity(new Intent(from, to));
                    from.finish();
                    initValues();
                    try {
                        join();
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void setDestination(final Activity from, final Class to) {
        if(from != null && to != null) {
            monitorThread = new MonitorThr(from, to);
            monitorThread.start();
        }

    }

    private void initValues() {
        expectedValue.clear();
        value.clear();
    }


}
