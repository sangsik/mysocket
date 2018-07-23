

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {
	
	public static final int PORT = 8000;

	public static void main(String[] args) {
		try {
			ExecutorService executorService = Executors.newFixedThreadPool(10);
			ServerSocket serverSocket = new ServerSocket(PORT);
			
			while(true) {
				Socket socket = serverSocket.accept();
				executorService.execute(new Worker(socket));
			}
//			executorService.shutdown();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Worker implements Runnable {
	Socket socket = null;
	public Worker(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InputStream inputStream = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(isr);
			String readLine = br.readLine();
			System.out.println("request:" + readLine);
			
			OutputStream outputStream = socket.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));
			bw.write(readLine);
			bw.newLine();
			bw.flush();
			
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
	}

}
