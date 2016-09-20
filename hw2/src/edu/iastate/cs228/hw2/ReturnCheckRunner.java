package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The runner class for the return check program.
 * 
 * Yuxiang Chen
 */
public class ReturnCheckRunner {

        /**
         * Runs the program defined in the spec.
         * 
         * Reads from employer file into a list of employer filings 
         * and reads from filer file into a list of individual filings.
         * 
         * After reading in all records, create a return check object
         * and use it to sort the employer and individual filings. 
         * Use the sorted filings to calculate and sort any discrepancies,
         * then display discrepancies and exit.
         * 
         * @param args
         *            Two expected arguments, the first being the employer
         *            file filename, and the second being the filer file
         *             filename
         */
        public static void main(String[] args) {
        	File employerFile = new File(args[0]);
        	File filerFile = new File(args[1]);
        	ArrayList<Employer> employers = new ArrayList<Employer>();
        	ArrayList<Filer> filers = new ArrayList<Filer>();
        	Scanner lineScan = null;
        	
        	try{
        		lineScan = new Scanner(employerFile);				//Scan employer file.
        		while(lineScan.hasNextLine()){
        			Scanner line = new Scanner(lineScan.nextLine());
        			line.useDelimiter(", ");
        			employers.add(new Employer(line.next(), line.nextInt(), line.next(), line.nextInt(), line.nextInt()));
        			line.close();
        		}
        		lineScan = new Scanner(filerFile);					//Scan filer file.
        		while(lineScan.hasNextLine()){
        			Scanner line = new Scanner(lineScan.nextLine());
        			line.useDelimiter(", ");
        			filers.add(new Filer(line.next(), line.nextInt(), line.nextInt()));
        			line.close();
        		}
        	} catch(FileNotFoundException e){
        		System.out.println(e);
        	}
        	
        	ReturnCheck rc = new ReturnCheck(employers, filers);
        	rc.sortEmployerRecordsByEmployeeSSN();
        	rc.sortReturnsBySSN();
        	rc.calculateDiscrepancies();
        	rc.sortDiscrepancies();
        	for(Discrepancy d : rc.getDiscrepancies()){
        		System.out.println(d);
        	}
        }
}
