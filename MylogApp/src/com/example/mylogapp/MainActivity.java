package com.example.mylogapp;

import java.io.File;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	Button mStart;
	Button mStop;
	Button mClear;
	TextView mTv;
	ActivityManager mMananager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mMananager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);

		mStart = (Button) findViewById(R.id.start);
		mStop = (Button) findViewById(R.id.stop);
		mClear = (Button) findViewById(R.id.clear);
		mTv = (TextView) findViewById(R.id.text);
		mTv.setMovementMethod(new ScrollingMovementMethod());

		TextView help = (TextView) findViewById(R.id.textView1);
		help.setText("log는 /data에 저장됩니다.");

		mStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String res = mMananager.mylogOpen("");
				addText(res + "\n");
			}
		});

		mStop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String res = mMananager.mylogClose();
				addText(res + "\n");
			}
		});
		mClear.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String res = mMananager.mylogRemoveFile("");
				addText(res + "\n");
			}
		});

	}

	private void addText(String s) {
		String t = s + mTv.getText();
		mTv.setText(t);
	}

}
