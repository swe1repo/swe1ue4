package at.swe1ue4.textparser;

public class TextParser {

	private String text;
	private String[] words;
	
	public TextParser() {
		
	}
	
	public String[] readText(String str) {
		text = str;
		
		return splitText();
	}
	
	public String[] splitText() {
		return text.split(" ");
	}
	
	public void evaluateWords() {
		//TODO: determine if nomen verb adverb ...
	}
}
