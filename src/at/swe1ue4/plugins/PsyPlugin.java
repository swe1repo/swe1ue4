package at.swe1ue4.plugins;

import java.util.Map;

import at.swe1ue4.textparser.WordTypes;

public class PsyPlugin implements PluginInterface{
	int rating;
	final static int RATING_VERB = 20;
	final static int RATING_PRONOUN = 10;
	final static int RATING_POSSESSIVEPRONOUN = 10;
	final static int RATING_SIZE = 5;
	final static int RATING_COLOR = 5;
	final static int RATING_PREPOSITION = 15;
	
	@Override
	public int rateString(String[] text) {
		//setting psychiatrist priority because when no plugin is found it is 
		//likely that psyplugin can answer something that makes sense
		rating = 1;
		for(String token : text) {
			for(int i=0;i<WordTypes.verb.size();i++) {
				if(token.compareTo(WordTypes.verb.get(i)) == 0) {
					rating += RATING_VERB;
				}
			}
			for(int i=0;i<WordTypes.pronoun.size();i++) {
				if(token.compareTo(WordTypes.pronoun.get(i)) == 0) {
					rating += RATING_PRONOUN;
				}
			}
			for(int i=0;i<WordTypes.possessivepronoun.size();i++) {
				if(token.compareTo(WordTypes.possessivepronoun.get(i)) == 0) {
					rating += RATING_POSSESSIVEPRONOUN;
				}
			}
			for(int i=0;i<WordTypes.size.size();i++) {
				if(token.compareTo(WordTypes.size.get(i)) == 0) {
					rating += RATING_SIZE;
				}
			}
			for(int i=0;i<WordTypes.color.size();i++) {
				if(token.compareTo(WordTypes.color.get(i)) == 0) {
					rating += RATING_COLOR;
				}
			}
			for(int i=0;i<WordTypes.preposition.size();i++) {
				if(token.compareTo(WordTypes.preposition.get(i)) == 0) {
					rating += RATING_PREPOSITION;
				}
			}
		}
		if(rating > 100) {
			return rating = PluginInterface.MAX_RATING;
		}
		else {
			return rating;
		}
	}
	
	@Override
	public String getMessageForString(String[] text) {
		int count_verb = 0;
		int count_pronoun = 0;
		int count_possessivepronoun = 0;
		int count_size = 0;
		int count_color = 0;
		int count_preposition = 0;
		int count_greetingword = 0;
		int count_questionword = 0;
		int count_number = 0;
		int count_punctuation = 0;
		
		//evaluate every single word. words with high priority should be at the top
		for(String token : text) {
			for(int i=0;i<WordTypes.greetingword.size();i++) {
				if(token.compareTo(WordTypes.greetingword.get(i)) == 0) {
					count_greetingword += foundGreetingWord(token);
					return "Very nice to meet you! How can i help you?";
				}
			}
			for(int i=0;i<WordTypes.questionword.size();i++) {
				if(token.compareTo(WordTypes.questionword.get(i)) == 0) {
					count_questionword += foundQuestionWord(token);
					if(count_questionword > 1) {
						return "Everything is going to be fine. Please ask me one question at a time :)";
					}
					if(count_questionword == 1) {
						return "That is a very good question! Hopefully with further implementation i can answer" +
								" your that.";
					}
				}
			}
			for(int i=0;i<WordTypes.verb.size();i++) {
				if(token.compareTo(WordTypes.verb.get(i)) == 0) {
					count_verb += foundVerb(token);
					return "You said the following verb: \""+token+"\". \nCome back later when implementation is complete!";
				}
			}
			for(int i=0;i<WordTypes.pronoun.size();i++) {
				if(token.compareTo(WordTypes.pronoun.get(i)) == 0) {
				}
			}
			for(int i=0;i<WordTypes.possessivepronoun.size();i++) {
				if(token.compareTo(WordTypes.possessivepronoun.get(i)) == 0) {
				}
			}
			for(int i=0;i<WordTypes.size.size();i++) {
				if(token.compareTo(WordTypes.size.get(i)) == 0) {
				}
			}
			for(int i=0;i<WordTypes.color.size();i++) {
				if(token.compareTo(WordTypes.color.get(i)) == 0) {
				}
			}
			for(int i=0;i<WordTypes.preposition.size();i++) {
				if(token.compareTo(WordTypes.preposition.get(i)) == 0) {
				}
			}
			for(int i=0;i<WordTypes.number.size();i++) {
				if(token.compareTo(WordTypes.number.get(i)) == 0) {
					
				}
			}
			for(int i=0;i<WordTypes.punctuation.size();i++) {
				if(token.compareTo(WordTypes.punctuation.get(i)) == 0) {
					
				}
			}
		}
		return null;
	}
	
	public int foundGreetingWord(String word) {
		return 1;
	}
	
	public int foundQuestionWord(String word) {
		return 1;
	}

	public int foundVerb(String word) {
		
		return 1;
	}
	
	public void foundPunctuation() {
		
	}
	
	public void foundSize() {
		
	}
	
	public void foundPossessivepronoun() {
		
	}
	
	public void foundColor() {
		
	}
	
	public void foundNumber() {
		
	}
	
	public void foundPreposition() {
		
	}
	
	public void foundPronoun() {
		
	}
}
