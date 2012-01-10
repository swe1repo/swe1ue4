package at.swe1ue4.network;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;
import java.io.*;


public class MainServer {
	ServerSocket serverSocket = null;
	final int port;
	final ExecutorService executor;
	static String configFilePath;
	static String osmFilePath;
	
	MainServer(int portnumber) {
		port = portnumber;
		executor = Executors.newCachedThreadPool();
	}
	
	int getPort() {
		return port;
	}
	
	/**
	 * @return The path to the configuration file that will be used to load plugins.
	 */
	public static String getConfigFilepath() {
		return MainServer.configFilePath;
	}
	
	public static void setConfigFilepath(String path) {
		MainServer.configFilePath = path;
	}
	
	/**
	 * @return The path to the openstreetmap file that will be used with NaviPlugins
	 */
	public static String getOsmFilepath() {
		return MainServer.osmFilePath;
	}
	
	public static void setOsmFilepath(String path) {
		MainServer.osmFilePath = path;
	}
	
	public static void print_usage() {
		System.out.println("Usage: swe1ue4 [server port] [config filepath] [osm filepath]");
	}
	
	public static void main(String[] args) throws IOException {
		if(args.length != 3) {
			print_usage();
			return;
		} else {
			configFilePath = args[1];
			osmFilePath = args[2];
		}
		
		MainServer server = new MainServer( Integer.parseInt( args[0] ) );
		
		server.run();	
	}
	
	/**
	 * Starts up the server. Once completed it will accept incoming connections and open a new socket for each.
	 */
	public void run(){
		System.out.println("Starting server on port " + getPort() + ".");
		
		try {
			serverSocket = new ServerSocket(getPort());
		} catch (IOException e) {
			System.err.println("Failed to open port " + port);
			return;
		}
		
		// Declare an exit hook to call the shutdown method
		Thread exithook = new Thread(new Runnable() {
						public void run() {
							shutdown();
						}
		});
		Runtime.getRuntime().addShutdownHook(exithook);
		
		
		
		// the server's main loop, where incoming connections are handled
		for(;;) {
			Socket socket = null;
			
			try {
				socket = serverSocket.accept();
			} catch (IOException e) {
				System.err.println("An error occured when accepting.");
				e.printStackTrace();
			}
			
			Client client = new Client(socket);
			
			// push the client thread into the thread pool
			try {
				executor.execute(client);
			} catch(RejectedExecutionException e) {
				System.err.println("Could not open connection with client, because the executor is closed.");
			}
		}
	}
	
	public void shutdown() {
		executor.shutdownNow(); // sends interrupt() to all threads, they will close their client sockets
		
		try {
			if(serverSocket != null) {
				// close the server's socket
				serverSocket.close();
			}
		} catch (IOException e) {
			System.err.println("failed to close socket "+ port);
			e.printStackTrace();
		}
		
		System.out.println("The server has been shut down successfully.");
	}
}
