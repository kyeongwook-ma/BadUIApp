package selab.dev.baduiapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

/**
 * Created by makyungjae on 2014. 10. 14..
 */
public abstract class BaseActivity extends Activity {

    protected List<Object> expectedValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initView();
        super.onCreate(savedInstanceState);

        /* 실험의 상태 모니터링 (현재 이용 안함) */
        expectedValue = makeExpectValue();
        setDestination();

    }

    protected abstract void initView();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem item = menu.add(0,1,0, "1번째 실험");
        menu.add(0,2,0,"2번째 실험");
        menu.add(0,3,0, "3번째 실험");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case 1:
                startActivity(new Intent(this, RythmGameActivity.class));
                finish();
                break;

            case 2:
                startActivity(new Intent(this, FeedActiviy.class));
                finish();
                break;

            case 3:
                startActivity(new Intent(this, MovingBallActivity.class));
                finish();
                break;
        }

        return false;
    }

    protected abstract void setDestination();

    protected abstract List<Object> makeExpectValue();
}