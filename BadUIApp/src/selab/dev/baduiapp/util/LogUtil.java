package selab.dev.baduiapp.util;

import android.content.ContentValues;

import selab.dev.baduiapp.db.BMDBscheme;
import selab.dev.baduiapp.db.DBHelper;

public class LogUtil {

	public static void writeBMLog(String className, String mode) {

        ContentValues cv = new ContentValues();
        cv.put(BMDBscheme.COLUMN_CLASS, className);
        cv.put(BMDBscheme.COLUMN_MODE, mode);

        DBHelper.getInstance().insert(BMDBscheme.TABLE_NAME, cv);
	}
	
	
	
}
