

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketClient2 {
	
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("127.0.0.1", SocketServer2.PORT);


			OutputStream outputStream = socket.getOutputStream();
			byte[] buf = new byte[30];
			byte[] message = "SEND MESSAGE".getBytes();
			System.arraycopy(message, 0, buf, 0, message.length);
			outputStream.write(buf);
			
			
//			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
//			bw.write("SEND MESSAGE");
//			bw.newLine();
//			bw.flush();

			
			InputStream inputStream = socket.getInputStream();
			inputStream.read(buf);
			String readLine = new String(buf);
			System.out.println("response:" + readLine);
			
//			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
//			String readLine = br.readLine();
//			while(readLine != null ) {
//				System.out.println("response:" + readLine);
//				readLine = br.readLine();
//			}
			
//			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
