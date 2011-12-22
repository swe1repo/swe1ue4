package at.swe1ue4.unitTests;

import static org.junit.Assert.*;
import at.swe1ue4.textparser.TextParser;
import org.junit.*;

public class TextParserTest{
	@Test
	public void testReadText() {
		TextParser tp = new TextParser();
		String[] word = new String[2];
		word[0] = "tesT";
		word[1] = "me";
		String tester = "tEst me";
		assertTrue((tp.readText(tester)[0].equalsIgnoreCase(word[0])));
		assertTrue((tp.readText(tester)[1].equalsIgnoreCase(word[1])));
		assertEquals(tp.readText(tester)[0], word[0].toLowerCase());
	}
}
