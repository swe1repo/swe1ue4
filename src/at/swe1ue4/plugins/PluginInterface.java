package at.swe1ue4.plugins;

public interface PluginInterface {
	static int MAX_RATING = 100;
	static int MIN_RATING = 0;
	
	/**
	 * This method determines a plugins' rating for a given input text.
	 * 
	 * @param text The input text split into words.
	 * @return The rating a plugin has given the input __text__
	 */
	int rateString(String[] text);
	
	/**
	 * This method will return the plugin's answer to the input __text__.
	 * 
	 * @param text The input text split into words.
	 * @return The plugins response.
	 */
	String getMessageForString(String[] text);
}
