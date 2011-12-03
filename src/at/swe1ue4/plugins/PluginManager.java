package at.swe1ue4.plugins;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import at.swe1ue4.network.MainServer;


public class PluginManager {
	ArrayList<PluginInterface> plugins;
	
	public PluginManager() {
		FileReader fr = null;
		String configFile = MainServer.getConfigFilepath();
		
		// load the configuration file
		try {
			fr = new FileReader( configFile );
			
			BufferedReader br = new BufferedReader( fr );
			
			try {
				String line = null;
				
				while( (line = br.readLine()) != null ) {
					Class<?> pluginClass = null;
					
					try {
						pluginClass = Class.forName(line);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
						System.exit(1);
					}
					
					PluginInterface p = null;
					
					try {
						p = (PluginInterface)pluginClass.newInstance();
					} catch (InstantiationException e) {
						e.printStackTrace();
						System.exit(1);
					} catch (IllegalAccessException e) {
						e.printStackTrace();
						System.exit(1);
					}
					
					plugins.add( p );
				}				
			} catch (IOException e) {
				System.err.println("Failed to obtain line from file [" + configFile + "].");
				System.exit(1);
			} finally {
				try {
					br.close();
				} catch (IOException e) {
					System.err.println("Failed to close bufferedReader [" + configFile + "].");
					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("File at " + configFile + " could not be found." +
							   "Please create a file containing the names of the plugins you with to load");
			System.exit(1);
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				System.err.println("Failed to close fileReader [" + configFile + "].");
				e.printStackTrace();
			}
		}
	}
	
	public String getMessageFromPlugin(String[] text) {
		PluginInterface plugin = null;
		int maxRating = 0;
		
		// find the plugin with highest rating
		for(PluginInterface pi : plugins) {
			int tmp = pi.rateString(text);
			
			if( tmp > maxRating ) {
				plugin = pi;
				maxRating = tmp;
			}
		}
		
		// execute the plugin with highest rating for given text
		return plugin.getMessageForString(text);
	}
}
