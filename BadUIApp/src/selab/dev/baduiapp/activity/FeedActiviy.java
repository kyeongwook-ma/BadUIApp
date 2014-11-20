package selab.dev.baduiapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

import selab.dev.baduiapp.R;
import selab.dev.baduiapp.util.LogUtil;
import selab.dev.baduiapp.util.TouchMode;
import selab.dev.baduiapp.view.DragClickListener;

/**
 * Created by se on 2014-10-14.
 */
public class FeedActiviy extends BaseActivity implements View.OnClickListener {

    private ImageView pome, meat, button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }



    @Override
    protected void initView() {
        setContentView(R.layout.feed_activity);

        pome = (ImageView)findViewById(R.id.iv_pome);
        pome.setOnClickListener(this);
        pome.setOnDragListener(new MyDragListener());

        meat = (ImageView)findViewById(R.id.iv_feed);
        meat.setTag("Meat");
        meat.setOnClickListener(this);
        meat.setOnLongClickListener(new DragClickListener());

        button = (ImageView)findViewById(R.id.imageView);

        GestureDetector.SimpleOnGestureListener ls = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                LogUtil.writeBMLog("Feed", TouchMode.DOUBLE_TOUCH);
                startActivity(new Intent(FeedActiviy.this, MovingBallActivity.class));
                finish();
                return true;
            }
        };



        button.setOnClickListener(this);



    }

    @Override
    protected void setDestination() {
        // missionMonitor.setDestination(null,null);
    }

    @Override
    protected List<Object> makeExpectValue() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_pome:
                LogUtil.writeBMLog("Pome", TouchMode.DOUBLE_TOUCH);
                Toast.makeText(this, "배가 고파요", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_feed:
                LogUtil.writeBMLog("Feed", TouchMode.DOUBLE_TOUCH);

                Toast.makeText(this, "고기", Toast.LENGTH_SHORT).show();
                break;

            case R.id.imageView:
                RotateAnimation anim =  new RotateAnimation(-30, 60, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(3);
                anim.setDuration(400);
                meat.startAnimation(anim);
                break;

        }
    }

    class MyDragListener implements View.OnDragListener {
        // Drawable normalShape = getResources().getDrawable(R.drawable.normal_shape);
        // Drawable targetShape = getResources().getDrawable(R.drawable.target_shape);

        @Override
        public boolean onDrag(View v, DragEvent event) {

            // Handles each of the expected events
            switch (event.getAction()) {

                //signal for the start of a drag and drop operation.
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;

                //the drag point has entered the bounding box of the View
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;

                //the user has moved the drag shadow outside the bounding box of the View
                case DragEvent.ACTION_DRAG_EXITED:
                    break;

                //drag shadow has been released,the drag point is within the bounding box of the View
                case DragEvent.ACTION_DROP:
                    // if the view is the bottomlinear, we accept the drag item
                    if(v == findViewById(R.id.iv_pome)) {
                        startActivity(new Intent(FeedActiviy.this, MovingBallActivity.class));
                        finish();
                    }
                    break;

                //the drag and drop operation has concluded.
                case DragEvent.ACTION_DRAG_ENDED:
                    //v.setBackground(normalShape);	//go back to normal shape

                default:
                    break;
            }
            return true;
        }
    }

}