

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer2 {

	public static final int PORT = 8000;

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);

			Socket socket = serverSocket.accept();
			try {
				InputStream inputStream = socket.getInputStream();
				byte[] buf = new byte[30];
				inputStream.read(buf);
				String readLine = new String(buf);
				
//				InputStreamReader isr = new InputStreamReader(inputStream);
//				BufferedReader br = new BufferedReader(isr);
//				String readLine = br.readLine();
				
				System.out.println("request:" + readLine);

				
				OutputStream outputStream = socket.getOutputStream();
				outputStream.write(buf);
				
//				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
//				bw.write(readLine);
//				bw.newLine();
//				bw.write(readLine);
//				bw.newLine();
//				bw.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
