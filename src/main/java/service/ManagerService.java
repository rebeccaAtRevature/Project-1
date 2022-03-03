package service;

import java.util.List;

import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.ReimbursementPojo;

public interface ManagerService {

	// LOGIN
	public ManagerPojo managerLogin(int managerId, String managerPassword) throws SystemException;

	// READ FROM PENDING REIMBURSEMENTS TABLE
	ReimbursementPojo readPendingRequest(int reimbursementId) throws SystemException;
			
	// APPROVE OR DENY PENDING REIMBURSEMENT REQUESTS
	ReimbursementPojo approveOrDeny(int reimbursementId, boolean approved) throws SystemException;
	
	// READ ALL VALUES FROM PENDING REQUESTS TABLE
	List<ReimbursementPojo> viewAllPendingRequests() throws SystemException;

	// READ ALL VALUES FROM RESOLVED REQUESTS TABLE
	List<ReimbursementPojo> veiwAllResolvedRequests() throws SystemException;

	// READ ALL PENDING REIMBURSEMENTS FOR ANY SINGLE EMPLOYEE
	List<ReimbursementPojo> viewPendingRequests(int employeeId) throws SystemException;

	// VIEW ALL EMPLOYEES
	List<EmployeePojo> viewAllEmployees() throws SystemException;

}
