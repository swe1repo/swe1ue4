package at.swe1ue4.unitTests;

import static org.junit.Assert.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import at.swe1ue4.plugins.DatePlugin;
import at.swe1ue4.textparser.TextParser;
import at.swe1ue4.textparser.WordTypes;
import org.junit.*;

public class DatePluginTest {
	@Test
	public void testRateString() {
		DatePlugin dp = new DatePlugin();
		TextParser tp = new TextParser();
		String text = "What time is it?";
		WordTypes.dateword.add("time");
		assertEquals(dp.rateString(tp.readText(text)), 100);
	}
	
	@Test
	public void testNow() {
		DatePlugin dp = new DatePlugin();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		assertEquals(dp.now("yyyy"), sdf.format(cal.getTime()));
	}
	
	@Test
	public void testGetMessageForString() {
		String text = "What year is it?";
		TextParser tp = new TextParser();
		DatePlugin dp = new DatePlugin();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		String should = sdf.format(cal.getTime()) + ".";
		assertEquals(dp.getMessageForString(tp.readText(text)), "The current year is "+should);
	}
}
