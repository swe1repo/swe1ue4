package at.swe1ue4.unitTests;

import static org.junit.Assert.*;
import at.swe1ue4.plugins.PsyPlugin;
import at.swe1ue4.textparser.TextParser;
import org.junit.*;

public class PsyPluginTest {

	@Test
	public void testRateString() {
		PsyPlugin pp = new PsyPlugin();
		TextParser tp = new TextParser();
		tp.init_wordTypes();
		String text = "Hello Hal, I need your help.";
		assertEquals(pp.rateString(tp.readText(text)), 81);
		text = "Yes";
		assertEquals(pp.rateString(tp.readText(text)), 21);
		text = "The time please.";
		assertEquals(pp.rateString(tp.readText(text)), 1);
	}
	
	@Test
	public void testGetMessageForString() {
		PsyPlugin pp = new PsyPlugin();
		TextParser tp = new TextParser();
		tp.init_wordTypes();
		String text = "Hi!";
		assertNotSame(pp.getMessageForString(tp.readText(text)), "");
	}
	
	@Test
	public void testFoundKeyWord() {
		PsyPlugin pp = new PsyPlugin();
		TextParser tp = new TextParser();
		tp.init_wordTypes();
		assertEquals(pp.foundKeyWord("yees", "verb"), "roses are red\nviolets are blue\nsome poems rhyme\nbut not this one.");
	}
	
	@Test
	public void testGetRandomAnswer() {
		PsyPlugin pp = new PsyPlugin();
		TextParser tp = new TextParser();
		tp.init_wordTypes();
		assertNotSame(pp.getrandomAnswer(), "");
	}
}
