package selab.dev.baduiapp;


import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCPClient implements Runnable{
	private static final String serverIP="192.168.34.52";
	private static final int serverPort = 10200;
	private String msg;
	public TCPClient(String fileName){
		super();
		this.msg = fileName;
	}
	@Override
	public void run(){
		try{
			InetAddress serverAddr = InetAddress.getByName(serverIP);
			Socket sock = new Socket(serverAddr, serverPort);
			try{
				PrintWriter out = new PrintWriter(new BufferedWriter(new
						OutputStreamWriter(sock.getOutputStream())), true);
				out.println(msg);
				out.flush();

				DataInputStream dis = new DataInputStream(new
						FileInputStream(new File("/mnt/sdcard/"+msg+".jpg")));
				DataOutputStream dos = new
						DataOutputStream(sock.getOutputStream());
				byte[] buf = new byte[1024];
				while(dis.read(buf)>0)
				{
					dos.write(buf);
					dos.flush();
				}
				dos.close();
			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally
			{
				sock.close();
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}