package p;

public class TextParser {

	private String text;
	private String[] words;
	private int length;
	
	TextParser() {
		
	}
	
	public void readText(String str) {
		text = str;
		length = text.length();
	}
	
	public void splitText() {
		String delimiter = " ";
		
		words = text.split(delimiter);
		
		System.out.println(words.length +" Words found");
		
		for(int i=0;i<words.length;i++) {
			System.out.println(words[i] +" - Word found");
		}
	}
	
	public void evaluateWords() {
		//TODO: determine if nomen verb adverb ...
	}
}
