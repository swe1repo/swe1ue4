package at.swe1ue4.textparser;

import java.util.ArrayList;

public class WordTypes {

	public static ArrayList<String> realnumber = new ArrayList<String>();
	public static ArrayList<String> verb = new ArrayList<String>();
	public static ArrayList<String> operator = new ArrayList<String>();
	public static ArrayList<String> punctuation = new ArrayList<String>();
	public static ArrayList<String> number = new ArrayList<String>();
	public static ArrayList<String> article = new ArrayList<String>();
	public static ArrayList<String> possessivepronoun = new ArrayList<String>();
	public static ArrayList<String> size = new ArrayList<String>();
	public static ArrayList<String> color = new ArrayList<String>();
	public static ArrayList<String> preposition = new ArrayList<String>();
	public static ArrayList<String> questionword = new ArrayList<String>();
	public static ArrayList<String> pronoun = new ArrayList<String>();
	public static ArrayList<String> greetingword = new ArrayList<String>();
	public static ArrayList<String> keyword = new ArrayList<String>();
	public static ArrayList<String> dateword = new ArrayList<String>();
	
	public static ArrayList<String> yesAnswer = new ArrayList<String>();
	public static ArrayList<String> noAnswer = new ArrayList<String>();
	public static ArrayList<String> youAnswer = new ArrayList<String>();
	public static ArrayList<String> youAreAnswer = new ArrayList<String>();
	public static ArrayList<String> becauseAnswer = new ArrayList<String>();
	public static ArrayList<String> randomAnswer = new ArrayList<String>();
	public static ArrayList<String> canYouAnswer = new ArrayList<String>();
	public static ArrayList<String> questionAnswer = new ArrayList<String>();
	public static ArrayList<String> greetingAnswer = new ArrayList<String>();
	
	
	private static String[] tmp;
	
	public WordTypes() {
	}
	/**
	 * Stores the words in the arraylist
	 * @param cat depending on the category entered the words are saved in the 
	 * specific category-list
	 * @param words contains all the words of one category
	 */
	public static void categorize(String cat,String words) {
		
		String delimiter = " ";

		tmp = words.split(delimiter);
		//System.out.println("Category "+cat+" count of available words: " +tmp.length);
		if(cat.compareTo("realnumber") == 0) {
			for(int i=0;i<tmp.length;i++) {
				realnumber.add(tmp[i]);
			}
		}
		if(cat.compareTo("verb") == 0) {
			for(int i=0;i<tmp.length;i++) {
				verb.add(tmp[i]);
			}
		}
		if(cat.compareTo("operator") == 0) {
			for(int i=0;i<tmp.length;i++) {
				operator.add(tmp[i]);
			}
		}
		if(cat.compareTo("punctuation") == 0) {
			for(int i=0;i<tmp.length;i++) {
				punctuation.add(tmp[i]);
			}
		}
		if(cat.compareTo("number") == 0) {
			for(int i=0;i<tmp.length;i++) {
				number.add(tmp[i]);
			}
		}
		if(cat.compareTo("article") == 0) {
			for(int i=0;i<tmp.length;i++) {
				article.add(tmp[i]);
			}
		}
		if(cat.compareTo("possessivepronoun") == 0) {
			for(int i=0;i<tmp.length;i++) {
				possessivepronoun.add(tmp[i]);
			}
		}
		if(cat.compareTo("size") == 0) {
			for(int i=0;i<tmp.length;i++) {
				size.add(tmp[i]);
			}
		}
		if(cat.compareTo("color") == 0) {
			for(int i=0;i<tmp.length;i++) {
				color.add(tmp[i]);
			}
		}
		if(cat.compareTo("pronoun") == 0) {
			for(int i=0;i<tmp.length;i++) {
				pronoun.add(tmp[i]);
			}
		}
		if(cat.compareTo("preposistion") == 0) {
			for(int i=0;i<tmp.length;i++) {
				preposition.add(tmp[i]);
			}
		}
		if(cat.compareTo("questionword") == 0) {
			for(int i=0;i<tmp.length;i++) {
				questionword.add(tmp[i]);
			}
		}
		if(cat.compareTo("greetingword") == 0) {
			for(int i=0;i<tmp.length;i++) {
				greetingword.add(tmp[i]);
			}
		}
		if(cat.compareTo("keyword") == 0) {
			for(int i=0;i<tmp.length;i++) {
				keyword.add(tmp[i]);
			}
		}
		if(cat.compareTo("dateword") == 0) {
			for(int i=0;i<tmp.length;i++) {
				dateword.add(tmp[i]);
			}
		}
	}
	
	/**
	 * Stores one answers of one category in the arraylist
	 * @param cat defines the category the answer belong to
	 * @param answer a String with a possible response from the category
	 */
	public static void init_answers(String cat, String answer) {
		if(cat.compareTo("yesAnswer") == 0) {
			yesAnswer.add(answer);
		}
		if(cat.compareTo("noAnswer") == 0) {
			noAnswer.add(answer);
		}
		if(cat.compareTo("questionAnswer") == 0) {
			questionAnswer.add(answer);
		}
		if(cat.compareTo("youAnswer") == 0) {
			youAnswer.add(answer);
		}
		if(cat.compareTo("youAreAnswer") == 0) {
			youAreAnswer.add(answer);
		}
		if(cat.compareTo("becauseAnswer") == 0) {
			becauseAnswer.add(answer);
		}
		if(cat.compareTo("canYouAnswer") == 0) {
			canYouAnswer.add(answer);
		}
		if(cat.compareTo("randomAnswer") == 0) {
			randomAnswer.add(answer);
		}
		if(cat.compareTo("greetingAnswer") == 0) {
			greetingAnswer.add(answer);
		}
	}
}
