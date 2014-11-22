package selab.dev.baduiapp.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import selab.dev.baduiapp.R;
import selab.dev.baduiapp.db.DBExporter;
import selab.dev.baduiapp.db.DBHelper;
import selab.dev.baduiapp.util.LogUtil;
import selab.dev.baduiapp.util.TouchMode;
import selab.dev.baduiapp.view.DragClickListener;

/**
 * Created by makyungjae on 2014. 10. 14..
 */
public class MovingBallActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener {

    private ImageView ivBall, ivSpring;
    private LinearLayout dragZone;
    private boolean isSpinning = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @Override
    protected void initView() {
        setContentView(R.layout.moving_activity);

        ivBall = (ImageView)findViewById(R.id.iv_ball);
        ivBall.setTag("Ball");
        ivBall.setScaleX(0.5f);
        ivBall.setScaleY(0.5f);

        ivBall.setOnClickListener(this);
        ivBall.setOnLongClickListener(new DragClickListener());

        ivSpring = (ImageView)findViewById(R.id.iv_spring);
        ivSpring.setTag("Spring");
        ivSpring.setOnClickListener(this);

        dragZone = (LinearLayout)findViewById(R.id.ll_dragzone);
        dragZone.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {

                // Handles each of the expected events
                switch (dragEvent.getAction()) {

                    //signal for the start of a drag and drop operation.
                    case DragEvent.ACTION_DRAG_STARTED:
                        LogUtil.writeBMLog("Ball", TouchMode.DRAG);
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
                        if(isSpinning && view == findViewById(R.id.ll_dragzone)) {

                            TranslateAnimation ani = new TranslateAnimation(
                                    Animation.RELATIVE_TO_SELF, 0,
                                    Animation.RELATIVE_TO_SELF, 10,
                                    Animation.RELATIVE_TO_SELF, 1,
                                    Animation.RELATIVE_TO_SELF, -300);
                            ani.setFillAfter(true); // 애니메이션 후 이동한좌표에
                            ani.setDuration(10000); //지속시간

                            ivBall.startAnimation(ani);

                            LogUtil.writeBMLog("Ball", TouchMode.DROP);

                            try {
                                DBExporter.exportDB("163.239.27.31", DBHelper.DB_NAME);
                                showFinishDlg();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }
                        break;

                    //the drag and drop operation has concluded.
                    case DragEvent.ACTION_DRAG_ENDED:
                        break;
                    default:
                        break;
                }
                return true;
            }
        });


    }


    private void showFinishDlg() {
        AlertDialog.Builder alert_confirm = new AlertDialog.Builder(MovingBallActivity.this);
        alert_confirm.setMessage("실험을 완료하였습니다.").setCancelable(false);
        AlertDialog alert = alert_confirm.create();
        alert.show();
    }

    @Override
    protected List<Object> makeExpectValue() {

        List<Object> expectedValue = new ArrayList<Object>();
        expectedValue.add("asd");


        return expectedValue;
    }

    @Override
    protected void setDestination() {
        // missionMonitor.setDestination(this, FeedActiviy.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_ball:

                if(!isSpinning) {
                    isSpinning = true;
                    RotateAnimation anim =  new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    anim.setInterpolator(new LinearInterpolator());
                    anim.setRepeatCount(Animation.INFINITE);
                    anim.setDuration(400);

                    ivBall.startAnimation(anim);

                    LogUtil.writeBMLog("Ball", TouchMode.CLICK);

                } else {
                    ivBall.clearAnimation();
                    isSpinning = false;
                }

                break;
            case R.id.iv_spring:

                Toast.makeText(this, "This is a spring", Toast.LENGTH_SHORT).show();

                LogUtil.writeBMLog("Spring", TouchMode.CLICK);

                break;

        }
    }



    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {

            case R.id.iv_spring:

                double x = view.getX();



                break;

        }

        return false;
    }
}