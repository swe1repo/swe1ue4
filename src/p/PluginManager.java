package p;

public class PluginManager {
	PluginInterface[] plugins;
	
	PluginManager() {
		// TODO: READ FILE
	}
	
	String getMessageFromPlugin(String[] text) {
		for(PluginInterface pi : plugins) {
			if(pi.rateString(text);
		}
		
		return plugins[0].getMessageForString(text);
	}
}
