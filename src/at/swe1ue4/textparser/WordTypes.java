package at.swe1ue4.textparser;

import java.util.ArrayList;

public class WordTypes {

	//public static ArrayList<ArrayList<String>> types;
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
	
	
	private static String[] tmp;
	
	public WordTypes() {
		//types = new ArrayList<ArrayList<String>>();
	}
	
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
		/*
		for(int i=0;i<tmp.length;i++) {
			types.add(new ArrayList<String>());
		}
		
		for(int i=0;i<tmp.length;i++) {
			types.get(0).add(cat);
			types.get(1).add(tmp[i]);
		}*/
	}
/*
	public static void printTypes() {
		for(int i=0;i<types.size();i++) {
			System.out.println(types.get(0).get(i) +" | "+ types.get(1).get(i));
		}
	}*/
}
