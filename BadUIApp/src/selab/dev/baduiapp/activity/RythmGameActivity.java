package selab.dev.baduiapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import selab.dev.baduiapp.R;
import selab.dev.baduiapp.util.LogUtil;
import selab.dev.baduiapp.util.TouchMode;

/**
 * Created by makyungjae on 2014. 10. 14..
 */
public class RythmGameActivity extends BaseActivity implements View.OnClickListener {

    private Button fakeSpace, A,B,C,D,E;
    private TextView tvInstruction, tvCount;
    private final String[] rythmSeq = {"Space", "B", "C", "Space","A","E","A","D", "Space"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        giveInst();
    }

    private void giveInst() {
        final Timer myTimer = new Timer();
        final Handler handler = new Handler();
        final Runnable myRunnable = new Runnable() {

            int countDown = 5;
            public void run() {

                if(countDown == 0) {
                    startActivity(new Intent(RythmGameActivity.this, FeedActiviy.class));
                    finish();
                    myTimer.cancel();
                } else {
                    tvCount.setText(String.valueOf(--countDown) + " 초 후 실험이 종료됩니다.");
                }
            }
        };

        myTimer.schedule(new TimerTask() {
            @Override
            public void run() { handler.post(myRunnable);}
        }, 5000, 1000);
    }

    private void changeTargetColor(String target, int color) {
        if (target.equals("Space")) {
            fakeSpace.setBackgroundColor(color);
            A.setBackgroundColor(Color.BLACK);
            B.setBackgroundColor(Color.BLACK);
            C.setBackgroundColor(Color.BLACK);
            D.setBackgroundColor(Color.BLACK);
            E.setBackgroundColor(Color.BLACK);
        } else if(target.equals("A")) {
            A.setBackgroundColor(color);
            fakeSpace.setBackgroundColor(Color.BLACK);
            B.setBackgroundColor(Color.BLACK);
            C.setBackgroundColor(Color.BLACK);
            D.setBackgroundColor(Color.BLACK);
            E.setBackgroundColor(Color.BLACK);
        } else if(target.equals("B")) {
            B.setBackgroundColor(color);
            A.setBackgroundColor(Color.BLACK);
            fakeSpace.setBackgroundColor(Color.BLACK);
            C.setBackgroundColor(Color.BLACK);
            D.setBackgroundColor(Color.BLACK);
            E.setBackgroundColor(Color.BLACK);
        } else if(target.equals("C")) {
            C.setBackgroundColor(color);
            A.setBackgroundColor(Color.BLACK);
            B.setBackgroundColor(Color.BLACK);
            fakeSpace.setBackgroundColor(Color.BLACK);
            D.setBackgroundColor(Color.BLACK);
            E.setBackgroundColor(Color.BLACK);
        } else if(target.equals("D")) {
            D.setBackgroundColor(color);
            A.setBackgroundColor(Color.BLACK);
            B.setBackgroundColor(Color.BLACK);
            C.setBackgroundColor(Color.BLACK);
            fakeSpace.setBackgroundColor(Color.BLACK);
            E.setBackgroundColor(Color.BLACK);
        } else if(target.equals("E")) {
            E.setBackgroundColor(color);
            A.setBackgroundColor(Color.BLACK);
            B.setBackgroundColor(Color.BLACK);
            C.setBackgroundColor(Color.BLACK);
            D.setBackgroundColor(Color.BLACK);
            fakeSpace.setBackgroundColor(Color.BLACK);
        }
    }


    public void setDestination() {    }

    @Override
    protected List<Object> makeExpectValue() {
        List<Object> expectedValue = new ArrayList<Object>();


        for(String seq : rythmSeq) {
            expectedValue.add(seq);
        }

        return expectedValue;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.rythm_acrivity);

        fakeSpace = (Button)findViewById(R.id.fake_space);
        fakeSpace.setOnClickListener(this);

        A = (Button)findViewById(R.id.fake_a);
        A.setOnClickListener(this);

        B = (Button)findViewById(R.id.fake_b);
        B.setOnClickListener(this);

        C = (Button)findViewById(R.id.fake_c);
        C.setOnClickListener(this);

        D = (Button)findViewById(R.id.fake_d);
        D.setOnClickListener(this);

        E = (Button)findViewById(R.id.fake_e);
        E.setOnClickListener(this);

        tvInstruction = (TextView)findViewById(R.id.tv_inst);

        tvCount = (TextView)findViewById(R.id.tv_count);

        StringBuilder sb = new StringBuilder();

        for(String inst : rythmSeq) {
            sb.append(" " + inst + " ");
        }

        tvInstruction.setText(sb.toString());

    }

    @Override
    public void onClick(View view) {
        int colors[] = new int[] { Color.CYAN, Color.GREEN, Color.MAGENTA, Color.YELLOW };
        int rand = new Random().nextInt(colors.length);

        switch (view.getId()) {
            case R.id.fake_space:
                fakeSpace.setTextColor(colors[rand]);
                A.setTextColor(Color.WHITE);
                B.setTextColor(Color.WHITE);
                C.setTextColor(Color.WHITE);
                D.setTextColor(Color.WHITE);
                E.setTextColor(Color.WHITE);

                LogUtil.writeBMLog("Space", TouchMode.CLICK);

                break;

            case R.id.fake_a:
                A.setTextColor(colors[rand]);
                B.setTextColor(Color.WHITE);
                C.setTextColor(Color.WHITE);
                D.setTextColor(Color.WHITE);
                E.setTextColor(Color.WHITE);
                fakeSpace.setTextColor(Color.WHITE);


                LogUtil.writeBMLog("A", TouchMode.CLICK);
                break;

            case R.id.fake_b:
                B.setTextColor(colors[rand]);

                A.setTextColor(Color.WHITE);
                C.setTextColor(Color.WHITE);
                D.setTextColor(Color.WHITE);
                E.setTextColor(Color.WHITE);
                fakeSpace.setTextColor(Color.WHITE);

                LogUtil.writeBMLog("B", TouchMode.CLICK);
                break;

            case R.id.fake_c:
                C.setTextColor(colors[rand]);
                A.setTextColor(Color.WHITE);
                B.setTextColor(Color.WHITE);
                D.setTextColor(Color.WHITE);
                E.setTextColor(Color.WHITE);
                fakeSpace.setTextColor(Color.WHITE);

                LogUtil.writeBMLog("C", TouchMode.CLICK);
                break;

            case R.id.fake_d:
                D.setTextColor(colors[rand]);
                A.setTextColor(Color.WHITE);
                B.setTextColor(Color.WHITE);
                C.setTextColor(Color.WHITE);
                E.setTextColor(Color.WHITE);
                fakeSpace.setTextColor(Color.WHITE);

                LogUtil.writeBMLog("D", TouchMode.CLICK);
                break;

            case R.id.fake_e:
                E.setTextColor(colors[rand]);
                A.setTextColor(Color.WHITE);
                B.setTextColor(Color.WHITE);
                C.setTextColor(Color.WHITE);
                D.setTextColor(Color.WHITE);
                fakeSpace.setTextColor(Color.WHITE);

                LogUtil.writeBMLog("E", TouchMode.CLICK);
                break;

            case R.id.tv_inst:
                break;
        }


    }
}