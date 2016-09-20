package edu.iastate.cs228.hw2;

/**
 * Stores information about discrepancies between employer and individual
 * filings.
 * 
 * Yuxiang Chen
 */
public class Discrepancy implements Comparable<Discrepancy> {
        /**
         * Create protected variables for keeping track of alert state,
         * wage discrepancy, filer name, filer SSN, and filer aliases.
         */
	protected boolean alert;
	protected int wageDiscrepancy;
	protected String filerName;
	protected int filerSSN;
	protected String filerAlias;
        /**
         * A simple constructor for setting the values of the discrepancy
         * 
         * @param alert
         *            Alert state of discrepancy
         * @param wageDiscrepancy
         *            Calculated wage difference between the employers and
         *            individual filings
         * @param filerName
         *            the filer's name, as reported by the individual
         * @param filerSSN
         *            the filer's SSN
         * @param filerAlias
         *            A comma-and-space separated string of names used by
         *            employers when filing for this SSN. No name should
         *            be repeated.
         */
        public Discrepancy(boolean alert, int wageDiscrepancy,
                           String filerName, int filerSSN,
                           String filerAlias) {
        	this.alert = alert;
        	this.wageDiscrepancy = wageDiscrepancy;
        	this.filerName = filerName;
        	this.filerSSN = filerSSN;
        	this.filerAlias = filerAlias;
        }

        @Override
        /**
         * Used to compare two discrepancies.
         * Returns int < 0 if this < discrepancy
         * Returns int > 0 if this > discrepancy
         * Returns int = 0 if this == discrepancy
         * 
         * Rules for comparison:
         *     1. All Discrepancies with an alert should be listed before
         *         any discrepancy without.
         *     2. When alert statuses are equal, larger absolute value of
         *        wage discrepancy is listed before a smaller value.
         *     3. When all of the above are equal, underpayments should
         *        be listed ahead of overpayments.
         *     4. When all of the above are equal, filers' names as
         *         reported by the individual should be listed alphabetically
         *     5. When all of the above are equal, numerically smaller
         *        SSNs should appear before larger.
         * 
         * When a "appears before" b, it means that a is considered
         * "less than" b, or a < b. So if this has an alert, but 
         * discrepancy does not, a negative number should be returned,
         * because this < discrepancy.
         */
        public int compareTo(Discrepancy discrepancy) {
        	if(this.alert == true && discrepancy.alert == false){
        		return -1;
        	} else if(this.alert == false && discrepancy.alert == true) {
        		return 1;
        	}
        	if(Math.abs(this.wageDiscrepancy) != Math.abs(discrepancy.wageDiscrepancy)){
        		return Math.abs(discrepancy.wageDiscrepancy) - Math.abs(this.wageDiscrepancy);
        	} else if(Math.abs(discrepancy.wageDiscrepancy) != discrepancy.wageDiscrepancy && Math.abs(this.wageDiscrepancy) == this.wageDiscrepancy){
        		return 1;
        	} else if(Math.abs(discrepancy.wageDiscrepancy) == discrepancy.wageDiscrepancy && Math.abs(this.wageDiscrepancy) != this.wageDiscrepancy){
        		return -1;
        	}
        	if(this.filerName.compareTo(discrepancy.filerName) != 0){
        		return this.filerName.compareTo(discrepancy.filerName);
        	} 
                return this.filerSSN - discrepancy.filerSSN;
        }

        
        @Override
        /**
         * Computes the output string for the current discrepancy.
         * From the spec, the format of the output should be:
         * 		<alert><wage discrepancy>,_<filer name>,_<filer SSN>(,_<filer alias>)*
         * where fields may contain whitespace only if that whitespace was part of the
         * associated input, no field may contain a comma, ',' is a literal comman,
         * '_' is a single space character, and:
         * 		-<alert> is an asterisk ('*') if the filer has underpaid and is
         * 				 suspected of fraud, or a single space otherwise
         * 		-<wage discrepancy> is the non-zero difference between the sum of the
         * 				 wages reported by all employers for this filer's SSN and the
         * 				 wages reported by the filer on his or her return, including a
         * 				 prefixed sign, whether positive or negative;
         * 		-<filer name> is the filer's name as reported in the filer's return
         * 		-<filer SSN> is the filer's social security number
         * 		-(,_<filer alias>)* is a comma-and-space delineated list of zero or
         * 				 more employer reported names associated with the filer's SSN
         * 				 that do not exactly match the name reported in the filer's return,
         * 				 with ',' and '_' defined as between the required fields.
         */
        public String toString() {
        	String alertMark = " ";
        	String otherNames = "";
        	String wageDiscrepancySign = "";
        	if(alert)
        		alertMark = "*";
        	if(filerAlias != null && filerAlias != "")
        		otherNames = ", " + filerAlias;
        	if(Math.abs(wageDiscrepancy) == wageDiscrepancy)
        		wageDiscrepancySign = "+";
        	return alertMark + wageDiscrepancySign + wageDiscrepancy + ", " + filerName + ", " + filerSSN + otherNames;
        }
}
