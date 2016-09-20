package edu.iastate.cs228.hw1;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Yuxiang Chen
 * 
 * This class is a sub-class of TextAnalyzer and implements ISentiment.
 * 
 *
 */
public class NaiveTextAnalyzer extends TextAnalyzer implements ISentiment {
	
	/** 
	 * Define a private member variable of type Property to keep the reference to a Property object 
	 * storing various properties from the configuration file. 
	 */
	
	//TODO
	private Property property;
	
	/**
	 * Default constructor
	 * 
	 * Call the setName method of TextAnalyzer using "Naive Text Sentiment Analyzer" as the argument.
	 */
	public NaiveTextAnalyzer() {
		// TODO
		setName("Naive Text Sentiment Analyzer");
	}
	
	/**
	 * Call the setName method of Text Analyzer using the input string of this method as the argument.
	 * 
	 * @param newname
	 */
	public NaiveTextAnalyzer(String newname) {
		// TODO
		setName(newname);
	}
	
	/**
	 * Find sentiment of a given input string s. 
	 * 
	 * Follow this guideline.
	 * 
	 * - Eliminate all full stops, commas, semi-colons from s. 
	 *   You do not need to check other punctuations.
	 *   
	 * - Separate the string into words using whitespace characters [ \t\n\x0B\f\r].
	 * 
	 * - Count the number of negative words in s.
	 * - Count the number of positive words in s.
	 * - Count the number of stop words in s.
	 * - Count the total number of words in s.
	 * 
	 * - Compute the sentiment score using these counts.
	 * - See how to do so and the examples in the assignment description.
	 * 
	 * - Use getProperty() to get the reference to the Property object.
	 *  
	 *   Obtain the array of positive words, negative words, and stop words 
	 *   using the methods getPositiveTerms(), getNegativeTerms(), getStopWords() of 
	 *   the Property object.
	 * 
	 *   The scoring method is obtained by calling the method getScoringMethod() 
	 *   of the same Property object.
	 * 
	 * @param s Input string
	 * @return 0 if s is empty or 
	 *           the stop word count is equal to the number of words in the string 
	 *         Otherwise, return the sentiment score computed based on the choice of the scoring method.
	 * 
	 * @throws NullPointerException if s is null
	 *         IllegalStateException if the member variable keeping the reference to the Property object is null. 
	 *         	  
	 */

	@Override
	public double findSentiment(String s) {
		// TODO and replace return 0 with the correct return value.
		if(s == null){
			throw new NullPointerException();
		}
		if(property == null){
			throw new IllegalStateException();
		}
		
		s = s.replace('.', ' ');
		s = s.replace(',', ' ');
		s = s.replace(';', ' ');
		
		Scanner scan = new Scanner(s);
		double numOfPositives = 0;
		double numOfNegatives = 0;
		double numOfStops = 0;
		double numOfWords = 0;
		double sentimentScore = 0;
		String[] positiveTerms = property.getPositiveTerms();
		String[] negativeTerms = property.getNegativeTerms();
		String[] stopTerms = property.getStopTerms();
		ArrayList<String> words = new ArrayList<String>();
		String[] wordArray = null;
		
		while(scan.hasNext()){
			numOfWords++;
			String word = scan.next();
			words.add(word);
		}
		scan.close();
		wordArray = (String[]) words.toArray(new String[0]);
		numOfPositives = findCount(positiveTerms, wordArray);
		numOfNegatives = findCount(negativeTerms, wordArray);
		numOfStops = findCount(stopTerms, wordArray);
		if(s.equals("") || numOfWords == numOfStops)
		return 0;
		
		if(property.getScoringMethod() == 0){
			sentimentScore = numOfPositives - numOfNegatives;
		} else if(property.getScoringMethod() == 1){
			sentimentScore = (numOfPositives - numOfNegatives) / (numOfWords - numOfStops);
		}
		return sentimentScore;
	}
	
	/**
	 * Count the number of words in tokenArr that match the words in wordArr.
	 * 
	 * 
	 * @param wordArr Array of positive words, negative words, or stop words depending on what you want to count
	 * @param tokenArr Array of words 
	 * @return 0 if wordArr is empty or tokenArr is empty; 
	 * 			otherwise, return the count of words in tokenArr matching those in wordArr.
	 * @throws NullPointerException if wordArr or tokenArr is null
	 */
	public static int findCount(String[] wordArr, String[] tokenArr) {
		
		// TODO and replace return 0 with the correct return value.
		if(wordArr == null || tokenArr == null){
			throw new NullPointerException();
		} else if(wordArr.length == 0 || tokenArr.length == 0){
			return 0;
		}
		
		int counter = 0;
		for(String w : wordArr){
			for(String t : tokenArr){
				if(w.equals(t)){
					counter++;
				}
			}
		}
		return counter;
	}
	
	/**
	 * Override equals as discussed in class.
	 * Consider both name and property when comparing the objects.
	 * Your code must not break if the reference to the Property object is null. 
	 * 
	 */
	@Override
	public boolean equals(Object o) {
		// TODO and replace false with appropriate code
		if (o == null || o.getClass() != this.getClass())
			return false;
		NaiveTextAnalyzer n = (NaiveTextAnalyzer) o;
		return n.property.equals(property) && super.equals(n);
	}

	/**
	 * Set the member variable of type Property to keep the given reference to the Property object.
	 * 
	 * @throws NullPointerException if o is null;
	 * 
	 */
	@Override
	public void init(Property o) {
		// TODO
		if(o == null){
			throw new NullPointerException();
		}
		property = new Property(o);
	}
	
	/**
	 * Show text sentiment for the given sentiment score.
	 * 
	 * @param score sentiment score
	 * @return 
	 *    "Positive" if score >= the predefined distance threshold obtained by calling getMinDistance() of the Property object
	 *    "Negative" if score <= -1 * the predefined distance threshold
	 *    "Unknown" otherwise
	 *  
	 *  @throws IllegalStateException if the member variable that keeps the reference to the Property object is null. 
	 */
	@Override
	public String showSentiment(double score) {
		// TODO and replace return null with the correct return value.
		if(property == null){
			throw new IllegalStateException();
		}
		
		if(score >= property.getMinDistance()){
			return "Positive";
		}
		if(score <= -property.getMinDistance()){
			return "Negative";
		}
		return "Unknown";
		
	}
	
	/**
	 * Use the copy constructor of the Property object here.
	 * 
	 * @return  Reference to a copy of the current property object. 
	 */
	@Override
	public Property getProperty() {
		// TODO and replace return null with the correct return value.
		return new Property(property);
	}
}

