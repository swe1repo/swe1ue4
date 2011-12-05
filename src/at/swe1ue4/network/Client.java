package at.swe1ue4.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import at.swe1ue4.plugins.PluginManager;
import at.swe1ue4.textparser.TextParser;
import at.swe1ue4.textparser.WordTypes;

public class Client implements Runnable {

	private Socket socket;
	private TextParser parser;
	private PluginManager pluginManager;
	
	Client(Socket socket_) {
		socket = socket_;
		parser = new TextParser();
		pluginManager = new PluginManager();
		//initialise some words for proper selection of plugin manager
		parser.init_wordTypes();
	}
	
	public void run() {
		System.out.println("A new client[" + socket.getLocalSocketAddress() + "] has connected.");
		
		// client request loop
		while(!Thread.currentThread().isInterrupted()) {
			try {
				// read clients request
				String request = readMessage(socket);
	
				System.out.println("[" + socket.getLocalSocketAddress() + "] received: " + request);
	
				// parsing the Text
				String[] words = parser.readText(request);
				
				// get response from the pluginManager
				String response = pluginManager.getMessageFromPlugin(words);
				
				System.out.println("[" + socket.getLocalSocketAddress() + "] responded: " + response);
				
				// send response
				writeMessage(response);
				
			} catch (IOException e) {
				System.out.println("Connection to client[" + socket.getLocalPort() + "] was closed.");
				break;
			}
		}
		
		try {
			socket.close();
		} catch (IOException e1) {
			System.err.println("Failed to close clients'[" + socket.getLocalPort() + "] socket.");
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
		printWriter.print(nachricht+"\n");
		printWriter.flush();
	}

}
