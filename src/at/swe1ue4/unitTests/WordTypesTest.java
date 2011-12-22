package at.swe1ue4.unitTests;

import org.junit.*;
import at.swe1ue4.textparser.WordTypes;
import junit.framework.TestCase;

public class WordTypesTest extends TestCase {
	@Test
	public void testCategorize() {
		String words = "why is are";
		WordTypes.categorize("questionword", words);
		assertEquals(3, WordTypes.questionword.size());
		assertEquals(WordTypes.questionword.get(1), "is");
	}
	@Test
	public void testInit_Answers() {
		String answer = "How aRe you?";
		WordTypes.init_answers("youAnswer", answer);
		assertEquals(1, WordTypes.youAnswer.size());
		assertEquals(answer, WordTypes.youAnswer.get(0));
	}
}
