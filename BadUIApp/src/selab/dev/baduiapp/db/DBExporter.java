package selab.dev.baduiapp.db;

import android.os.Environment;

import java.io.File;

/**
 * Created by se on 2014-11-20.
 */
public class DBExporter {

    public static void exportDB(String addr, String dbName) {
        File sd = Environment.getExternalStorageDirectory();
        File data = Environment.getDataDirectory();

        String currDBPath = "";

        File currDB = new File(data, currDBPath);

        if(currDB.exists()) {

        }
    }

}
