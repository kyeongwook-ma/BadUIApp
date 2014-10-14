package selab.dev.baduiapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import selab.dev.baduiapp.R;

/**
 * Created by makyungjae on 2014. 10. 14..
 */
public class RythmGameActivity extends BaseActivity implements View.OnClickListener {

    private Button fakeSpace, B,C;
    private TextView tvInstruction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void setDestination() {
        missionMonitor.setDestination(this, MovingBallActivity.class);
    }

    @Override
    protected List<Object> makeExpectValue() {
        List<Object> expectedValue = new ArrayList<Object>();
        expectedValue.add(fakeSpace.getText().toString());
        expectedValue.add(B.getText().toString());
        expectedValue.add(C.getText().toString());
        return expectedValue;
    }

    @Override
    protected void initView() {
        setContentView(R.layout.rythm_acrivity);

        fakeSpace = (Button)findViewById(R.id.fake_space);
        fakeSpace.setOnClickListener(this);

        B = (Button)findViewById(R.id.fake_b);
        B.setOnClickListener(this);

        C = (Button)findViewById(R.id.fake_c);
        C.setOnClickListener(this);


        tvInstruction = (TextView)findViewById(R.id.tv_inst);
        tvInstruction.setTextSize(40);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fake_space:
                missionMonitor.addValue(fakeSpace.getText().toString());
                break;

            case R.id.fake_b:
                missionMonitor.addValue(B.getText().toString());
                break;
            case R.id.fake_c:
                missionMonitor.addValue(C.getText().toString());
                break;

        }


    }
}