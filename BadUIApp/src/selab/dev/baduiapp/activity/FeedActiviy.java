package selab.dev.baduiapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
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
    protected String genInfoDialogMsg() {
        return "강아지에게 먹이를 주시오";
    }


    @Override
    protected void initView() {
        setContentView(R.layout.feed_activity);

        pome = (ImageView)findViewById(R.id.iv_pome);
        pome.setOnClickListener(this);

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

        final GestureDetector gestureDetector = new GestureDetector(this, ls);
        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });
        button.setOnClickListener(this);



    }

    @Override
    protected void setDestination() {
        missionMonitor.setDestination(null,null);
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
                Toast.makeText(this, "먹이를 주시오", Toast.LENGTH_SHORT).show();
                break;

            case R.id.iv_feed:
                LogUtil.writeBMLog("Feed", TouchMode.DOUBLE_TOUCH);

                Toast.makeText(this, "고기", Toast.LENGTH_SHORT).show();
                break;

            case R.id.imageView:
                meat.setVisibility(View.VISIBLE);
                break;

        }
    }
}