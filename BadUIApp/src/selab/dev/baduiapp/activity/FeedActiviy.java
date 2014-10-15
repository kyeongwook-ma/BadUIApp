package selab.dev.baduiapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import selab.dev.baduiapp.R;
import selab.dev.baduiapp.view.DragClickListener;

/**
 * Created by se on 2014-10-14.
 */
public class FeedActiviy extends BaseActivity implements View.OnClickListener {

    private ImageView pome, meat, button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }


    @Override
    protected void initView() {
        setContentView(R.layout.feed_activity);

        pome = (ImageView)findViewById(R.id.iv_pome);
        pome.setOnClickListener(this);

        meat = (ImageView)findViewById(R.id.iv_feed);
        meat.setTag("Meat");
        meat.setOnClickListener(this);
        meat.setVisibility(View.INVISIBLE);
        meat.setOnLongClickListener(new DragClickListener());

        button = (ImageView)findViewById(R.id.imageView);
        button.setOnClickListener(this);

    }

    @Override
    protected void setDestination() {
        missionMonitor.setDestination(null,null);
    }

    @Override
    protected List<Object> makeExpectValue() {
        return null;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_pome:
                break;

            case R.id.iv_feed:
                break;

            case R.id.imageView:
                meat.setVisibility(View.VISIBLE);
                break;

        }
    }
}