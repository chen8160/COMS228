package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Dictionary class.
 * 
 * @author Yuxiang Chen
 *
 */
public class Dictionary {
	/**
	 * An instance of a BinarySearchTree which stores this Dictionary's list of
	 * words.
	 */

	private BinarySearchTree<Entry> tree;

	/**
	 * Constructs a new Dictionary which is empty.
	 */
	public Dictionary() {
		tree = new BinarySearchTree<Entry>();
	}

	/**
	 * Constructs a new Dictionary whose word list is exactly (a deep copy of)
	 * the list stored in the given tree. (For testing purposes, you can set
	 * this Dictionary's BST to the given BST, rather clone it, but your final
	 * method must do the deep copy)
	 * 
	 * @param tree
	 *            - The tree of the existing word list
	 */
	public Dictionary(BinarySearchTree<Entry> tree) {
		if (tree.isEmpty()) {
			this.tree = new BinarySearchTree<Entry>();
		} else {
			this.tree = new BinarySearchTree<Entry>();
			ArrayList<Entry> arr = tree.getPreorderTraversal();
			if (arr.size() != 0) {
				for (Entry e : arr) {
					this.tree.add(e.copy());
				}
			}
		}
	}

	/**
	 * Constructs a new Dictionary from the file specified by the given file
	 * name. Each line of the file will contain at least one word with an
	 * optional definition. Each line will have no leading or trailing
	 * whitespace. For each line of the file, create a new Entry containing the
	 * word and definition (if given) and add it to the BST.
	 * 
	 * @param filename
	 *            - The file containing the wordlist
	 * @throws FileNotFoundException
	 *             - If the given file does not exist
	 */
	public Dictionary(String filename) throws FileNotFoundException {
		tree = new BinarySearchTree<Entry>();
		File f = new File(filename);
		Scanner scan = new Scanner(f);
		while (scan.hasNextLine()) {
			Scanner line = new Scanner(scan.nextLine());
			while (line.hasNext()) {
				String word = line.next();
				String definition = "";
				if (line.hasNext()) {
					definition = line.next();
					if (line.hasNextLine()) { // this is for eliminating the
												// space
												// at the beginning.
						definition += line.nextLine();
					}
				}
				addEntry(word, definition);
			}
			line.close();
		}
		scan.close();
	}

	/**
	 * Adds a new Entry to the Dictionary for the given word with no definition.
	 * 
	 * @param word
	 *            - The word to add to the Dictionary
	 * @return true only if the Entry was successfully added to the Dictionary,
	 *         false otherwise.
	 */
	public boolean addEntry(String word) {
		if (word == null)
			throw new NullPointerException();
		return addEntry(word, "");
	}

	/**
	 * Adds a new Entry to the Dictionary for the given word and definition.
	 * 
	 * @param word
	 *            - The word to add to the Dictionary
	 * @param definition
	 *            - The definition of the given word
	 * @return true only if the Entry was successfully added to the Dictionary,
	 *         false otherwise.
	 */
	public boolean addEntry(String word, String definition) {
		if (word == null || definition == null)
			throw new NullPointerException();
		Entry e = new Entry(word, definition);
		return tree.add(e);
	}

	/**
	 * Tests whether or not word exists in this Dictionary.
	 * 
	 * @param word
	 *            - The word to test.
	 * @return true is word exists in this Dictionary, false otherwise.
	 */
	public boolean hasWord(String word) {
		return tree.contains(new Entry(word));
	}

	/**
	 * Returns the definition of the given word in the Dictionary, if it is
	 * there.
	 * 
	 * @param word
	 *            - The word to retrieve the definition of.
	 * @return the definition of the word.
	 * @throws IllegalArgumentExeception
	 *             - If word does not exist in the Dictionary.
	 */
	public String getDefinitionOf(String word) throws IllegalArgumentException {
		if (!hasWord(word))
			throw new IllegalArgumentException();
		return tree.get(new Entry(word)).definition;
	}

	/**
	 * Removes the given word from the word dictionary if it is there.
	 * 
	 * @param word
	 *            - The word to remove from Dictionary.
	 * @return true only if the word is successfully removed from the
	 *         BinarySearchTree, false otherwise.
	 */
	public boolean removeEntry(String word) {
		return tree.remove(new Entry(word));
	}

	/**
	 * Changes the definition of given word if it is there.
	 * 
	 * @param word
	 *            - The word to change the definition of
	 * @param newDef
	 *            - The new definition of the word
	 * @return true if the definition was successfully updated, false otherwise.
	 */
	public boolean updateEntry(String word, String newDef) {
		if (!hasWord(word))
			return false;
		Entry e = tree.get(new Entry(word));
		e.definition = newDef;
		return true;
	}

	/**
	 * Outputs this Dictionary to the given file. The file should be formatted
	 * as follows: 1) One word and definition should appear per line separated
	 * by exactly one space. 2) Lines should not have any leading or trailing
	 * whitespace except for a single newline. 3) Each line of the file should
	 * have text. There should be no empty lines. 4) The words should be sorted
	 * alphabetically (i.e. using the BST's inorder traversal)
	 * 
	 * @param filename
	 * @throws FileNotFoundException
	 */
	public void printToFile(String filename) throws FileNotFoundException {
		File f = new File(filename);
		PrintWriter pw = new PrintWriter(f);
		Iterator<Entry> iter = tree.iterator();
		while (iter.hasNext()) {
			Entry next = iter.next();
			pw.print(next.word + " " + next.definition + "\n");
		}
		pw.close();
	}

	/**
	 * Returns the number of items stored in the Dictionary.
	 */
	public int entryCount() {
		return tree.size();
	}

	/**
	 * Returns a sorted list of Entries (as returned by an inorder traversal of
	 * the BST)
	 * 
	 * @return an ArrayList of sorted Entries
	 */
	public ArrayList<Entry> getSortedEntries() {
		return tree.getInorderTraversal();
	}

	/**
	 * A Key-Value Pair class which represents an entry in a Dictionary.
	 * 
	 * @author Yuxiang Chen
	 */
	public static class Entry implements Comparable<Entry> {

		/**
		 * Instance variables storing the word and definition of this Entry
		 */

		/**
		 * The string of the word being stored in the dictionary.
		 */
		private String word;
		/**
		 * The definition of the word. Not necessary.
		 */
		private String definition;

		/**
		 * Constructs a new Entry with the given word with no definition
		 * 
		 * @param w
		 *            - The word to create an entry for.
		 */
		public Entry(String w) {
			word = w.toLowerCase();
			definition = "";
		}

		/**
		 * Constructs a new Entry with the given word and definition
		 * 
		 * @param w
		 *            - The word to create an entry for.
		 * @param d
		 *            - The definition of the given word.
		 */
		public Entry(String w, String d) {
			word = w.toLowerCase();
			definition = d;
		}

		/**
		 * Compares the word contained in this entry to the word in other.
		 * Returns a value < 0 if the word in this Entry is alphabetically
		 * before the other word, = 0 if the words are the same, and > 0
		 * otherwise.
		 */
		@Override
		public int compareTo(Entry other) {
			return word.compareTo(other.word);
		}

		/**
		 * Tests for equality of this Entry with the given Object. Two entries
		 * are considered equal if the words they contain are equal regardless
		 * of their definitions.
		 */
		@Override
		public boolean equals(Object o) {
			if (o == null || o.getClass() != this.getClass())
				return false;
			Entry e = (Entry) o;
			return word.equals(e.word);
		}

		/**
		 * Create a deep copy of the entry.
		 * 
		 * @return a copy of entry.
		 */
		private Entry copy() {
			return new Entry(word, definition);
		}
	}
}
