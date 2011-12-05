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
	WordTypes wt;
	
	/**
	 * The PluginManager's constructor will attempt to load the configuration file that has been passed
	 * as a command line parameter.
	 */
	public PluginManager() {
		wt = new WordTypes();
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
	
	public void init_wordTypes() {
		String words;
		words = "i me mine myself we us ours ourselves ourself " +
				"you yours yourself yourselves he him himself " +
				"she hers herself it that those this these things " +
				"thing they them themselves theirs somebody everybody " +
				"something everything";
		wt.categorize("pronoun", words);
		
		words = "how why who have has when where do i are";
		wt.categorize("questionword", words);
	
		words = "about above after around as at befor beneath behind " +
				"beside between by for from in inside into like near " +
				"next of on onto over same through under underneath with " +
				"without";
		wt.categorize("preposition", words);
		
		words = "one two three four five six seven eight nine ten eleven " +
				"twelve thirteen fourteen fifteen sixteen seventeen eighteen " +
				"nineteen twenty thirty forty fifty sixty seventy eighty ninety " +
				"hundred thousand million billion half quarter first second third " +
				"fourth fifth sixth seventh eighth ninth tenth";
		wt.categorize("number", words);
		
		words = "black blue brown grey green orange pink purple red violet white " +
				"red";
		wt.categorize("color", words);
		
		words = "big large tall fat wide thick small short thin skinny";
		wt.categorize("size", words);
		
		words = "my your his her our their";
		wt.categorize("possessivepronoun", words);
		
		words = "the a an";
		wt.categorize("article", words);
		
		words = "abort aborted aborts ask asked asks am applied applies apply " +
				"are associate be became become becomes becoming been being " +
				"believe believed believes bit bite bites bore bored bores " +
				"boring bought buy buys buying call called calling calls came " +
				"can caught catch come contract contracted contracts control " +
				"controlled controls ould croak croaks croaked cut cuts dare " +
				"dared define defines dial dialed dials did die died dies dislike " +
				"disliked dislikes do does drank drink drinks drinking drive drives " +
				"driving drove dying eat eating eats expand expanded expands expect " +
				"expected expects expel expels expelled explain explained explains " +
				"fart farts feel feels felt fight fights find finds finding forget " +
				"forgets forgot fought found fuck fucked fucking fucks gave get gets " +
				"getting give gives go goes going gone got gotten had harm harms has " +
				"hate hated hates have having hear heard hears hearing help helped " +
				"helping helps hit hits hope hoped hopes hurt hurts implies imply is " +
				"join joined joins jump jumped jumps keep keeping keeps kept kill killed " +
				"killing kills kiss kissed kisses kissing knew know knows laid lay lays let " +
				"lets lie lied lies like liked likes liking listen listens login look looked " +
				"looking looks lose losing lost love loved loves loving luse lusing lust lusts " +
				"made make makes making may mean means meant might move moved moves moving must " +
				"need needed needs order ordered orders ought paid pay pays pick picked picking " +
				"picks placed placing prefer prefers put puts ran rape raped rapes read " +
				"reading reads recall receive received receives refer refered referred refers " +
				"relate related relates remember remembered remembers romp romped romps run " +
				"running runs said sang sat saw say says screw screwed screwing screws scrod " +
				"see sees seem seemed seems seen sell selling sells send sendind sends sent " +
				"shall shoot shot should sing sings sit sits sitting sold studied study take " +
				"takes taking talk talked talking talks tell tells telling think thinks thought " +
				"told took tooled touch touched touches touching transfer transferred transfers " +
				"transmit transmits transmitted type types types typing walk walked walking walks " +
				"want wanted wants was watch watched watching went were will wish would work " +
				"worked works write writes writing wrote use used uses using";
		wt.categorize("verb", words);
		wt.printTypes();
		
	}
}
