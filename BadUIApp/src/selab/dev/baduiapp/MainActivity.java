package selab.dev.baduiapp;

import selab.dev.baduiapp.db.DBHelper;
import selab.dev.baduiapp.db.SeqDBscheme;
import selab.dev.baduiapp.util.SeqHolder;
import android.content.ContentValues;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnClickListener {
	
	private Button fakeChild, fakeAdult;
	private LinearLayout llChild, llAdult;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		startRecordSeqNum();

		
		
	}
	
	@Override
	protected void onDestroy() {
		SeqHolder.saveCurrentSeq();
	}
	
	
	private void initView() {
		
		fakeAdult = (Button)findViewById(R.id.btn_fake_adult);
		fakeChild = (Button)findViewById(R.id.btn_fake_child);
		llAdult = (LinearLayout)findViewById(R.id.ll_adult);
		llChild = (LinearLayout)findViewById(R.id.ll_child);
		
	}
	
	private void startRecordSeqNum() {
		int seq = SeqHolder.getCurrentSeq();
		
		ContentValues cv = new ContentValues();
		cv.put("_id", seq);
		DBHelper.getInstance().insert(SeqDBscheme.TABLE_NAME, cv);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}


}
