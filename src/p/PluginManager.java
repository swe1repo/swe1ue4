package p;

public class PluginManager {
	PluginInterface[] plugins;
	
	PluginManager() {
		// TODO: READ FILE
	}
	
	String getMessageFromPlugin(String[] text) {
		PluginInterface plugin = null;
		int maxRating = 0;
		
		for(PluginInterface pi : plugins) {
			int tmp = pi.rateString(text);
			if( tmp > maxRating ) {
				plugin = pi;
				maxRating = tmp;
			}
		}
		
		return plugin.getMessageForString(text);
	}
}
