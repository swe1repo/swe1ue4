package at.swe1ue4.plugins;

import java.util.Calendar;
import java.text.SimpleDateFormat;

import at.swe1ue4.textparser.WordTypes;

public class DatePlugin implements PluginInterface {
	public static final String ERROR_MESSAGE = "The Date Plugin could not answer the question.";
	
	@Override
	public int rateString(String[] text) {
		for(String token : text) {
			for(int i=0;i<WordTypes.dateword.size();i++)
			{	
				if(token.compareTo( WordTypes.dateword.get(i)) == 0 ) {
					return PluginInterface.MAX_RATING;
				}
			}
		}
		return 0;
	}
	public String now(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
		
	}
	
	@Override
	public String getMessageForString(String[] text) {
		String response;
		for(String token : text) {
			if(token.compareTo("date") == 0) {
				response = this.now("dd.MMMM.yyyy");
				return "The current date is " + response+".";
			}
			if(token.compareTo("time") == 0) {
				response = this.now("HH:mm:ss");
				return "It is " + response + " o'clock.";
			}
			if(token.compareTo("month") == 0) {
				response = this.now("MMMM");
				return "The current month is " + response+".";
			}
			if(token.compareTo("year") == 0) {
				response = this.now("yyyy");
				return "The current year is " + response+".";
			}
			if(token.compareTo("day") == 0) {
				response = this.now("EEEE");
				return "Today is " + response + ".";
			}
		}
		return ERROR_MESSAGE;
	}
}
