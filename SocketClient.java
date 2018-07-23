

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClient {
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", SocketServer.PORT);


			OutputStream outputStream = socket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
			bw.write("SEND MESSAGE");
			bw.newLine();
			bw.flush();

			InputStream inputStream = socket.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String readLine = br.readLine();
			while(readLine != null ) {
				System.out.println("response:" + readLine);
				readLine = br.readLine();
			}
			
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
