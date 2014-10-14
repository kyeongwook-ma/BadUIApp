package selab.dev.baduiapp.activitty;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import selab.dev.baduiapp.R;

/**
 * Created by makyungjae on 2014. 10. 14..
 */
public class RythmGameActivity extends Activity implements View.OnClickListener {

    private Button fakeSpace, A,B,C,D;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rythm_acrivity);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fake_space:
            case R.id.fake_a:
            case R.id.fake_b:
            case R.id.fake_c:
            case R.id.fake_d:

        }
    }
}