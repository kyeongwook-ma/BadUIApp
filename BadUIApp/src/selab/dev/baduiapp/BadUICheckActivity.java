package selab.dev.baduiapp;

import java.util.Timer;

import selab.dev.baduiapp.db.DBHelper;
import selab.dev.baduiapp.db.SeqDBscheme;
import selab.dev.baduiapp.util.SeqHolder;
import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;

public class BadUICheckActivity extends Activity {

	protected Timer timer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		startRecordSeqNum();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		SeqHolder.saveCurrentSeq();

	}


	private void startRecordSeqNum() {
		int seq = SeqHolder.getCurrentSeq();

		ContentValues cv = new ContentValues();
		cv.put("_id", seq);
		DBHelper.getInstance().insert(SeqDBscheme.TABLE_NAME, cv);
	}


}
