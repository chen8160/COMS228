package edu.iastate.cs228.hw4;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DictionaryTest {
	private static final boolean TEST_NULL = true;

	private static final boolean ADDENTRY_STRING = true;
	private static final boolean ADDENTRY_STRING_STRING = true;

	private static final boolean ENTRYCOUNT = true;
	private static final boolean HASWORD_STRING = true;
	private static final boolean GETDEFINITIONOF_STRING = true;
	private static final boolean REMOVEENTRY_STRING = true;
	private static final boolean UPDATEENTRY_STRING_STRING = true;
	private static final boolean GETSORTEDENTRIES = true;

	private Dictionary dictionary;

	@Before
	public void setupDictionary() throws Exception {
		dictionary = new Dictionary();
	}

	@Test
	public void testAddEntry() throws Exception {
		if (!ADDENTRY_STRING)
			testFail("testAddEntry");

		for (int i = 0; i < 20; i++) {
			String word = "Word" + (char) ('A' + i);

			addEntry("addEntry(\"" + word + "\") failed", true, word);
			hasWord('"' + word
					+ "\" wasn't in the dictionary after addEntry(\"" + word
					+ "\")", true, word);
			getDefinitionOf("addEntry(\"" + word + "\") added a definition",
					null, word);
		}

		addEntry("addEntry(\"dash-test\") returned false", true, "dash-test");
		hasWord("\"dash-test\" wasn't in dictionary after addEntry(\"dash-test\")",
				true, "dash-test");
		getDefinitionOf("addEntry(\"dash-test\") added a definition", null,
				"dash-test");

		addEntry("addEntry(\"apostrophe'test\") returned false", true,
				"apostrophe'test");
		hasWord("\"apostrophe'test\" wasn't in dictionary after addEntry(\"apostrophe'test\")",
				true, "apostrophe'test");
		getDefinitionOf("addEntry(\"apostrophe'test\") added a definition",
				null, "apostrophe'test");

		addEntry(
				"addEntry(\"dash-test\") returned true when the dictionary already had \"dash-test\"",
				false, "dash-test");
		addEntry("addEntry(\"Dash-test\") appears case insensitive", true,
				"Dash-test");

		if (TEST_NULL)
			addEntry("addEntry(null) returned true", false, null);
	}

	@Test
	public void testAddEntry1() throws Exception {
		if (!ADDENTRY_STRING_STRING)
			testFail("testAddEntry1");

		for (int i = 0; i < 20; i++) {
			String word = "Word" + (char) ('A' + i);
			String definition = String.format("Definition of %s", word);

			addEntry(word, definition);

			hasWord(String.format("addEntry(\"%s\", \"%s\") missed the word",
					word, definition), true, word);
			getDefinitionOf(String.format(
					"addEntry(\"%s\", \"%s\") missed the definition", word,
					definition), definition, word);
		}

		addEntry(
				"addEntry(\"WordA\", \"def\") returned true when the dictionary already had \"WordA\"",
				false, "WordA", "def");
		addEntry("addEntry(\"Worda\", \"def\") appears to be base insensitive",
				true, "Worda", "def");

		if (TEST_NULL) {
			addEntry("addEntry(null, null) returned true", false, null, null);
			addEntry("addEntry(null, \"nonNull\") returned true", false, null,
					"nonNull");

			// behavior here could vary based on implementation
			if (dictionary.addEntry("nonNull", null))
				getDefinitionOf("\"nonNull\" has the wrong definition", null,
						"nonNull");
		}
	}

	@Test
	public void testHasWord() throws Exception {
		if (!(ADDENTRY_STRING && ADDENTRY_STRING_STRING && HASWORD_STRING))
			testFail("testHasWord");

		hasWord("hasWord(\"skynet\") returned true when it had no words",
				false, "skynet");

		addEntry("wordA");
		hasWord("Expected hasWord(\"wordA\") == true", true, "wordA");

		addEntry("wordB", "some definition");
		hasWord("Expected hasWord(\"wordB\") == true", true, "wordB");

		hasWord("hasWord(\"WoRdA\") returned true", false, "WoRdA");

		hasWord("hasWord(\"wordAd\") seems to only check part of the input",
				false, "wordAd");

		hasWord("hasWord(\"\") can't handle empty Strings", false, "");

		String[] words = new String[] { "life", "the", "universe", "and",
				"everything", "dash-test", "apostrophe'test",
				"cAPiTAlizATiONTEsTSaREsoCOOL" };

		for (String word : words) {
			addEntry(word);
			hasWord("hasWord(\"" + word + "\") returned false", true, word);
		}
		for (String word : words)
			hasWord("hasWord(\"" + word + "\") returned false", true, word);

		if (TEST_NULL)
			hasWord("hasWord(null) can't handle null input", false, null);
	}

	@Test
	public void testGetDefinitionOf() throws Exception {
		if (!((ADDENTRY_STRING || ADDENTRY_STRING_STRING) && GETDEFINITIONOF_STRING))
			testFail("testGetDefinitionOf");

		try {
			getDefinitionOf(
					"getDefinitionOf(\"\") didn't throw IllegalArgumentException",
					null, "");
			fail("getDefinitionOf(\"\") didn't throw IllegalArgumentException");
		} catch (IllegalArgumentException a) {

		} catch (Exception e) {
			fail("Wrong exception thrown: " + e.toString());
		}

		if (ADDENTRY_STRING) {
			addEntry("wordA");
			getDefinitionOf(
					"getDefinitionOf(\"wordA\") returned definition when none existed",
					null, "wordA");
		}

		if (ADDENTRY_STRING_STRING) {
			addEntry("wordB", "");
			getDefinitionOf(
					"getDefinitionOf(\"wordB\") can't handle empty definitions",
					"", "wordB");

			addEntry("wordC", "definition 2");
			getDefinitionOf(
					"getDefinitionOf(\"wordC\") expected \"definition 2\"",
					"definition 2", "wordC");
		}

		if (TEST_NULL) {
			try {
				getDefinitionOf("getDefinitionOf(null) returned a value",
						"',.pyf", null);
				fail("getDefinitionOf(null) returned a value");
			} catch (IllegalArgumentException e) {

			}
		}
	}

	@Test
	public void testRemoveEntry() throws Exception {
		if (!REMOVEENTRY_STRING)
			testFail("testRemoveEntry");

		removeEntry("removeEntry(\"\") returned true on an empty dictionary",
				false, "");

		if (ADDENTRY_STRING) {
			addEntry("test");

			removeEntry("removeEntry(\"asdf\") returned true", false, "asdf");
			hasWord("removeEntry(\"asdf\") removed \"test\"", true, "test");

			removeEntry(
					"removeEntry(\"Test\") appears case insensitive, removed \"test\"",
					false, "Test");
			hasWord("removeEntry(\"Test\") removed \"test\"", true, "test");

			removeEntry("removeEntry(\"\") returned true", false, "");
			hasWord("removeEntry(\"\") removed \"test\"", true, "test");

			removeEntry("removeEntry(\"test\") returned false", true, "test");
			hasWord("removeEntry(\"test\") didn't remove \"test\"", false,
					"test");
		}

		if (TEST_NULL)
			removeEntry("removeEntry(null) returned true", false, null);
	}

	@Test
	public void testUpdateEntry() throws Exception {
		if (!(UPDATEENTRY_STRING_STRING && (ADDENTRY_STRING || ADDENTRY_STRING_STRING)))
			testFail("testUpdateEntry");

		updateEntry("updateEntry(\"\") failed on an empty dictionary", false,
				"", "");

		if (ADDENTRY_STRING) {
			addEntry("wordA");

			updateEntry(
					"updateEntry(\"wordA\", \"definition\") returned false",
					true, "wordA", "definition");
			getDefinitionOf(
					"updateEntry(\"wordA\", \"definition\") didn't change the definition",
					"definition", "wordA");

			addEntry("test");
			updateEntry(
					"This shouldn't fail - updateEntry(\"test\", \"def\") returned false",
					true, "test", "def");
		}

		if (ADDENTRY_STRING_STRING) {
			addEntry("wordB", "definition");

			updateEntry(
					"updateEntry(\"wordB\", \"new definition\") returned false",
					true, "wordB", "new definition");
			getDefinitionOf(
					"updateEntry(\"wordB\", \"new definition\") didn't change the definition",
					"new " + "definition", "wordB");

			if (!ADDENTRY_STRING)
				addEntry("test", "def");
		}

		updateEntry("updateEntry(\"test\", \"Def\") returned false", true,
				"test", "Def");
		getDefinitionOf(
				"updateEntry(\"test\", \"Def\") changed \"Def\"'s case", "Def",
				"test");

		if (TEST_NULL) {
			updateEntry("updateEntry(null, null) returned true", false, null,
					null);
			updateEntry("updateEntry(null, \"not null\") returned true", false,
					null, "not null");
			updateEntry("updateEntry(\"notNull\", null) returned true", false,
					"notNull", null);

			// Behavior here could vary based on implementation
			if (dictionary.updateEntry("test", null))
				getDefinitionOf(
						"updateEntry(\"test\", null) didn't change definition",
						null, "test");
		}
	}

	@Test
	public void testEntryCount() throws Exception {
		if (!(ENTRYCOUNT && ADDENTRY_STRING && ADDENTRY_STRING_STRING))
			testFail("testEntryCount");

		int count = 0;

		entryCount("entryCount() didn't return " + count, count);

		for (int i = 0; i < 20; i++) {
			String word = Character.toString((char) ('a' + i));
			addEntry(word);
			count++;
			entryCount("entryCount() didn't return " + count
					+ " after addEntry(\"" + word + "\")", count);
		}

		for (int i = 0; i < 20; i++) {
			String word = "a" + (char) ('a' + i);
			addEntry(word, "def");
			count++;
			entryCount("entryCount() didn't return " + count
					+ " after addEntry(\"" + word + "\", \"def\")", count);
		}

		addEntry(
				"addEntry(\"a\") returned true when \"a\" was already defined",
				false, "a");
		entryCount(
				"entryCount() incremented when addEntry(\"a\") returned false",
				count);

		addEntry(
				"addEntry(\"aa\", \"def\") returned true when \"aa\" was already defined",
				false, "aa", "def");
		entryCount(
				"entryCount() incremented when addEntry(\"aa\", \"def\") returned false",
				count);

		if (TEST_NULL) {
			addEntry("addEntry(null) returned true", false, null);
			entryCount("entryCount() incremented on addEntry(null)", count);

			addEntry("addEntry(null, null) returned true", false, null, null);
			entryCount("entryCount() incremented on addEntry(null, null)",
					count);

			addEntry("addEntry(null, \"not null\") returned true", false, null,
					"not null");
			entryCount(
					"entryCount() incremented on addEntry(null, \"not null\")",
					count);

			// behavior here depends on the implementation
			if (dictionary.addEntry("notNull", null))
				entryCount(
						"entryCount() didn't increment on addEntry(\"notNull\", null)",
						++count);
			else
				entryCount(
						"entryCount() incremented on addEntry(\"notNull\", null)",
						count);
		}
	}

	@Test
	public void testGetSortedEntries() throws Exception {
		if (!GETSORTEDENTRIES)
			testFail("testGetSortedEntries");

		Dictionary.Entry[] entries = new Dictionary.Entry[100];

		for (int i = 0; i < entries.length; i++) {
			String string = "";

			for (int j = 0; j < 5; j++)
				string += (char) ('A' + Math.random() * 27);

			entries[i] = new Dictionary.Entry(string);
			dictionary.addEntry(string);
		}

		ArrayList<Dictionary.Entry> sortedEntries = dictionary
				.getSortedEntries();
		Dictionary.Entry[] sortedEntryArray = new Dictionary.Entry[sortedEntries
				.size()];
		sortedEntries.toArray(sortedEntryArray);

		Arrays.sort(entries);

		assertTrue(
				"getSortedEntries() returned an incorrectly sorted ArrayList<Entry>",
				Arrays.equals(entries, sortedEntryArray));
	}

	private void addEntry(String entry) {
		addEntry(null, true, entry);
	}

	private void addEntry(String entry, String definition) {
		addEntry(null, true, entry, definition);
	}

	private void addEntry(String message, boolean expected, String entry) {
		if (ADDENTRY_STRING)
			assertEquals(message, expected, dictionary.addEntry(entry));
	}

	private void addEntry(String message, boolean expected, String entry,
			String definition) {
		if (ADDENTRY_STRING_STRING)
			assertEquals(message, expected,
					dictionary.addEntry(entry, definition));
	}

	private void entryCount(String message, int expected) {
		if (ENTRYCOUNT)
			assertEquals(message, expected, dictionary.entryCount());
	}

	private void hasWord(String message, boolean expected, String word) {
		if (HASWORD_STRING)
			assertEquals(message, expected, dictionary.hasWord(word));
	}

	private void getDefinitionOf(String message, String expected, String word) {
		if (GETDEFINITIONOF_STRING) {
			String result = dictionary.getDefinitionOf(word);

			if (expected == null)
				assertTrue(message,
						result == null || result.equals("<undefined>")
								|| result.length() == 0);
			else
				assertEquals(message, expected, result);
		}
	}

	private void removeEntry(String message, boolean expected, String entry) {
		if (REMOVEENTRY_STRING)
			assertEquals(message, expected, dictionary.removeEntry(entry));
	}

	private void updateEntry(String message, boolean expected, String word,
			String definition) {
		if (UPDATEENTRY_STRING_STRING)
			assertEquals(message, expected,
					dictionary.updateEntry(word, definition));
	}

	private void testFail(String method) {
		fail(String.format("Required methods for %s() not tested", method));
	}
}