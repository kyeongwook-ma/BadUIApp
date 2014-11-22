package selab.dev.baduiapp.activity;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by se on 2014-10-22.
 */
public class InfoDialog extends AlertDialog {

    protected InfoDialog(Context context) {
        super(context);
    }

    protected InfoDialog(Context context, String msg) {
        super(context);
        setMessage(msg);

    }




}
