package edu.iastate.cs228.hw2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Checks for discrepancies between employer filings and individual filings.
 * 
 * Yuxiang Chen
 */
public class ReturnCheck {
	/**
	 * Define private ArrayList variables for employers, filers, and
	 * discrepancies
	 */
	private ArrayList<Employer> employers;
	private ArrayList<Filer> filers;
	private ArrayList<Discrepancy> discrepancies;

	/**
	 * Simple constructor setting the employers and filers ArrayLists
	 * 
	 * @param employers
	 * @param filers
	 */
	public ReturnCheck(ArrayList<Employer> employers, ArrayList<Filer> filers) {
		this.employers = employers;
		this.filers = filers;
		discrepancies = new ArrayList<Discrepancy>();
	}

	/**
	 * Gets the employers ArrayList
	 * 
	 * @return the list of employers filings.
	 */
	public ArrayList<Employer> getEmployers() {
		return employers;
	}

	/**
	 * Gets the filers ArrayList
	 * 
	 * @return the list of individual filers filings.
	 */
	public ArrayList<Filer> getFilers() {
		return filers;
	}

	/**
	 * Gets the discrepancies ArrayList. Must have called calculateDiscrepancies
	 * first.
	 * 
	 * @return the list of discrepancies between employers and filers
	 */
	public ArrayList<Discrepancy> getDiscrepancies() {
		return discrepancies;
	}

	/**
	 * Sets the discrepancies list
	 * 
	 * @param discrepancies
	 *            the new discrepancies list.
	 */
	public void setDiscrepancies(ArrayList<Discrepancy> discrepancies) {
		this.discrepancies = discrepancies;
	}

	/**
	 * Sorts the employers list by employeeSSN using insertion sort
	 */
	public void sortEmployerRecordsByEmployeeSSN() {
		int n = employers.size();
		for(int i = 1; i < n; i++){
			Employer temp = employers.get(i);
			int j = i - 1;
			employers.remove(i);
			while(j > -1 && employers.get(j).employeeSSN > temp.employeeSSN){
				--j;
			}
			employers.add(j + 1, temp);
		}
	}

	/**
	 * Sorts the filers list by SSN using merge sort
	 */
	public void sortReturnsBySSN() {
		filers = mergeSort(filers);
	}

	/**
	 * Sorts the discrepancies list using Java's built-in sorting options.
	 * Assumes calculatedDiscrepancies has been called.
	 * 
	 * The sort order for discrepancies is defined as follows: 1. All records
	 * with an alert should be displayed before any record without. 2. When
	 * alert statuses are equal, larger absolute value of wage discrepancy are
	 * listed ahead of smaller. 3. When all of the above are equal,
	 * underpayments should appear before over-payments. 4. When all of the
	 * above are equal, filer's names as reported in their return should be
	 * listed alphabetically 5. When all of the above are equal, numerically
	 * smaller SSNs should appear before larger.
	 */
	public void sortDiscrepancies() {
		Collections.sort(discrepancies);
	}

	/**
	 * Calculates the discrepancies between employer filings and employee
	 * filings.
	 */
	public void calculateDiscrepancies() {
		for (int i = 0; i < filers.size(); i++) {
			int wageDiscrepancy = filers.get(i).getIncome();
			String filerAlias = "";
			Boolean alert = false;
			for (Employer employer : employers) {
				if (employer.sameEmployee(filers.get(i))) {
					wageDiscrepancy -= employer.getEmployeeWages();
					if (!filers.get(i).getName().equals(employer.getEmployeeName())) {
						String addName = employer.getEmployeeName();
						Scanner scan = new Scanner(filerAlias);
						scan.useDelimiter(", ");
						if(scan.hasNext()){
							while(scan.hasNext()){
								if(scan.next().equals(addName)){
									addName = "";
									break;
								} else{
									addName = ", " + addName;
								}
							}
						}
						scan.close();
						filerAlias += addName;
					}
				}
			}
			if(wageDiscrepancy == 0)
				continue;
			alert = surnameChecker(filers.get(i).getName(), filerAlias) && wageDiscrepancy < 0;
			discrepancies.add(new Discrepancy(alert, wageDiscrepancy,
					filers.get(i).getName(), filers.get(i).getSSN(),
					filerAlias));
		}
	}

	/**
	 * Creates the output string for each discrepancy, with a newline after each
	 * discrepancy. The output string is formatted as follows: <alert><wage
	 * discrepancy>,_<filer name>,_<filer SSN>(,_<filer alias>)\n Where: '\n' is
	 * a newline character ',' is a literal comma, '_' is a single space
	 * character, <alert> is an asterisk ('*') if the filer is suspected of
	 * fraud, <wage discrepancy> is the non-zero difference between the sum of
	 * the wages reported by all employers for this filer's SSN and the income
	 * reported by filer in his or her return, including a prefixed sign,
	 * whether positive or negative <filer name> is the filer's name as reported
	 * in the filer's return, <filer SSN> is the filer's social security number,
	 * and (,_<filer alias>)* is a comma-and-space delineated list of zero or
	 * more employer- reported names associated with the filer's SSN that do not
	 * exactly match the name reported in the filer's return, with ',' and '_'
	 * defined as between the required fields.
	 * 
	 * @return The concatenated strings of each discrepancy, in order.
	 */
	public String writeDiscrepanciesToString() {
		String output = "";
		for(Discrepancy d : discrepancies){
			output += (d.toString() + "\n");
		}
		return output;
	}

	/**
	 * Uses a binary search to find all employer records for the SSN provided in
	 * the Filer argument.
	 * 
	 * @param filer
	 *            Used to provied an SSN to search for in the employer records.
	 * @return A list of employer filings that contain the same SSN as the
	 *         filer.
	 */
	public ArrayList<Employer> findEmployerRecords(Filer filer) {
		// TODO
		sortEmployerRecordsByEmployeeSSN();
		ArrayList<Employer> copyOfSortedEmployers = new ArrayList<Employer>();
		for(Employer e : employers){
			copyOfSortedEmployers.add(new Employer(e));
		}
		ArrayList<Employer> returnList = new ArrayList<Employer>();
		boolean status = true;
		while(status){
			status = binarySearch(copyOfSortedEmployers, filer, returnList);
		}
		return returnList;
	}
	
	/**
	 * Binary search method.
	 * @param employers
	 * The list of employers in which we want to find the target.
	 * @param target
	 * The target employer which is wanted
	 * @param returnList
	 * The list contains all the target employers
	 * @return
	 * true if found target, false otherwise.
	 */
	private boolean binarySearch(ArrayList<Employer> employers, Filer target, ArrayList<Employer> returnList){
		int n = employers.size();
		int left = 0;
		int right = n - 1;
		while (left <= right){
			int mid = (left + right) / 2;
			if(employers.get(mid).getEmployeeSSN() == target.getSSN()){
				returnList.add(employers.get(mid));
				employers.remove(mid);
			}
			if(employers.get(mid).getEmployeeSSN() > target.getSSN()){
				right = mid - 1;
			} else{
				left = mid + 1;
			}
		}
		return false;
	}
	
	/**
	 * Merge Sort of filers
	 * @param filerList
	 * The filers field.
	 */
	private ArrayList<Filer> mergeSort(ArrayList<Filer> filerList){
		int n = filerList.size();
		if(n <= 1)
			return filerList;
		ArrayList<Filer> left = new ArrayList<Filer>();
		ArrayList<Filer> right = new ArrayList<Filer>();
		int mid = n / 2;
		for(int i = 0; i < mid; i++){
			left.add(new Filer(filerList.get(i)));
		}
		for(int i = mid; i < n; i++){
			right.add(new Filer(filerList.get(i)));
		}
		left = mergeSort(left);
		right = mergeSort(right);
		return merge(left, right);
	}
	
	/**
	 * Merge two array together
	 * @param left
	 * Left half of the array which is being sorted.
	 * @param right
	 * The right half of the array which is being sorted.
	 * @return
	 * The sorted array
	 */
	private ArrayList<Filer> merge(ArrayList<Filer> left, ArrayList<Filer> right){
		int p = left.size();
		int q = right.size();
		ArrayList<Filer> returnList = new ArrayList<Filer>();
		int i = 0;
		int j = 0;
		while(i < p && j < q){
			if(left.get(i).SSN < right.get(j).SSN){
				returnList.add(left.get(i));
				i++;
			} else{
				returnList.add(right.get(j));
				j++;
			}
		}
		if(i >= p){
			while(j < q){
				returnList.add(right.get(j));
				j++;
			}
		} else{
			while(i < p){
				returnList.add(left.get(i));
				i++;
			}
		}
		return returnList;
	}
	
	/**
	 * Check if the surname filer's reported is the same with the filer's Aliases.
	 * @param filerName
	 * The name which the filer reported
	 * @param filerAlias
	 * The aliases of the filer
	 * @return
	 * true if any of the surnames in filerAlias is not the same as the surname in filerName.
	 * Otherwise, false.
	 */
	private boolean surnameChecker(String filerName, String filerAlias){
		if(filerAlias.equals(""))
			return false;
		Scanner scanFilerAliases = new Scanner(filerAlias);
		scanFilerAliases.useDelimiter(", ");
		while(scanFilerAliases.hasNext()){
			String nextAlias = scanFilerAliases.next();
			Scanner scanName = new Scanner(nextAlias);
			String surnameOfAlias = scanName.next();
			scanName.close();
			scanName = new Scanner(filerName);
			String surnameOfFiler = scanName.next();
			scanName.close();
			if(!surnameOfAlias.equals(surnameOfFiler)){
				scanFilerAliases.close();
				return true;
			}
		}
		scanFilerAliases.close();
		return false;
	}
}
