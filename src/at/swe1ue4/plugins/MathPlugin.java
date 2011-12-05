package at.swe1ue4.plugins;

import java.util.HashMap;
import java.util.Map;

import at.swe1ue4.textparser.WordTypes;


public class MathPlugin implements PluginInterface {
	int rating = 0;
	int numberflag = 0;
	final static int RATING_OPERATOR = 50;
	final static int RATING_NUMBER = 25;
	final static int OPERATOR_UNKNOWN = 0;
	final static int OPERATOR_PLUS = 1;
	final static int OPERATOR_MINUS = 2;
	final static int OPERATOR_DIVIDE = 3;
	final static int OPERATOR_MULT = 4;
	final static String ERROR_STRING = "Diese Frage kann ich als Mathematiker leider nicht beantworten\n";
	final static Map<Integer, String> OPERATOR_MAP;
	
	static {
		Map<Integer, String> tmp = new HashMap<Integer, String>();
		tmp.put(OPERATOR_UNKNOWN, "");
		tmp.put(OPERATOR_PLUS, "+");
		tmp.put(OPERATOR_MINUS, "-");
		tmp.put(OPERATOR_DIVIDE, "/");
		tmp.put(OPERATOR_MULT, "*");
		OPERATOR_MAP = tmp;
	}

	@Override
	public int rateString(String[] text) {
		rating=0;
		for(String token : text) {
			for(Map.Entry<Integer, String> entry : OPERATOR_MAP.entrySet() )
			{	
				//what gets checked here???
				if(token.contains( entry.getValue() ) ) {
					//rating += RATING_OPERATOR;					
				}
			}
			for(int i=0;i<WordTypes.operator.size();i++) {
				if(token.compareTo(WordTypes.operator.get(i)) == 0) {
					rating += RATING_OPERATOR;
				}
			}
			for(int i=0;i<WordTypes.realnumber.size();i++) {
				if(token.compareTo(WordTypes.realnumber.get(i)) == 0) {
					rating += RATING_NUMBER;
					numberflag = 1;
				}
			}
			if(numberflag == 0) {
				return rating = PluginInterface.MIN_RATING;
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
		double op1 = 0, op2 = 0, res = 0;
		int operationType = OPERATOR_UNKNOWN;
		
		
		int i;
		// determine the operation type
		for(i = 0; i < text.length; i++) {
			if(text[i].contains( OPERATOR_MAP.get(OPERATOR_PLUS) )) {
				operationType = OPERATOR_PLUS;
				break;
			} else if(text[i].contains( OPERATOR_MAP.get(OPERATOR_MINUS) )) {
				operationType = OPERATOR_MINUS;
				break;
			} else if(text[i].contains( OPERATOR_MAP.get(OPERATOR_DIVIDE) )) {
				operationType = OPERATOR_DIVIDE;
				break;
			} else if(text[i].contains( OPERATOR_MAP.get(OPERATOR_MULT) )) {
				operationType = OPERATOR_MULT;
				break;
			}
		}
		
		// obtain the operands
		try {
			if(operationType != OPERATOR_UNKNOWN) {
				if(text[i].length() == 1) {
					// operators are the adjacent words
					op1 = Double.parseDouble(text[i-1]);
					op2 = Double.parseDouble(text[i+1]);
				} else {
					// operators must be within the word
					String[] operators = text[i].split( "\\" + OPERATOR_MAP.get(operationType) );
					
					if(operators.length != 2) {
						return ERROR_STRING;
					}
					
					op1 = Double.parseDouble(operators[0]);
					op2 = Double.parseDouble(operators[1]);
				}
			}
		} catch(NumberFormatException e) {
			return ERROR_STRING;
		}
		
		// calculate the result
		switch(operationType) {
		case OPERATOR_PLUS:
			res = op1 + op2;
			break;
		case OPERATOR_MINUS:
			res = op1 - op2;
			break;
		case OPERATOR_DIVIDE:
			res = op1 / op2;
			break;
		case OPERATOR_MULT:
			res = op1 * op2;
			break;
		case OPERATOR_UNKNOWN:
		default:
			return ERROR_STRING;
		}
		
		// pass back the result
		return "Das Ergebnis der Rechnung " + op1 + " " + OPERATOR_MAP.get(operationType) + " " + op2 + " ist " + res + ".\n" ;
	}

}
