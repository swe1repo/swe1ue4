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
		String delimiter = " ";
		
		words = text.split(delimiter);
		
		for(int i = 0; i < words.length; ++i) {
			words[i] = words[i].toLowerCase();
		}
		
		/** Strip trailing ?'s. */
		if( words[words.length - 1].endsWith("?") || words[words.length - 1].endsWith("!") || words[words.length - 1].endsWith(".")) {
			words[words.length - 1] = words[words.length - 1].substring(0, words[words.length - 1].length() - 1);
		}
		
		return words;
	}
	
	/**
	 * This method contains all words and responses, the program can interpret and respond
	 */
	public void init_wordTypes() {
		String words;
		
		words = "no yes you are can because";
		WordTypes.categorize("keyword", words);
		
		words = "i me mine myself we us ours ourselves ourself " +
				"you yours yourself yourselves he him himself " +
				"she hers herself it that those this these things " +
				"thing they them themselves theirs somebody everybody " +
				"something everything";
		WordTypes.categorize("pronoun", words);
		
		words = "how why who have has when where do are";
		WordTypes.categorize("questionword", words);
		
		words = "hello hi hey howdy greetings";
		WordTypes.categorize("greetingword", words);
	
		words = "about above after around as at befor beneath behind " +
				"beside between by for from in inside into like near " +
				"next of on onto over same through under underneath with " +
				"without";
		WordTypes.categorize("preposition", words);
		
		words = "one two three four five six seven eight nine ten eleven " +
				"twelve thirteen fourteen fifteen sixteen seventeen eighteen " +
				"nineteen twenty thirty forty fifty sixty seventy eighty ninety " +
				"hundred thousand million billion half quarter first second third " +
				"fourth fifth sixth seventh eighth ninth tenth";
		WordTypes.categorize("number", words);
		
		words = "black blue brown grey green orange pink purple red violet white " +
				"red";
		WordTypes.categorize("color", words);
		
		words = "big large tall fat wide thick small short thin skinny";
		WordTypes.categorize("size", words);
		
		words = "my your his her our their";
		WordTypes.categorize("possessivepronoun", words);
		
		words = "the a an";
		WordTypes.categorize("article", words);
		
		words = "abort aborted aborts ask asked asks am applied applies apply " +
				"are associate be became become becomes becoming been being " +
				"believe believed believes bit bite bites bore bored bores " +
				"boring bought buy buys buying call called calling calls came " +
				"can caught catch come contract contracted contracts control " +
				"controlled controls ould croak croaks croaked cut cuts dare " +
				"dared define defines dial dialed dials did die died dies dislike " +
				"disliked dislikes do does drank drink drinks drinking drive drives " +
				"driving drove dying eat eating eats expand expanded expands expect " +
				"expected expects expel expels expelled explain explained explains " +
				"fart farts feel feels felt fight fights find finds finding forget " +
				"forgets forgot fought found fuck fucked fucking fucks gave get gets " +
				"getting give gives go goes going gone got gotten had harm harms has " +
				"hate hated hates have having hear heard hears hearing help helped " +
				"helping helps hit hits hope hoped hopes hurt hurts implies imply is " +
				"join joined joins jump jumped jumps keep keeping keeps kept kill killed " +
				"killing kills kiss kissed kisses kissing knew know knows laid lay lays let " +
				"lets lie lied lies like liked likes liking listen listens login look looked " +
				"looking looks lose losing lost love loved loves loving luse lusing lust lusts " +
				"made make makes making may mean means meant might move moved moves moving must " +
				"need needed needs order ordered orders ought paid pay pays pick picked picking " +
				"picks placed placing prefer prefers put puts ran rape raped rapes read " +
				"reading reads recall receive received receives refer refered referred refers " +
				"relate related relates remember remembered remembers romp romped romps run " +
				"running runs said sang sat saw say says screw screwed screwing screws scrod " +
				"see sees seem seemed seems seen sell selling sells send sendind sends sent " +
				"shall shoot shot should sing sings sit sits sitting sold studied study take " +
				"takes taking talk talked talking talks tell tells telling think thinks thought " +
				"told took tooled touch touched touches touching transfer transferred transfers " +
				"transmit transmits transmitted type types types typing walk walked walking walks " +
				"want wanted wants was watch watched watching went were will wish would work " +
				"worked works write writes writing wrote use used uses using";
		WordTypes.categorize("verb", words);
		
		words = "0";
		for(int i=1;i<1000;i++) {
			words = words +" "+ Integer.toString(i);
		}
		WordTypes.categorize("realnumber", words);
		
		words = "! ? .";
		WordTypes.categorize("punctuation", words);
		
		words = "+ - / *";
		WordTypes.categorize("operator", words);
		//wt.printTypes();	
		
		// now initalise the responses
		words = "Very nice to meet you! How can i help you?";
		WordTypes.init_answers("greetingAnswer", words);
		words = "Hello. My name is Halo and i am the psychiatrist. Why do you wish to talk to me?";
		WordTypes.init_answers("greetingAnswer", words);
		words = "Hi. What is bothering you today?";
		WordTypes.init_answers("greetingAnswer", words);
		
		words = "You seem to be quite positive.";
		WordTypes.init_answers("yesAnswer", words);
		words = "I understand";
		WordTypes.init_answers("yesAnswer", words);
		words = "I see.";
		WordTypes.init_answers("yesAnswer", words);
		words = "Really?";
		WordTypes.init_answers("yesAnswer", words);

		words = "You are beeing a bit negative.";
		WordTypes.init_answers("noAnswer", words);
		words = "Why not?";
		WordTypes.init_answers("noAnswer", words);
		words = "Please explain yourself.";
		WordTypes.init_answers("noAnswer", words);

		words = "Does that question interest you?";
		WordTypes.init_answers("questionAnswer", words);
		words = "What is it you really wanted to know?";
		WordTypes.init_answers("questionAnswer", words);
		words = "Are such questions a lot on your mind?";
		WordTypes.init_answers("questionAnswer", words);
		words = "What answer would please you most?";
		WordTypes.init_answers("questionAnswer", words);

		words = "We are discussing you, not me.";
		WordTypes.init_answers("youAnswer", words);
		words = "Why do you say that?";
		WordTypes.init_answers("youAnswer", words);
	
		words = "What makes you think i am";
		WordTypes.init_answers("youAreAnswer", words);
		words = "Do you sometimes wish you were";
		WordTypes.init_answers("youAreAnswer", words);
		words = "Perhaps you would like to be";
		WordTypes.init_answers("youAreAnswer", words);
		
		words = "What other reasons might there be?";
		WordTypes.init_answers("becauseAnswer", words);
		words = "Does that reason seem to explain anything else?";
		WordTypes.init_answers("becauseAnswer", words);
		words = "Is that the real reason?";
		WordTypes.init_answers("becauseAnswer", words);
		words = "Don't any other reasons come to mind?";
		WordTypes.init_answers("becauseAnswer", words);
		
		words = "Perhaps you would like to be able to ";
		WordTypes.init_answers("canYouAnswer", words);
		words = "You believe i can ";
		WordTypes.init_answers("canYouAnswer", words);
		words = "Can you elaborate on that?";
		WordTypes.init_answers("canYouAnswer", words);
		words = "Why do you ask";
		WordTypes.init_answers("canYouAnswer", words);
		
		words = "Do you feel strongly about discussing such things?";
		WordTypes.init_answers("randomAnswer", words);
		words = "What are your feelings now?";
		WordTypes.init_answers("randomAnswer", words);
		words = "Please go on.";
		WordTypes.init_answers("randomAnswer", words);
		words = "Can you elaborate on that?";
		WordTypes.init_answers("randomAnswer", words);
		words = "What comes to mind when you say that?";
		WordTypes.init_answers("randomAnswer", words);
		words = "What does that suggest to you?";
		WordTypes.init_answers("randomAnswer", words);
		words = "Continue.";
		WordTypes.init_answers("randomAnswer", words);
		words = "I think you need to talk about that in more detail.";
		WordTypes.init_answers("randomAnswer", words);
		
		words = "date time year tomorrow day today yesterday month";
		WordTypes.categorize("dateword", words);
	}
	
}

