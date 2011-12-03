package at.swe1ue4.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import at.swe1ue4.plugins.PluginManager;
import at.swe1ue4.textparser.TextParser;

public class Client implements Runnable {

	private Socket socket;
	private String message;
	private TextParser parser;
	private PluginManager pluginManager;
	
	Client(Socket socket_) {
		socket = socket_;
		parser = new TextParser();
		pluginManager = new PluginManager();
	}

	String getMessage() {
		return message;
	}
	
	//should not be used -> readMessage() !
	void setMessage(String m) {
		message = m;
	}
	
	public void run() {
		System.out.println("A new client[" + socket.getLocalPort() + "] has connected.");
		
		for(;;) {
			try {
				message = readMessage(socket);
	
				System.out.println("Socket "+ socket.getLocalSocketAddress() +": "+ message);
	
				String[] words = parser.readText(message);
				
				String message = pluginManager.getMessageFromPlugin(words);
				
				writeMessage(message);
				
			} catch (IOException e) {
				System.out.println("Connection to client[" + socket.getLocalPort() + "] was closed.");
				
				try {
					socket.close();
				} catch (IOException e1) {
					System.err.println("Failed to close clients'[" + socket.getLocalPort() + "] socket.");
					return;
				}
				
				return;
			}
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
				socket.getOutputStream()));
		printWriter.print(nachricht);
		printWriter.flush();
	}

}
