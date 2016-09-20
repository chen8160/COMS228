package edu.iastate.cs228.hw2;

/**
 * Keeps track of one line read from the Filer File
 * 
 * Yuxiang Chen
 */
public class Filer {
        /**
         * Create 3 protected variables, one for the filer name, one
         * for the filer SSN, and one for the reported filer income.
         */
	protected String name;
	protected int SSN;
	protected int income; 
        /**
         * Simple constructor for setting values of name, SSN, and income
         * 
         * @param name
         *            Name of individual filer
         * @param SSN
         *            SSN of individual filer
         * @param income
         *            Income reported by individual filer
         */
        public Filer(String name, int SSN, int income) {
        	this.name = name;
        	this.SSN = SSN;
        	this.income = income;
        }
        
        public Filer(Filer f){
        	this.name = f.name;
        	this.SSN = f.SSN;
        	this.income = f.income;
        }

        /**
         * Gets the filer's name
         * 
         * @return the name
         */
        public String getName() {
                return name;
        }

        /**
         * Gets the filer's SSN
         * 
         * @return the SSN
         */
        public int getSSN() {
                return SSN;
        }

        /**
         * Gets the filer's claimed income
         * 
         * @return the income
         */
        public int getIncome() {
                return income;
        }
}
