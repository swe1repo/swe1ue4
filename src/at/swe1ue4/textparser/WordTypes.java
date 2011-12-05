package at.swe1ue4.textparser;

import java.util.ArrayList;

public class WordTypes {

	//public static ArrayList<ArrayList<String>> types;
	public static ArrayList<String> realnumber = new ArrayList<String>();;
	public static ArrayList<String> verb = new ArrayList<String>();;
	public static ArrayList<String> operator = new ArrayList<String>();;
	
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
