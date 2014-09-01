package selab.dev.baduiapp;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class MainActivity extends BadUICheckActivity implements OnClickListener {

	private Button fakeChild, fakeAdult;
	private LinearLayout llChild, llAdult;
	private Spinner spChild, spAdult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();

	}

	private void initView() {

		fakeAdult = (Button)findViewById(R.id.btn_fake_adult);
		fakeChild = (Button)findViewById(R.id.btn_fake_child);
		llAdult = (LinearLayout)findViewById(R.id.ll_adult);
		llChild = (LinearLayout)findViewById(R.id.ll_child);

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, generateSeq(10));
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spAdult = (Spinner)findViewById(R.id.spin_adult);
		spAdult.setAdapter(dataAdapter);
		spAdult.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int pos, long id) {
				fakeAdult.setText(parent.getItemAtPosition(pos).toString() + "Έν");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		spChild = (Spinner)findViewById(R.id.spin_child);
		spChild.setAdapter(dataAdapter);
		spChild.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View v,
					int pos, long id) {
				fakeChild.setText(parent.getItemAtPosition(pos).toString() + "Έν");
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

	}

	private List<String> generateSeq(int maxSeq) {

		ArrayList<String> seqList = new ArrayList<String>();

		for(int i = 0; i < maxSeq; ++i) {
			seqList.add(String.valueOf(i));
		}

		return seqList;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}


}
