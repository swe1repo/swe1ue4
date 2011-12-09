package at.swe1ue4.plugins;

import at.swe1ue4.pluginHelpers.OsmHelper;

public class NaviPlugin implements PluginInterface {
	OsmHelper helper = new OsmHelper();

	@Override
	public int rateString(String[] text) {
		//int rating = PluginInterface.MIN_RATING;
	
		if( isReload(text) ) {
			// this command must be executed
			return PluginInterface.MAX_RATING + 1;
		}
		
		return 0;
	}

	@Override
	public String getMessageForString(String[] text) {
		if( isReload(text) ) {
			// this command must be executed
			helper.rebuildOsmIndex();
			return "I have successfully rebuilt the street map(" + helper.getCityCount() + 
					" cities, " + helper.getStreetCount() + " streets) in " + helper.getReloadTime() + " seconds.";
		} else {
			for(String str : text) {
				String tmp = helper.getCityForStreet(str);
				
				if( tmp != null ) {
					return tmp;
				}
			}
		}
		
		return null;
	}
	
	boolean isReload(String[] text) {
		String originalText = "";
		for(String s : text) {
			originalText += s + " ";
		}
		originalText = originalText.substring(0, originalText.length() - 1); // remove the trailing whitespace
		
		// bypass rating system for the NaviPlugin's reload command
		if( originalText.equals("Please reload the street map") || originalText.equals("Reload the street map") ||
			originalText.equals("please reload the street map") || originalText.equals("reload the street map") ) {
			// this command must be executed
			return true;
		}
		
		return false;
	}
}
