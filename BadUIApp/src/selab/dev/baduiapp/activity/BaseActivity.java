package selab.dev.baduiapp.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import selab.dev.baduiapp.db.DBHelper;
import selab.dev.baduiapp.db.SeqDBscheme;
import selab.dev.baduiapp.util.ActivityTimer;
import selab.dev.baduiapp.util.SeqHolder;

/**
 * Created by makyungjae on 2014. 10. 14..
 */
public abstract class BaseActivity extends Activity {

    private ActivityTimer timer;
    protected List<Object> expectedValue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initView();
        super.onCreate(savedInstanceState);

        timer = new ActivityTimer();
        timer.startTimer();

        expectedValue = makeExpectValue();
        setDestination();


        startRecordSeqNum();
    }

    protected abstract void initView();

    @Override
    protected void onDestroy() {
        super.onDestroy();

        recordElapsedTime(SeqHolder.getCurrSeq(), timer.getElapsedTime());
        SeqHolder.saveCurrentSeq();
    }

    private void recordElapsedTime(int seq, int elapsedTime) {

		/* INSERT INTO SeqTable(times) VALUES (elapsedTime) WHERE _id=seq */
        final String sql = "UPDATE " + SeqDBscheme.TABLE_NAME +
                " SET " + SeqDBscheme.COLUMN_TIME + " = " + String.valueOf(elapsedTime) +
                " WHERE " + SeqDBscheme.COLUMN_ID + "=" + String.valueOf(seq) + " ;";

        DBHelper.getInstance().exec(sql);
    }

    private void startRecordSeqNum() {
        int seq = SeqHolder.getCurrSeq() + 1;

        ContentValues cv = new ContentValues();
        cv.put("_id", seq);
        DBHelper.getInstance().insert(SeqDBscheme.TABLE_NAME, cv);
    }

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