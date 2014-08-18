package selab.dev.baduiapp;

import selab.dev.baduiapp.db.DBHelper;
import selab.dev.baduiapp.db.SeqDBscheme;
import selab.dev.baduiapp.util.SeqHolder;
import selab.dev.baduiapp.util.SharedPrefsUtil;
import android.content.ContentValues;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnSeekCompleteListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.DragEvent;
import android.view.View;
import android.view.View.OnDragListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity implements OnSeekBarChangeListener, OnSeekCompleteListener{

	private TextView tvChildCount, tvAdultCount;
	private SeekBar sbChild, sbAdult;
	
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
		
		tvAdultCount = (TextView)findViewById(R.id.tv_adult_count);
		tvChildCount = (TextView)findViewById(R.id.tv_child_count);
		sbChild = (SeekBar)findViewById(R.id.sb_child);
		sbAdult = (SeekBar)findViewById(R.id.sb_adult);
		
		sbChild.setOnSeekBarChangeListener(this);
		sbAdult.setOnSeekBarChangeListener(this);
		
	}
	
	private void startRecordSeqNum() {
		int seq = SeqHolder.getCurrentSeq();
		
		ContentValues cv = new ContentValues();
		cv.put("_id", seq);
		DBHelper.getInstance().insert(SeqDBscheme.TABLE_NAME, cv);
	}

	@Override
	public void onSeekComplete(MediaPlayer mp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		
		switch(seekBar.getId()) {
		case R.id.sb_adult :
			tvAdultCount.setText(progress / 100 + " Έν ");
			break;
			
		case R.id.sb_child :
			tvChildCount.setText(progress / 100 + " Έν ");
			break;
		
		}
		
		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		
	}


}
