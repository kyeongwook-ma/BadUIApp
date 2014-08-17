import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class TCPServer implements Runnable{
	public static final int serverPort = 10200;
	@Override
	public void run(){
		try{
			System.out.println("대기중..");
			ServerSocket serverSocket = new ServerSocket(serverPort);

			while(true)
			{
				Socket sock = serverSocket.accept();
				System.out.println("수신중....");
				try{
					BufferedReader in = new BufferedReader(new
							InputStreamReader(sock.getInputStream()));
					String str = in.readLine();
					System.out.println("수신중인 파일 이름 : " + str);
					File f = new File("c:\\down\\", str);
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
					System.out.println("서버 에러!!");
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
