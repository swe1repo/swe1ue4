package at.swe1ue4.plugins;

public interface PluginInterface {
	static int MAX_RATING = 100;
	static int MIN_RATING = 0;
	int rateString(String[] text);
	String getMessageForString(String[] text);
}
