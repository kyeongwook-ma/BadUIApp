package selab.dev.baduiapp.view;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

/**
 * Created by se on 2014-10-15.
 */
public class DoubleTabListener extends GestureDetector.SimpleOnGestureListener {

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        float x = e.getX();
        float y = e.getY();

        Log.d("Double Tap", "Tapped at: (" + x + "," + y + ")");

        return super.onDoubleTap(e);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return super.onDown(e);
    }
}
