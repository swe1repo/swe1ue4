package at.swe1ue4.unitTests;

import static org.junit.Assert.*;
import at.swe1ue4.textparser.TextParser;
import org.junit.*;

public class TextParserTest{
	//TODO: test all methods
	@Test
	public void testReadText() {
		TextParser tp = new TextParser();
		String[] word = new String[2];
		word[0] = "test";
		word[1] = "me";
		String tester = "test me";
		assertTrue((tp.readText(tester).length == word.length));
		assertTrue((tp.readText(tester)[0].equalsIgnoreCase(word[0])));
		assertTrue((tp.readText(tester)[1].equalsIgnoreCase(word[1])));
	}
	
	@Test
	public void testInit_Words() {
		
	}
}
