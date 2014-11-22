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

        GestureDetector.SimpleOnGestureListener ls = new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                LogUtil.writeBMLog("Feed", TouchMode.DOUBLE_TOUCH);
                startActivity(new Intent(FeedActiviy.this, MovingBallActivity.class));
                finish();
                return true;
            }
        };

        pome = (ImageView)findViewById(R.id.iv_pome);
        pome.setOnClickListener(this);
        pome.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {
                switch (dragEvent.getAction()) {

                    case DragEvent.ACTION_DRAG_STARTED:

                        LogUtil.writeBMLog("Pome", TouchMode.DRAG);
                        break;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        break;

                    case DragEvent.ACTION_DRAG_EXITED:
                        break;

                    case DragEvent.ACTION_DROP:
                        LogUtil.writeBMLog("Pome", TouchMode.DROP);
                        break;

                    case DragEvent.ACTION_DRAG_ENDED:

                    default:
                        break;
                }
                return true;
            }
        });

        meat = (ImageView)findViewById(R.id.iv_feed);
        meat.setTag("Meat");
        meat.setOnClickListener(this);
        meat.setOnLongClickListener(new DragClickListener());

        button = (ImageView)findViewById(R.id.imageView);

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



}