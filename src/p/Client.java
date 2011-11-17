package p;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client implements Runnable {

	private Socket socket;
	private String message;
	
	Client(Socket socket_) {
		socket = socket_;
	}

	String getMessage() {
		return message;
	}
	
	//should not be used -> readMessage() !
	void setMessage(String m) {
		message = m;
	}
	
	public void run() {
		for(;;) {
			try {
				message = readMessage(socket);
			} catch (IOException e) {
				try {
					socket.close();
				} catch (IOException e1) {
					System.err.println("failed to close port "+ socket);
					e1.printStackTrace();
					return;
				}
				e.printStackTrace();
			}
			System.out.println("Socket "+ socket.getLocalSocketAddress() +": "+ message);
			// writeMessage(nachricht);
			//TODO:client stuff
		}
	}



	String readMessage(Socket socket) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		String nachricht = bufferedReader.readLine(); // blockiert bis Nachricht
														// empfangen;
		return nachricht;
	}

	void writeMessage(String nachricht) throws IOException {
		PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(
				System.out));
		printWriter.print(nachricht);
		printWriter.flush();
	}

}
