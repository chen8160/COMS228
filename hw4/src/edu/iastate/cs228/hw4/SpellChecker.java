package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;

public class SpellChecker {

	/**
	 * Displays usage information.
	 *
	 * There's no reason that you should need to modify this.
	 */
	private static void doUsage() {
		System.out.println("Usage: SpellChecker [-i] <dictionary> <document>\n"
				+ "                    -d <dictionary>\n"
				+ "                    -h");
	}

	/**
	 * Displays detailed usage information and exits.
	 *
	 * There's no reason that you should need to modify this.
	 */
	private static void doHelp() {
		doUsage();
		System.out
				.println("\n"
						+ "When passed a dictionary and a document, spell check the document.  Optionally,\n"
						+ "the switch -i toggles interactive mode; by default, the tool operates in non-\n"
						+ "interactive mode.  Interactive mode will write the corrected document to disk,\n"
						+ "backing up the uncorrected document by concatenating a tilde onto its name.\n\n"
						+ "The optional -d switch with a dictionary parameter enters dictionary edit mode.\n"
						+ "Dictionary edit mode allows the user to query and update a dictionary.  Upon\n"
						+ "completion, the updated dictionary is written to disk, while the original is\n"
						+ "backed up by concatenating a tilde onto its name.\n\n"
						+ "The switch -h displays this help and exits.");
		System.exit(0);
	}

	/**
	 * Runs the three modes of the SpellChecker based on the input arguments. DO
	 * NOT change this method in any way other than to set the name and section
	 * variables.
	 */
	public static void main(String[] args) {
		if (args.length == 0) {
			doUsage();
			System.exit(-1);
		}

		/* In order to be considered for the competition, set these variables. */
		String name = "Yuxiang Chen"; // First and Last
		String section = "A"; // "A" or "B"

		Timer timer = new Timer();

		timer.start();

		if (args[0].equals("-h"))
			doHelp();
		else if (args[0].equals("-i"))
			doInteractiveMode(args);
		else if (args[0].equals("-d"))
			doDictionaryEditMode(args);
		else
			doNonInteractiveMode(args);

		timer.stop();

		System.out.println("Student name:    " + name);
		System.out.println("Student section: " + section);
		System.out.println("Execution time: " + timer.runtime() + " ms");
	}

	/**
	 * Carries out the Interactive mode of the Spell Checker.
	 * 
	 * @param args
	 *            the arguments given to the main. The correct number of
	 *            arguments may or may not be contained in it. Call doUsage()
	 *            and exit if the parameter count is incorrect.
	 */
	public static void doInteractiveMode(String[] args) {
		if (args.length != 3) {
			doUsage();
			System.exit(-1);
		}
		File doc = new File(args[2]);
		Dictionary dictionary = null;
		Scanner scan = null;
		try {
			dictionary = new Dictionary(args[1]);
			scan = new Scanner(doc);
		} catch (IOException e) {
			System.out.println("File does not exist!");
			doUsage();
			System.exit(-1);
		}

		while (scan.hasNextLine()) {
			boolean loop = true;
			String l = scan.nextLine();
			while (loop) {
				String firstMistake = "";
				boolean hasMistake = false;
				System.out.println(l);
				Scanner line = new Scanner(l);
				String indicator = "";
				while (line.hasNext()) {
					String word = line.next();
					String temp = wordModifier(word);
					if (dictionary.hasWord(temp.toLowerCase())) {
						indicator += " ";
					} else {
						indicator += "^";
						hasMistake = true;
						if (firstMistake == "")
							firstMistake += temp;
					}
					for (int i = 0; i < word.length(); i++) {
						indicator += " ";
					}
				}
				line.close();
				if (hasMistake) {
					System.out.println(indicator);
					Scanner in = new Scanner(System.in);
					System.out.print(firstMistake + ": [r]eplace/[a]ccept? ");
					String opt = in.next();
					if (opt.equals("r")) {
						System.out.print("Replacement text: ");
						String replacement = in.next();
						l = l.replaceFirst(firstMistake, replacement);
					} else if (opt.equals("a")) {
						dictionary.addEntry(firstMistake);
					}
				} else
					loop = false;
			}
		}
	}

	/**
	 * Carries out the Non-Interactive mode of the Spell Checker.
	 * 
	 * @param args
	 *            the arguments given to the main. The correct number of
	 *            arguments may or may not be contained in it. Call doUsage()
	 *            and exit if the parameter count is incorrect.
	 */
	public static void doNonInteractiveMode(String[] args) {
		if (args.length != 2) {
			doUsage();
			System.exit(-1);
		}
		File doc = new File(args[1]);
		Dictionary dictionary = null;
		Scanner scan = null;
		try {
			dictionary = new Dictionary(args[0]);
			scan = new Scanner(doc);
		} catch (IOException e) {
			System.out.println("File does not exist!");
			doUsage();
			System.exit(-1);
		}

		while (scan.hasNextLine()) {
			boolean hasMistake = false;
			String l = scan.nextLine();
			System.out.println(l);
			String indicator = "";
			if (l.startsWith(" ")) {
				int i = 0;
				while (l.charAt(i) == ' ') {
					indicator += " ";
					i++;
				}
			}
			Scanner line = new Scanner(l);
			while (line.hasNext()) {
				String word = line.next();
				String temp = wordModifier(word);
				if (dictionary.hasWord(temp.toLowerCase())) {
					indicator += " ";
				} else {
					indicator += "^";
					hasMistake = true;
				}
				for (int i = 0; i < word.length(); i++) {
					indicator += " ";
				}
			}
			line.close();
			if (hasMistake)
				System.out.println(indicator);
		}
	}

	/**
	 * Carries out the Dictionary Edit mode of the Spell Checker.
	 * 
	 * @param args
	 *            the arguments given to the main. The correct number of
	 *            arguments may or may not be contained in it. Call doUsage()
	 *            and exit if the parameter count is incorrect.
	 */
	public static void doDictionaryEditMode(String[] args) {
		if (args.length != 2) {
			doUsage();
			System.exit(-1);
		}
		Dictionary dictionary = null;
		try {
			dictionary = new Dictionary(args[1]);
		} catch (IOException e) {
			System.out.println("No such File");
			doUsage();
			System.exit(-1);
		}
		System.out.println("Editing " + args[1]);
		boolean needBackUp = false;
		while (true) {
			Scanner in = new Scanner(System.in);
			System.out.print("Word: ");
			String word = in.next();
			if (word.equals("!quit")) {
				if (needBackUp) {
					try {
						Files.copy(new File(args[1]).toPath(), new File(args[1]
								+ "~").toPath());
						dictionary.printToFile(args[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.println("Wrote " + args[1]
							+ ". Original backed up to " + args[1] + "~");
				}
				break;
			}
			if (!checkValidity(word)) {
				System.out.println("'" + word
						+ "' is invalid. Please enter a valid word.");
				continue;
			}
			if (dictionary.hasWord(word.toLowerCase())) {
				System.out.println("'" + word + "' was found.");
				System.out
						.print("[r]emove/[g]et definition/[c]hange definition/do [n]othing: ");
				String opt = in.next();
				in.nextLine();
				if (opt.equals("r")) {
					dictionary.removeEntry(word.toLowerCase());
					needBackUp = true;
				} else if (opt.equals("g")) {
					String def = dictionary.getDefinitionOf(word.toLowerCase());
					if (def.equals("")) {
						System.out.println("<undefined>");
					} else {
						System.out.println(def);
					}
				} else if (opt.equals("c")) {
					System.out.print("Definition: ");
					String newDef = in.nextLine();
					dictionary.updateEntry(word.toLowerCase(), newDef);
					needBackUp = true;
				} else if (opt.equals("n")) {
				}
			} else {
				System.out.println("'" + word + "' not found.");
				System.out.print("[a]dd/add with [d]efinition/do [n]othng: ");
				String opt = in.next();
				in.nextLine();
				if (opt.equals("a")) {
					dictionary.addEntry(word.toLowerCase());
					needBackUp = true;
				} else if (opt.equals("d")) {
					System.out.print("Definition: ");
					String definition = in.nextLine();
					dictionary.addEntry(word, definition);
					needBackUp = true;
				} else if (opt.equals("n")) {
				}
			}
		}
	}

	/**
	 * Timer class used for this project's competition. DO NOT modify this class
	 * in any way or you will be ineligible for Eternal Glory.
	 */
	private static class Timer {
		private long startTime;
		private long endTime;

		public void start() {
			startTime = System.currentTimeMillis();
		}

		public void stop() {
			endTime = System.currentTimeMillis();
		}

		public long runtime() {
			return endTime - startTime;
		}
	}

	private static String wordModifier(String word) {
		for (int i = 0; i < word.length(); i++) {
			if (!(word.toLowerCase().charAt(i) >= 'a'
					&& word.toLowerCase().charAt(i) <= 'z'
					|| word.toLowerCase().charAt(i) == '-' || word
					.toLowerCase().charAt(i) == '\'')) {
				char c = word.charAt(i);
				word = word.replace(c, ' ');
			}
		}
		return word.trim();
	}

	private static boolean checkValidity(String word) {
		String temp = wordModifier(word);
		return word.equals(temp);
	}
}
