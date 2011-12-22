package at.swe1ue4.unitTests;

import static org.junit.Assert.*;
import at.swe1ue4.plugins.MathPlugin;
import at.swe1ue4.textparser.TextParser;
import org.junit.*;

public class MathPluginTest {
	@Test
	public void testRateString() {
		MathPlugin mp = new MathPlugin();
		TextParser tp = new TextParser();
		String text = "1+2?";
		assertEquals(mp.rateString(tp.readText(text)), 100);
	}
	
	@Test
	public void testGetMessageForString() {
		MathPlugin mp = new MathPlugin();
		TextParser tp = new TextParser();
		String text = "4/2";
		String text_err = "asd";
		String resp = "The result of the calculation 4.0 / 2.0 is 2.0.";
		assertEquals(mp.getMessageForString(tp.readText(text)), resp);
		assertEquals(mp.getMessageForString(tp.readText(text_err)), mp.ERROR_STRING);
	}
}
