package selab.dev.baduiapp.activitty;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;

import selab.dev.baduiapp.TCPClient;
import selab.dev.baduiapp.db.DBHelper;
import selab.dev.baduiapp.db.SeqDBscheme;
import selab.dev.baduiapp.util.ActivityTimer;
import selab.dev.baduiapp.util.SeqHolder;

public class BadUICheckActivity extends Activity {

	private ActivityTimer timer;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startRecordSeqNum();
		timer = new ActivityTimer();
		timer.run();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		timer.stopTimer();
		int elapsedTime = timer.getElapsedTime();
		
		recordElapsedTime(SeqHolder.getCurrSeq(), elapsedTime);
		SeqHolder.saveCurrentSeq();
		
		TCPClient uploadProcess = new TCPClient("file.db");
		uploadProcess.run();
	}
	
	private void recordElapsedTime(int seq, int elapsedTime) {
		
		/* INSERT INTO SeqTable(times) VALUES (elapsedTime) WHERE _id=seq */
		final String sql = "UPDATE " + SeqDBscheme.TABLE_NAME + 
				" SET " + SeqDBscheme.COLUMN_TIME + " = " + String.valueOf(elapsedTime) +
				" WHERE " + SeqDBscheme.COLUMN_ID + "=" + String.valueOf(seq) + " ;";
		
		DBHelper.getInstance().exec(sql);
	}


	private void startRecordSeqNum() {
		int seq = SeqHolder.getCurrSeq();

		ContentValues cv = new ContentValues();
		cv.put("_id", seq);
		DBHelper.getInstance().insert(SeqDBscheme.TABLE_NAME, cv);
	}


}
