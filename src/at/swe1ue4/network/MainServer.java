package at.swe1ue4.network;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.io.*;


public class MainServer {
	ServerSocket serverSocket = null;
	final int port;
	final ExecutorService executor;
	static String configFilePath;
	
	MainServer(int portnumber) {
		port = portnumber;
		executor = Executors.newCachedThreadPool();
	}
	
	int getPort() {
		return port;
	}
	
	public static String getConfigFilepath() {
		return MainServer.configFilePath;
	}
	
	public static void print_usage() {
		System.out.println("Usage: swe1ue4 [server port] [config filepath]");
	}
	
	public static void main(String[] args) throws IOException {
		if(args.length != 2) {
			print_usage();
			return;
		} else {
			configFilePath = args[1];
		}
		
		MainServer server = new MainServer( Integer.parseInt( args[0] ) );
		
		server.run();	
	}
	
	public void run(){
		System.out.println("Starting server on port " + getPort());
		
		try {
			serverSocket = new ServerSocket(getPort());
		} catch (IOException e) {
			System.err.println("Failed to open port " + port);
			e.printStackTrace();
		}
		Thread exithook = new Thread(new Runnable() {
						public void run() {
							shutdown();
						}
		});
		Runtime.getRuntime().addShutdownHook(exithook);
		
		
		
		//creates Multiple Threads for each Client
		for(;;) {
			Socket socket = null;
			
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("An error occured when accepting.");
				e.printStackTrace();
			}
			
			Client client = new Client(socket);
			executor.execute(client);		
		}
	}
	
	public void shutdown() {
		executor.shutdownNow(); // sends interrupt() to all threads
		
		try {
			if(serverSocket != null) {
				serverSocket.close();
			}
		} catch (IOException e) {
			System.err.println("failed to close socket "+ port);
			e.printStackTrace();
		}
	}
}
