package at.swe1ue4.textparser;

import java.util.ArrayList;

public class WordTypes {

	private ArrayList<ArrayList<String>> types;
	private String[] tmp;
	
	public WordTypes() {
		types = new ArrayList<ArrayList<String>>();
	}
	
	public void categorize(String cat,String words) {
		String delimiter = " ";

		tmp = words.split(delimiter);
		//System.out.println("Category "+cat+" count of available words: " +tmp.length);
		for(int i=0;i<tmp.length;i++) {
			types.add(new ArrayList<String>());
		}
		
		for(int i=0;i<tmp.length;i++) {
			types.get(0).add(cat);
			types.get(1).add(tmp[i]);
		}
	}
	
	public void printTypes() {
		for(int i=0;i<types.size();i++) {
			System.out.println(types.get(0).get(i) +" | "+ types.get(1).get(i));
		}
	}
}
