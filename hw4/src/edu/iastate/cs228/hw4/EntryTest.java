package edu.iastate.cs228.hw4;

import edu.iastate.cs228.hw4.Dictionary.Entry;
import org.junit.Test;

import static org.junit.Assert.*;

public class EntryTest {
	private static final boolean TEST_NULL = false;

	@Test
	public void testCompareTo() throws Exception {
		String sensitive = "%s.compareTo(%s) seems to be sensitive to Entry definitions";

		Entry lowWord, lowDef;
		Entry highWord, highDef;

		lowWord = new Entry("BBBB");
		lowDef = new Entry("BBBB", "ZZ");

		highWord = new Entry("YYYYY");
		highDef = new Entry("YYYYY", "AA");

		assertEquals(sensitive, 0, lowWord.compareTo(lowDef));
		assertEquals(sensitive, 0, lowDef.compareTo(lowWord));
		assertEquals(sensitive, 0, highWord.compareTo(highDef));
		assertEquals(sensitive, 0, highDef.compareTo(highWord));

		assertTrue("lowWord.compareTo(highWord) >= 0",
				lowWord.compareTo(highWord) < 0);
		assertTrue("lowDef.compareTo(highDef) >= 0",
				lowDef.compareTo(highDef) < 0);
		assertTrue(String.format(sensitive, "lowWord", "highDef"),
				lowWord.compareTo(highDef) < 0);
		assertTrue(String.format(sensitive, "lowDef", "highWord"),
				lowDef.compareTo(highWord) < 0);

		assertTrue("highWord.compareTo(lowWord) <= 0",
				highWord.compareTo(lowWord) > 0);
		assertTrue("highDef.compareTo(lowDef) <= 0",
				highDef.compareTo(lowDef) > 0);
		assertTrue(String.format(sensitive, "highWord", "lowDef"),
				highWord.compareTo(lowDef) > 0);
		assertTrue(String.format(sensitive, "highDef", "lowWord"),
				highDef.compareTo(lowWord) > 0);

		Entry shortEntry = new Entry("NN");
		Entry longEntry = new Entry("NNNN");

		assertTrue("shortEntry.compareTo(longEntry) >= 0",
				shortEntry.compareTo(longEntry) < 0);
		assertTrue("longEntry.compareTo(shortEntry) <= 0",
				longEntry.compareTo(shortEntry) > 0);

		if (TEST_NULL) {
			// This is simply testing for a crash
			shortEntry.compareTo(null);
		}
	}

	@Test
	public void testEquals() throws Exception {
		Entry wordNoDef = new Entry("word");
		Entry wordDef = new Entry("word", "Definition");

		Entry otherNoDef = new Entry("other");
		Entry otherDef = new Entry("other", "Definition");

		assertTrue("wordNoDef.equals(wordNoDef) returned false",
				wordNoDef.equals(wordNoDef));
		assertTrue("equals(Entry) appears to use ==",
				wordNoDef.equals(new Entry("word")));
		assertTrue(
				"wordNoDef.equals(wordDef) appears to be sensitive to the definition",
				wordNoDef.equals(wordDef));

		assertFalse(
				"wordNoDef.equals(otherNoDef) returned true, doesn't correctly check the words",
				wordNoDef.equals(otherNoDef));
		assertFalse(
				"wordDef.equals(otherDef) appears to be sensitive to the definition",
				wordDef.equals(otherDef));

		if (TEST_NULL) {
			assertFalse("wordNoDef.compareTo(null) returned true",
					wordNoDef.equals(null));
		}
	}
}