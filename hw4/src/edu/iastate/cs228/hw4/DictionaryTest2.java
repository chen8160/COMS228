package edu.iastate.cs228.hw4;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.iastate.cs228.hw4.Dictionary.Entry;

/**
 * I got points taken off on ChunkyList because I forgot the author tag. I will
 * not do that again! I promise!
 *
 * Hello everyone, I noticed there wasn't any junits for this project yet so I
 * decided to share mine.
 *
 * If anything is WRONG, don't notify me personally, just make a comment in the
 * reply section for others to see.
 *
 * Please note that this is a very brief junit to test very basic functions of
 * the dictionary file. This is by no means an end-all-be-all test file to make
 * sure your code is perfect. That you should be able to do on your own using
 * the spec examples.
 *
 * Also note that this covers all functions in the dictionary file EXCEPT
 * printToFile because I didn't even want to begin how to make a test for that
 * (I did it on my own). Furthermore (last one), this test file may be hard to
 * follow but just do your best.
 *
 * If you're still reading this, you're a great person and you will grow up to
 * make lots of money. Probably. We can dream.
 *
 * @author Joey Elliott
 *
 */
public class DictionaryTest2 {

	@Test
	public void test() {
		Dictionary dictionary = new Dictionary();
		String[] words = { "zero", "one", "two", "three", "four", "five",
				"six", "seven", "eight", "nine" };
		String[] moreWords = { "d", "f", "c", "g", "e" };
		String[] definitions = { "nothing", "a single unit", "a pair of units",
				null, null, null, null, null, null, "last digit" };

		// This is only in here because my unlink method
		// was NOT working properly, but I kept it in here
		// in case others might want it
		dictionary.addEntry("firstelement");
		dictionary.addEntry("secondelement");
		dictionary.addEntry("thirdelement");
		dictionary.removeEntry("firstelement");
		dictionary.removeEntry("secondelement");
		dictionary.removeEntry("thirdelement");

		// addEntry(word) and hasWord(word)
		dictionary.addEntry("firstword");
		assertTrue(dictionary.hasWord("firstword"));

		// removeEntry(word) and hasWord(word)
		dictionary.removeEntry("firstword");
		assertTrue(!dictionary.hasWord("firstword"));

		// addEntry(word) and updateEntry(word, def) and getDefinitionOf(word)
		dictionary.addEntry("a");
		dictionary.updateEntry("a", "First letter in a series.");
		assertTrue(dictionary.getDefinitionOf("a").equals(
				"First letter in a series."));

		// addEntry(word, def) and getDefinitionOf(word)
		dictionary.addEntry("b", "Second letter in a series.");
		assertTrue(dictionary.getDefinitionOf("b").equals(
				"Second letter in a series."));

		// addEntry(word) multiple times with hasWord(word) multiple times, then
		// removeEntry(word) multiple times
		for (int i = 0; i < words.length; i++) {
			dictionary.addEntry(words[i]);
		}
		for (int i = 0; i < words.length; i++) {
			assertTrue(dictionary.hasWord(words[i]));
		}
		for (int i = 0; i < words.length; i++) {
			assertTrue(dictionary.removeEntry(words[i]));
		}

		// At this point, the list should only contain a and b
		assertTrue(dictionary.entryCount() == 2);

		// Now let's test the getSortedEntries
		// Remember a and b are still in the list
		// but now we are adding: c d e f g
		for (int i = 0; i < moreWords.length; i++) {
			dictionary.addEntry(moreWords[i]);
		}

		ArrayList<Entry> arraylist = dictionary.getSortedEntries();
		// c d e f g
		ArrayList<Entry> expected = new ArrayList<Entry>();
		expected.add(new Entry("a"));
		expected.add(new Entry("b"));
		expected.add(new Entry("c"));
		expected.add(new Entry("d"));
		expected.add(new Entry("e"));
		expected.add(new Entry("f"));
		expected.add(new Entry("g"));
		assertTrue(arraylist.equals(expected));

	}

}