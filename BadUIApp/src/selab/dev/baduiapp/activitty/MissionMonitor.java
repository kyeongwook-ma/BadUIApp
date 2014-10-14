package selab.dev.baduiapp.activitty;

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

    public MissionMonitor(List<Object> expectedValue) {
        this.expectedValue = expectedValue;
        this.value = new ArrayList<Object>();
    }

    public void addValue(Object o) {
        this.value.add(o);
    }

    public void setDestination(Activity from, Class to) {
        if(isComplete()) {
            from.startActivity(new Intent(from, to));
            initValues();
        }
    }

    private void initValues() {
        expectedValue.clear();
        value.clear();
    }

    private boolean isComplete() {
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return expectedValue.equals(value);

    }
}
