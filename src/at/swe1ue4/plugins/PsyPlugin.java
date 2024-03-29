package at.swe1ue4.plugins;

import java.util.ArrayList;
import at.swe1ue4.textparser.WordTypes; 

public class PsyPlugin implements PluginInterface {
	final static int RATING_VERB = 20;
	final static int RATING_PRONOUN = 15;
	final static int RATING_POSSESSIVEPRONOUN = 10;
	final static int RATING_PREPOSITION = 15;
	final static int RATING_KEYWORD = 20;
	
	@Override
	public int rateString(String[] text) {
		//setting psychiatrist priority because when no plugin is found it is 
		//likely that psyplugin can answer something that makes sense
		int rating = 1;
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
			for(int i=0;i<WordTypes.keyword.size();i++) {
				if(token.compareTo(WordTypes.keyword.get(i)) == 0) {
					rating += RATING_KEYWORD;
				}
			}
			for(int i=0;i<WordTypes.preposition.size();i++) {
				if(token.compareTo(WordTypes.preposition.get(i)) == 0) {
					rating += RATING_PREPOSITION;
				}
			}
		}
		if(rating > 100) {
			return rating = PluginInterface.MAX_RATING + 1; //plus one because psyplugin has higher priority (more questions can be answered)
		}
		else {
			return rating;
		}
	}
	
	@Override
	public String getMessageForString(String[] text) {
		String verb = null;
		for(int x=0;x<text.length;x++) {
			String asd = text[x];
			for(int i=0;i<WordTypes.verb.size();i++) {
				if(asd.compareTo(WordTypes.verb.get(i)) == 0) {
					//ignore the can verb because in the "can you" response program needs the second verb
					if((asd.compareTo("can") != 0) && asd.compareTo("are") != 0) {
						verb = asd;
					}
					else {
						if((x+1) < text.length) {
							if(!WordTypes.pronoun.contains(text[x+1]))
							{
								verb = text[x+1];
							}
							else {
								if((x+2) < text.length) {
									verb = text[x+2];
								}
							}
						}
					}
				}
			}	
		}
		for(String as : text) {
			for(int i=0;i<WordTypes.verb.size();i++) {
				if(as.compareTo(WordTypes.verb.get(i)) == 0) {
					//special case in order to respond to "you are" statement. otherwise the programm goes to "you" answers.
					if(as.compareTo("are") == 0) {
						return foundKeyWord(as,verb);
					}		
				}
			}	
		}
		
		
		//evaluate every single word. words with high priority should be at the top
		for(String token : text) {
			for(int i=0;i<WordTypes.greetingword.size();i++) {
				if(token.compareTo(WordTypes.greetingword.get(i)) == 0) {
					return foundGreetingWord(token,verb);
				}
			}
			for(int i=0;i<WordTypes.questionword.size();i++) {
				if(token.compareTo(WordTypes.questionword.get(i)) == 0) {
					return foundQuestionWord(token,verb);
				}
			}
			for(int i=0;i<WordTypes.keyword.size();i++) {
				if(token.compareTo(WordTypes.keyword.get(i)) == 0) {
					return foundKeyWord(token,verb);
				}
			}
		}

		//if no question to this point has been answered choose one from random-Answers
		return getrandomAnswer();
	}
	
	public String foundGreetingWord(String word, String verb) {
		long random = 0 + Math.round((Math.random() * (WordTypes.greetingAnswer.size() - 1)));	
		return WordTypes.greetingAnswer.get((int) random);
	}
	
	public String foundQuestionWord(String word, String verb) {
		long random = 0 + Math.round((Math.random() * (WordTypes.questionAnswer.size() - 1)));
		long jumpToRandomAnswer = 1 + Math.round((Math.random() * (3 - 1)));
		
		if(jumpToRandomAnswer == 2) {
			return getrandomAnswer();
		}
		else {
			return WordTypes.questionAnswer.get((int)random);
		}
	}
	
	public String foundKeyWord(String word, String verb) {
		long random;
		if(word.compareTo("yes") == 0) {
			random = 0 + Math.round((Math.random() * (WordTypes.yesAnswer.size() - 1)));
			return WordTypes.yesAnswer.get((int)random);
		}
		else if(word.compareTo("no") == 0) {
			random = 0 + Math.round((Math.random() * (WordTypes.noAnswer.size() - 1)));
			return WordTypes.noAnswer.get((int)random);
		}
		else if(word.compareTo("are") == 0) {
			if(verb == null) {
				return getrandomAnswer();
			}
			random = 0 + Math.round((Math.random() * (WordTypes.youAreAnswer.size() - 1)));
			return WordTypes.youAreAnswer.get((int)random) +" "+ verb + "?";
		}
		else if(word.compareTo("you") == 0) {
			random = 0 + Math.round((Math.random() * (WordTypes.youAnswer.size() - 1)));
			System.out.println("Size of you answer"+WordTypes.youAnswer.size());
			return WordTypes.youAnswer.get((int)random);
		}
		else if(word.compareTo("because") == 0) {
			random = 0 + Math.round((Math.random() * (WordTypes.becauseAnswer.size() - 1)));
			return WordTypes.becauseAnswer.get((int)random);
		}
		else if(word.compareTo("can") == 0) {
			if(verb == null) {
				return getrandomAnswer();
			}
			random = 0 + Math.round((Math.random() * (WordTypes.canYouAnswer.size() - 1)));
			ArrayList<String> tmp = new ArrayList<String>();
			tmp.add(WordTypes.canYouAnswer.get(0) + verb + " your self.");
			tmp.add(WordTypes.canYouAnswer.get(1) + verb + " dont you?");
			tmp.add(WordTypes.canYouAnswer.get(2));
			return tmp.get((int)random);
		}
		else {
			return "roses are red\nviolets are blue\nsome poems rhyme\nbut not this one.";
		}
	}
	
	public String getrandomAnswer() {
		long random = 0 + Math.round((Math.random() * (WordTypes.randomAnswer.size() - 1)));
		return WordTypes.randomAnswer.get((int)random);
	}
}
