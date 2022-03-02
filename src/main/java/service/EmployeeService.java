package service;

import exceptions.SystemException;
import pojo.EmployeePojo;

public interface EmployeeService {

	// EMPLOYEE OPERATIONS
	// *****************************************************

	// LOGIN
	public EmployeePojo employeeLogin(int employeeId, String employeePassword) throws SystemException;

	// SUBMIT A REIMBURSEMENT REQUEST

	// VIEW PENDING REIMBUSEMENT REQUEST FOR LOGGED IN CUSTOMER

	// VIEW RESOLVED REIMBURSEMENT REQUESTS FOR LOGGED IN EMPLOYEE

	// READ FROM EMPLOYEE DETAILS TABLE
	public EmployeePojo fetchEmployee(int employeeId) throws SystemException;

	// UPDATE EMPLOYEE DETAILS TABLE

	// LOGOUT
}
