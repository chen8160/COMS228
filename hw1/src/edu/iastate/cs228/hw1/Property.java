package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * @author Yuxiang Chen
 * 
 *         This class maintains properties obtained from reading a configuration
 *         file.
 * 
 *         For methods that require copying values of a String array, you may
 *         write your own private method to do the copy or use the generic
 *         method
 * 
 *         public static <T> T[] Arrays.copyOf(T[] original, int newLength)
 * 
 *         E.g., String[] oldArray={"1","2","3"}; String[] newArray=null;
 * 
 *         newArray = Arrays.copyOf(oldArray, oldArray.length);
 * 
 *
 */
public class Property {

	/**
	 * Define three private member variables of type String[] for positive
	 * terms, negative terms, stop terms. Define one private variable of type
	 * int to keep the method for determining the sentiment scoring. Define one
	 * private variable of type double to store the minimum distance used to
	 * judge the sentiment of a given string.
	 * 
	 */

	private String[] positive;
	private String[] negative;
	private String[] stop;
	private int scoringmethod;
	private double mindistance;

	/**
	 * Default constructor
	 * 
	 * Set the default values of all member variables.
	 * 
	 * By default, the array of positive terms has "fun", "happy", "positive".
	 * By default, the array of negative terms has "sad", "bad", "angry". By
	 * default, the array of stop terms keeps "a", "an", "the". The default
	 * value of the scoring method is 0. The default value of the minimum
	 * distance is 0.5.
	 * 
	 */
	public Property() {
		positive = new String[] { "fun", "happy", "positive" };
		negative = new String[] { "sad", "bad", "angry" };
		stop = new String[] { "a", "an", "the" };
		scoringmethod = 0;
		mindistance = 0.5;
	}

	/**
	 * Copy constructor; deep copy
	 * 
	 * Copy all the values of the member variables of the input Property object
	 * to this object.
	 * 
	 * @param p
	 *            Property object as input
	 */

	public Property(Property p) {
		positive = Arrays.copyOf(p.positive, p.positive.length);
		negative = Arrays.copyOf(p.negative, p.negative.length);
		stop = Arrays.copyOf(p.stop, p.stop.length);
		scoringmethod = p.scoringmethod;
		mindistance = p.mindistance;
	}

	/**
	 * 
	 * Initialize member variables with values read from the given configuration
	 * file.
	 * 
	 * The content of the file is case sensitive.
	 * 
	 * Set all the properties to the default values first. Read the
	 * configuration file and overrides the default properties if the values in
	 * the configuration file are valid.
	 * 
	 * See the example of the configuration file in the assignment description.
	 * 
	 * We assume that users do not provide duplicated terms for positive terms,
	 * negative terms, or stop terms.
	 * 
	 * You do not need to check whether positive terms, negative terms, or stop
	 * terms overlap. You do not need to check duplicates within each array of
	 * terms.
	 * 
	 * Ignore any properties that do not match the above predefined properties.
	 * 
	 * Use try catch to handle IOException or NumberFormatException. If any of
	 * them occurs, print on the screen to indicate such an error and use the
	 * default values for all the properties like those set in the default
	 * constructor.
	 * 
	 * You do not need to handle other exceptions.
	 * 
	 * Properly close the file when done.
	 * 
	 * You may introduce your own private method to keep the code concise. No
	 * JUnit is needed for these private methods.
	 * 
	 * @param configFileName
	 *            Name of the configuration file
	 * @throws NullPointerException
	 *             if configFileName is null
	 * 
	 */
	public Property(String configFileName) {

		positive = new String[] { "fun", "happy", "positive" };
		negative = new String[] { "sad", "bad", "angry" };
		stop = new String[] { "a", "an", "the" };
		scoringmethod = 0;
		mindistance = 0.5;

		if (configFileName == null) {
			throw new NullPointerException();
		}
		Scanner in = null;
		Scanner scan = null;
		try {
			File conf = new File(configFileName);
			in = new Scanner(conf);
			while (in.hasNextLine()) {
				String line = in.nextLine().replace('=', ',');
				scan = new Scanner(line);
				scan.useDelimiter(",");
				String firstWord = scan.next();
				if (firstWord.equals("positive")) {
					positive = termsArray(scan);
				} else if (firstWord.equals("negative")) {
					negative = termsArray(scan);
				} else if (firstWord.equals("stop")) {
					stop = termsArray(scan);
				} else if (firstWord.equals("scoringmethod")) {
					String next = scan.next();
					scoringmethod = Integer.parseInt(next);
				} else if (firstWord.equals("mindistance")) {
					String next = scan.next();
					mindistance = Double.parseDouble(next);
				}
			}
		} catch (NumberFormatException ex) {
			System.out.println(ex.getMessage());
			positive = new String[] { "fun", "happy", "positive" };
			negative = new String[] { "sad", "bad", "angry" };
			stop = new String[] { "a", "an", "the" };
			scoringmethod = 0;
			mindistance = 0.5;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
			positive = new String[] { "fun", "happy", "positive" };
			negative = new String[] { "sad", "bad", "angry" };
			stop = new String[] { "a", "an", "the" };
			scoringmethod = 0;
			mindistance = 0.5;
		} finally{
			if(scan != null)
				scan.close();
		}
	}

	/**
	 * 
	 * @return A copy of the array of positive terms. May return an empty String
	 *         array if the orginal array is empty.
	 * 
	 */

	public String[] getPositiveTerms() {
		// TODO and replace return null with the appropriate code.
		return positive;
	}

	/**
	 * 
	 * Set the content of the positiveTerms array to the content of the newTerms
	 * array. Both arrays must have the same length and the same content once
	 * this method returns.
	 * 
	 * Make sure that positiveTerms and newTerms do not reference the same array
	 * object since we do not want the positiveTerm Property to change
	 * unknowingly because the content of newTerms are changed.
	 * 
	 * @param newTerms
	 *            Array of String of new words to use as positive terms
	 * @throws NullPointerException
	 *             when newTerms is null or the array is empty
	 * 
	 */
	public void setPositiveTerms(String[] newTerms) {
		// TODO
		if (newTerms == null) {
			throw new NullPointerException();
		}
		positive = Arrays.copyOf(newTerms, newTerms.length);
	}

	/**
	 * 
	 * @return A copy of the array of negative terms. May return an empty String
	 *         array if the orginal array is empty.
	 */

	public String[] getNegativeTerms() {
		// TODO and replace return null with the appropriate code.
		return negative;
	}

	/**
	 * 
	 * Set the content of the negativeTerms array to the content of the newTerms
	 * array. Both arrays must have the same length and the same content once
	 * this method returns.
	 * 
	 * Make sure that negativeTerms and newTerms do not reference the same array
	 * object since we do not want the negative terms of this Property object to
	 * change unknowingly because newTerms are changed.
	 * 
	 * @param newTerms
	 *            Array of String of new words to use as negative terms
	 * @throws NullPointerException
	 *             when newTerms is null or the array is empty
	 * 
	 */
	public void setNegativeTerms(String[] newTerms) {
		// TODO
		if (newTerms == null) {
			throw new NullPointerException();
		}
		negative = Arrays.copyOf(newTerms, newTerms.length);

	}

	/**
	 * 
	 * @return A copy of the array of stop terms May return an empty array if
	 *         the orginal array is empty.
	 */

	public String[] getStopTerms() {
		// TODO and replace return null with the appropriate code.
		return stop;
	}

	/**
	 * 
	 * Set the content of the stopTerms array to the content of the newTerms
	 * array. Both arrays must have the same length and the same content once
	 * this method returns.
	 * 
	 * Make sure that stopTerms and newTerms do not reference the same array
	 * object since we do not want the stop terms of this Property object to
	 * change unknowingly because newTerms are changed.
	 * 
	 * @param newTerms
	 *            Array of String of new words to use as stop terms
	 * @throws NullPointerException
	 *             when newTerms is null or the array is empty
	 * 
	 */
	public void setStopTerms(String[] newTerms) {
		// TODO
		if (newTerms == null) {
			throw new NullPointerException();
		}
		stop = Arrays.copyOf(newTerms, newTerms.length);
	}

	/**
	 * 
	 * @return Sentiment analysis scoring method
	 */
	public int getScoringMethod() {
		// TODO and replace return 0 with the appropriate code.
		return scoringmethod;
	}

	/**
	 * 
	 * @param method
	 *            Positive integer indicating which method is used for computing
	 *            the sentimental score
	 * @throws IllegalArgumentException
	 *             if the method value is not zero and is not 1.
	 */
	public void setScoringMethod(int method) {
		// TODO
		if (method != 1 && method != 0) {
			throw new IllegalArgumentException();
		}
		scoringmethod = method;
	}

	/**
	 * 
	 * @return Value of the member variable keeping the minimum distance for
	 *         sentiment classification
	 */
	public double getMinDistance() {
		// TODO and replace return 0.0f with the appropriate code.
		return mindistance;
	}

	/**
	 * Set the value of the member variable that keeps the minimum distance to
	 * the value of newdistance.
	 * 
	 * @param newdistance
	 *            New minimum distance
	 * @throws IllegalArgumentException
	 *             when the newdistance is not positive.
	 */
	public void setMinDistance(double newdistance) {
		// TODO
		if (newdistance <= 0) {
			throw new IllegalArgumentException();
		}
		mindistance = newdistance;
	}

	/**
	 * Two objects are equals if they have same values for all their private
	 * member variables: positiveTerms, negativeTerms, stopTerms, scoringMethod,
	 * and minDistance.
	 * 
	 * Implement the method using the guidelines given in class.
	 * 
	 * For comparing two arrays, make sure that the content of the two arrays at
	 * the same index have the same value and the two arrays have the same
	 * length. You may write your own private method to compare or you may use
	 * Arrays.equals() or Arrays.deepEquals() for this purpose. These two
	 * methods return different results for nested arrays.
	 * 
	 * For comparing doubles, two doubles are equal if they differ less than a
	 * small error range, defined as 0.000001 here.
	 * 
	 * 
	 */
	public boolean equals(Object o) {
		// TODO and replace return false with the appropriate code.
		if (o == null || o.getClass() != this.getClass())
			return false;
		Property p = (Property) o;
		return Arrays.deepEquals(p.positive, this.positive)
				&& Arrays.deepEquals(p.negative, this.negative)
				&& Arrays.deepEquals(p.stop, this.stop)
				&& p.scoringmethod == this.scoringmethod
				&& Math.abs(p.mindistance - this.mindistance) < 0.000001;
	}

	/**
	 * @return String "Property"
	 */
	public String toString() {
		// // TODO and replace return null with the appropriate code.
		return "Property";

	}
	
	/**
	 * Return the String of the Scanner as a String[] of words.
	 * @param scan
	 * A scanner which scans a line of sentence.
	 * @return
	 * a String[] which contains all the words in the sentence of the scanner.
	 */
	private String[] termsArray(Scanner scan){
		ArrayList<String> list = new ArrayList<String>();
		while (scan.hasNext()) {
			list.add(scan.next());
		}
		return (String[]) list.toArray(new String[0]);
	}
}
