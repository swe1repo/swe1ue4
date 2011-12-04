package at.swe1ue4.textparser;

public class TextParser {
	public TextParser() {
	}
	
	/**
	 * This method parses the given text and splits it into words.
	 * 
	 * @param str The input text.
	 * @return The input text split into words.
	 */
	public String[] readText(String str) {
		return str.split(" ");
	}
}
