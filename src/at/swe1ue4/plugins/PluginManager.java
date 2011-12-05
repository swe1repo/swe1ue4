package at.swe1ue4.plugins;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import at.swe1ue4.textparser.*;

import at.swe1ue4.network.MainServer;


public class PluginManager {
	List<PluginInterface> plugins = new ArrayList<PluginInterface>();
	
	/**
	 * The PluginManager's constructor will attempt to load the configuration file that has been passed
	 * as a command line parameter.
	 */
	public PluginManager() {
		FileReader fr = null;
		String configFile = MainServer.getConfigFilepath();
		
		// load the configuration file.
		try {
			fr = new FileReader( configFile );
			
			BufferedReader br = new BufferedReader( fr );
			
			try {
				String line = null;
				
				while( (line = br.readLine()) != null ) {
					Class<?> pluginClass = null;
					
					try {
						// obtains the plugins class from its name
						pluginClass = Class.forName(line);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
						System.err.println("The class[" + line + "] could not be found. " + 
								   	       "Make sure it is in your CLASSPATH and a valid class.");
						continue;
					}
					
					PluginInterface p = null;
					
					// check if the plugin implements the PluginInterface
					boolean implementsInterface = false;
					for(Class<?> in : pluginClass.getInterfaces()) {
						if(in.getName() == PluginInterface.class.getName()) {
							implementsInterface = true;
						}
					}
					
					if(implementsInterface == false) {
						System.err.println("The specified plugin[" + pluginClass.getName() + "] does not implement the PluginInterface. " +
										   "It is going to be skipped.");
						continue;
					}
					
					try {
						// instantiate the plugin
						p = (PluginInterface)pluginClass.newInstance();
					} catch (InstantiationException e) {
						System.err.println("The class[" + pluginClass.getName() + "] could not be instantiated.");
						continue;
					} catch (IllegalAccessException e) {
						System.err.println("The class[" + pluginClass.getName() + "] could not be accessed.");
						continue;
					}
					
					// add it to the list of active plugins
					plugins.add( p );
				}				
			} catch (IOException e) {
				System.err.println("Failed to obtain line from file [" + configFile + "].");
				Thread.currentThread().interrupt();
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
			Thread.currentThread().interrupt();
		} finally {
			try {
				fr.close();
			} catch (IOException e) {
				System.err.println("Failed to close fileReader [" + configFile + "].");
				e.printStackTrace();
			}
		}
		
		// Clients must load at least one plugin
		if(plugins.size() < 1) {
			System.err.println("You need at least one plugin to process text. Please supply a plugin class in your configuration file.");
			Thread.currentThread().interrupt();
		} else {
			for(PluginInterface pi : plugins) {
				// list all loaded plugins
				System.out.println("The plugin " + pi.getClass().getName() + " has been loaded.");
			}
		}
	}
	
	/**
	 * This method picks the plugin with the highest rating for a given textual client request
	 * to answer.
	 * 
	 * @param text The received request that has been split by the TextParser.
	 * @return The chosen plugin's response
	 */
	public String getMessageFromPlugin(String[] text) {
		PluginInterface plugin = null;
		int maxRating = PluginInterface.MIN_RATING - 1;
		
		// find the plugin with highest rating
		for(PluginInterface pi : plugins) {
			int tmp = pi.rateString(text);
			
			System.out.println("The plugin[" + pi.getClass().getName() + "] rated the input with " + tmp + ".");
			
			if( tmp > maxRating ) {
				plugin = pi;
				maxRating = tmp;
			}
		}
		
		// execute the plugin with highest rating for given text
		return plugin.getMessageForString(text);
	}
}
