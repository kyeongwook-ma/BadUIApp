package selab.dev.baduiapp.test;

import junit.framework.TestCase;

import java.io.IOException;

import selab.dev.baduiapp.db.DBExporter;
import selab.dev.baduiapp.db.DBHelper;

/**
 * Created by se on 2014-11-21.
 */
public class ExportTest extends TestCase {

    public void export() {
        try {
            DBExporter.exportDB("163.239.27.31", DBHelper.DB_NAME);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
