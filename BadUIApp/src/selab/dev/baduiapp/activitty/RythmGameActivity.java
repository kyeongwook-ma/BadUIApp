package selab.dev.baduiapp.activitty;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import selab.dev.baduiapp.R;

/**
 * Created by makyungjae on 2014. 10. 14..
 */
public class RythmGameActivity extends Activity implements View.OnClickListener {

    private Button fakeSpace, A,B,C,D;
    private TextView tvInstruction;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();
    }

    private void initView() {
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

                break;

            case R.id.fake_b:

                break;
            case R.id.fake_c:

                break;

        }
    }
}