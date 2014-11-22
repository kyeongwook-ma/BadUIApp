package selab.dev.baduiapp.db;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import selab.dev.baduiapp.BadUIApp;

/**
 * Created by se on 2014-11-20.
 */
public class DBExporter {

    public static void exportDB(final String addr, final String dbName) throws IOException {

        final File currDB = BadUIApp.getContext().getDatabasePath(dbName);

        if(currDB.exists()) {

            new Thread() {
                @Override
                public void run() {
                    Socket server = null;
                    try {
                        server = new Socket(addr, 10200);
                        OutputStream outputStream = null;
                        outputStream = server.getOutputStream();
                        FileInputStream fip = null;

                        fip = new FileInputStream(currDB);

                        byte[] buffer = new byte[1024];
                        int length = 0;

                        while( (length = fip.read(buffer, 0, buffer.length)) != -1 ) {
                            outputStream.write(buffer, 0, length);
                        }

                        outputStream.flush();

                        if(fip != null) {
                            fip.close();
                        }

                        server.close();

                    }

                    catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

        }
    }



}
