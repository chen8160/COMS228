package edu.iastate.cs228.hw1;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * 
 * @author Yuxiang Chen
 * 
 * This is the class that accepts String command line arguments which are in args argument given to the main method.
 *
 * The version number of this software is 1.0.0.
 * 
 * The only method required in this class is the main method.
 * 
 * If the two arguments are not given, output the following on the console and return from this method.
 * Usage: SentimentAnalysis-v1.0.0 <input filename> <config filename>
 * 
 * When both arguments are given, assume that the user gives them in the correct input order. 
 * 
 *  args[0] is the first argument and it has the input filename.
 *  args[1] is the second argument and it has the configuration filename.
 *  
 * The method creates a Property object with the name of the configuration file.
 * It creates a NaiveAnalyzer object. Then calls the init method of this object with the Property object.
 * 
 * In the try-catch statement, it reads one line at a time from the input file.
 * Each line including an empty line has a unique ID, starting from 1.
 * 
 * For each non-empty line, the program computes the sentiment score and text using the NaiveAnalyzer object 
 * and outputs the following line on the console.
 * 
 *  	Line <lineid>: score=<sentimentscore> Sentiment=<sentiment text>
 *  
 *  	<lineid> is replaced by line numbering starting from one, incrementing by one.
 *   
 *  	<sentimentscore> is replaced by the sentiment score of the line.
 * 		<sentiment text> is either "Positive", "Negative", or "Unknown".
 *  
 *      "Positive" is output on screen when the line has more positive words than negative words 
 *      by a significant margin defined using the scoring method and the minimum distance in 
 *      the configuration file.
 *  
 *      "Negative" is output on screen when the line has more negative words than positive words 
 *      by a significant margin defined using the scoring method and the minimum distance in 
 *      the configuration file.
 *      
 *      "Unknown" is printed out when we cannot classify it as "Positive" or "Negative".
 *      
 *      See the examples given in the description of the assignment.
 *      
 *  Once all the lines in this file are processed, show the following on the console.
 *  
 *  Total score with Naive Text Sentiment Analyzer=<sum sentiment score>
 *  
 *  <sum sentiment score> is to be replaced by the sum of all the sentiment scores for each line in the file.
 *  "Naive Text Sentiment Analyzer" is obtaind using the inheritted getName() method from TextAnalyzer.
 *  
 *  See the examples given in the description of the assignment.
 *  
 *  The catch statement handles the cases when the input file is not found or problems 
 *  with openning the input file (e.g., IOException). It outputs the following on the console and returns 
 *  (exit the program).
 *  
 *  Input file <input filename> not found or there're problems with the file. Check your input file.
 *  
 *  <input filename> is replaced by the actual input filename.
 *  
 *  When testing the program, it may be easier to use an absolute path to your input file and configuration file. 
 *  If you do not use the absolute path, the program looks for the files in a default path, which depends on 
 *  your setting and how you run the program. 
 *  
 *  If you run the program from Eclipse, the default directory is the directory of the project 
 *  under your workspace directory.
 *  
 *  See how to run a Java program with arguments in IDE in the text description of the assignment.
 */

public class ScoreSentiment {
	
	// TODO
	// Define private member variables you need to use.
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length != 2){
			System.out.println("Usage: SentimentAnalysis-v1.0.0 <input filename> <config filename>");
			return;
		} 
		if(args[0] == null || args[1] == null){
			System.out.println("Usage: SentimentAnalysis-v1.0.0 <input filename> <config filename>");
			return;
		}
		
		File input = new File( args[0]);
		//File config = new File(args[1]);
		Property property = new Property(args[1]);
		NaiveTextAnalyzer analyzer = new NaiveTextAnalyzer();
		analyzer.init(property);
		double totalScore = 0;
		Scanner scan = null;
		try{
			scan = new Scanner(input);
			int lineID = 1;
			while(scan.hasNextLine()){
				String lineScan = scan.nextLine();
				if(lineScan.equals("")){
					lineID++;
					continue;
				}
				double sentimentScore = analyzer.findSentiment(lineScan);
				totalScore += sentimentScore;
				System.out.printf("Line" + lineID + ": score=%.6f Sentiment=" + analyzer.showSentiment(sentimentScore) + "\n",sentimentScore);
				lineID++;
			}
			System.out.printf("Total score with " + analyzer.getName() + "=%.8f", totalScore);
		} catch(IOException ex){
			System.out.println("Input file " + args[0] + "not found or there're problems with the file. Check your input file.");
		} finally{
			scan.close();
		}
	}	// end Main
}
