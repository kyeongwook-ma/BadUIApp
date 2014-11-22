import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer implements Runnable{
	public static final int serverPort = 10200;
    private int idx = 0;
	@Override
	public void run(){
		try{
			System.out.println("wating..");
			ServerSocket serverSocket = new ServerSocket(serverPort);

			while(true)
			{
				Socket sock = serverSocket.accept();
				System.out.println("receving....");
				try{
                    ++idx;
					BufferedReader in = new BufferedReader(new
							InputStreamReader(sock.getInputStream()));

					System.out.println("receiving : ");
					File f = new File("./", String.valueOf(idx));
					FileOutputStream output = new FileOutputStream(f);
					byte[] buf = new byte[1024];                                       while(sock.getInputStream().read(buf)>0)
					{
						output.write(buf);
						output.flush();
					}
					in.close();
					output.close();
				}
				catch(Exception e){
					System.out.println("err!!");
					e.printStackTrace();
				}
				finally{
					sock.close();
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static void main(String[] argv){
		Thread doit = new Thread(new TCPServer());
		doit.start();
	}
}
