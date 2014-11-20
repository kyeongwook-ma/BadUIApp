package selab.dev.baduiapp.db;

import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

import selab.dev.baduiapp.BadUIApp;

/**
 * Created by se on 2014-11-20.
 */
public class DBExporter {

    public static void exportDB(String addr, String dbName) throws IOException {
        File data = Environment.getDataDirectory();

        String currDBPath = "";

        File currDB = new File(data, currDBPath);

        if(currDB.exists()) {

            Socket server = new Socket(addr, 10200);
            OutputStream outputStream = server.getOutputStream();

            outputStream.write((getDeviceUUID() + "\n").getBytes());

            FileInputStream fip = new FileInputStream(dbName);

            byte[] buffer = new byte[1024];
            int length = 0;

            while( (length = fip.read(buffer, 0, buffer.length)) != -1 ) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.flush();

            if(fip != null) fip.close();
            server.close();
        }
    }

    private static String getDeviceUUID() {
        final TelephonyManager tm = (TelephonyManager) BadUIApp.getContext().getSystemService(Context.TELEPHONY_SERVICE);

        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(BadUIApp.getContext().getContentResolver(),
                android.provider.Settings.Secure.ANDROID_ID);

        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString();
    }

}
