package edu.iastate.cs228.hw2;

/**
 * Keeps track of one employer filing for the IRS tax records.
 * 
 * Yuxiang Chen
 */

public class Employer {
	/**
	 * Create protected variables for employer name, employer ID, employee name,
	 * employee SSN, and employee wages as reported by the employer.
	 */
	protected String name;
	protected int ID;
	protected String employeeName;
	protected int employeeSSN;
	protected int employeeWages;

	/**
	 * Simple constructor for setting value of protected variables
	 * 
	 * @param name
	 *            Employer name
	 * @param ID
	 *            Employer ID
	 * @param employeeName
	 *            name of employee
	 * @param employeeSSN
	 *            SSN of employee
	 * @param employeeWages
	 *            wages earned by employee through this employer
	 */
	public Employer(String name, int ID, String employeeName, int employeeSSN,
			int employeeWages) {
		this.name = name;
		this.ID = ID;
		this.employeeName = employeeName;
		this.employeeSSN = employeeSSN;
		this.employeeWages = employeeWages;
	}
	
	/**
	 * Copy constructor
	 * @param other
	 * Other Employer object which is copied
	 */
	public Employer(Employer other){
		this.name = other.name;
		this.ID = other.ID;
		this.employeeName = other.employeeName;
		this.employeeSSN = other.employeeSSN;
		this.employeeWages = other.employeeWages;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass())
			return false;
		Employer e = (Employer) obj;
		return name.equals(e.name) && ID == e.ID
				&& employeeName.equals(e.employeeName)
				&& employeeSSN == e.employeeSSN
				&& employeeWages == e.employeeWages;
	}

	/**
	 * Compares this against another Employer object to see if the employers are
	 * the same in each.
	 * 
	 * @param employer
	 *            The employer to compare against.
	 * @return true if and only if the ID fields of this and employer are equal.
	 */
	public boolean sameEmployer(Employer employer) {
		if(employer == null)
			return false;
		return ID == employer.ID;
	}

	/**
	 * Compares this against an Employee object to see if the employees are the
	 * same in each.
	 * 
	 * @param employee
	 *            The employee to compare against.
	 * @return true if and only if the SSN fields of this and employer are
	 *         equal.
	 */
	public boolean sameEmployee(Filer employee) {
		if(employee == null)
			return false;
		return employeeSSN == employee.getSSN();
	}

	/**
	 * Gets the employer name.
	 * 
	 * @return The employer name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Gets the employer ID
	 * 
	 * @return The employer ID
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Gets the employee name
	 * 
	 * @return The employee name
	 */
	public String getEmployeeName() {
		return employeeName;
	}

	/**
	 * Gets the employee SSN
	 * 
	 * @return the employee SSN
	 */
	public int getEmployeeSSN() {
		return employeeSSN;
	}

	/**
	 * Gets the employee wages claimed by the employer
	 * 
	 * @return the employee wages
	 */
	public int getEmployeeWages() {
		return employeeWages;
	}
}
