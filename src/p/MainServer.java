package p;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.*;


public class MainServer {

	ServerSocket serverSocket;
	final int port;
	final ExecutorService executor;
	
	MainServer(int portnumber) {
		port = portnumber;
		executor = Executors.newCachedThreadPool();
	}
	
	int getPort() {
		return port;
	}
	
	public static void main(String[] args) throws IOException {
		MainServer server = new MainServer(8084);
		
		server.run();	
	}
	
	public void run(){
		try {
			serverSocket = new ServerSocket(getPort());
		} catch (IOException e) {
			System.err.println("failed to open port " + port);
			e.printStackTrace();
		}
		Thread exithook = new Thread(new Runnable() {
						public void run() {
							shutdown();
						}
		});
		Runtime.getRuntime().addShutdownHook(exithook);
		
		//creates Multiple Threads for eacht Client
		for(;;) {
			Socket socket = null;
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Client client = new Client(socket);
			executor.execute(client);		
		}
	}
	
	public void shutdown() {
		executor.shutdownNow();
		try {
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("failed to close socket "+ port);
			e.printStackTrace();
		}
	}
}
