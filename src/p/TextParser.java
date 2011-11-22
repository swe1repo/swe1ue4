package p;

public class TextParser {

	private String text;
	private String[] words;
	
	TextParser() {
		
	}
	
	public String[] readText(String str) {
		text = str;
		
		return splitText();
	}
	
	public String[] splitText() {
		String delimiter = " ";
		
		words = text.split(delimiter);
		
		System.out.println(words.length +" Words found");
		
		for(int i=0;i<words.length;i++) {
			System.out.println(words[i] +" - Word found");
		}
		
		return words;
	}
	
	public void evaluateWords() {
		//TODO: determine if nomen verb adverb ...
	}
}
