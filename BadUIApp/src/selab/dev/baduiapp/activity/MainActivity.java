package selab.dev.baduiapp.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import selab.dev.baduiapp.R;
import selab.dev.baduiapp.db.DBHelper;
import selab.dev.baduiapp.db.SeqDBscheme;
import selab.dev.baduiapp.util.ActivityTimer;
import selab.dev.baduiapp.util.SeqHolder;

public class MainActivity extends Activity{

	private TextView mainText;
    private Button btnNext;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

        /* 해당 실험 차수 sequence 기록 */
        startRecordSeqNum();

	}

    private void recordElapsedTime(int seq, int elapsedTime) {

		/* INSERT INTO SeqTable(times) VALUES (elapsedTime) WHERE _id=seq */
        final String sql = "UPDATE " + SeqDBscheme.TABLE_NAME +
                " SET " + SeqDBscheme.COLUMN_TIME + " = " + String.valueOf(elapsedTime) +
                " WHERE " + SeqDBscheme.COLUMN_ID + " = " + String.valueOf(seq) + " ;";

        DBHelper.getInstance().exec(sql);
    }

    private void startRecordSeqNum() {
        int seq = SeqHolder.getCurrSeq();

        ContentValues cv = new ContentValues();
        cv.put("_id", seq);
        DBHelper.getInstance().insert(SeqDBscheme.TABLE_NAME, cv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        recordElapsedTime(SeqHolder.getCurrSeq(), ActivityTimer.getInstance().getElapsedTime());
        SeqHolder.saveCurrentSeq();
    }

    private void initView() {

        mainText = (TextView)findViewById(R.id.tv_main);
        mainText.setText("본 어플리케이션은 사용자 인터페이스"+
        "사용성 측정을 위해 제작되었습니다.\n\n"+
        "각 실험마다 지시사항이 주어지며 이를 해결하면 다음 실험으로 넘어갑니다."+
        "총 3개의 실험이 진행되며 실험사항에 대한 사용자 행동이 기록됩니다.");

        btnNext = (Button)findViewById(R.id.btn_next);
        btnNext.setBackgroundColor(Color.parseColor("#00bfff"));
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.btn_next:
                        startActivity(new Intent(MainActivity.this, RythmGameActivity.class));
                        break;
                }
            }
        });

	}



}
