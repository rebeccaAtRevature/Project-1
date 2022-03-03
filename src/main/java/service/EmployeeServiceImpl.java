package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;

public class EmployeeServiceImpl implements EmployeeService{
	
	public static final Logger LOG = LogManager.getLogger(EmployeeServiceImpl.class);
	
	EmployeeDao employeeDao;

	public EmployeeServiceImpl() {
		employeeDao = new EmployeeJdbcDaoImpl();
	}

	public EmployeePojo employeeLogin(int employeeId, String employeePassword) throws SystemException {
		LOG.info("Entering employeeLogin() in Service Layer");
		
		EmployeePojo employeePojo = null;
		EmployeePojo loginAttempt = employeeDao.fetchEmployee(employeeId);
		if (loginAttempt.getPassword().equals(employeePassword)) {
			employeePojo = loginAttempt;
		}
		
		LOG.info("Exiting employeeLogin() in Service Layer");
		return employeePojo;
	}

	public EmployeePojo fetchEmployee(int employeeId) throws SystemException {
		return employeeDao.fetchEmployee(employeeId);
	}

	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws SystemException {
		return employeeDao.submitRequest(reimbursementPojo);
	}

	public List<ReimbursementPojo> viewPendingRequests(int employeeId) throws SystemException {
		return employeeDao.viewPendingRequests(employeeId);
	}

	public List<ReimbursementPojo> viewResolvedRequests(int employeeId) throws SystemException {
		return employeeDao.viewResolvedRequests(employeeId);
	}

	public EmployeePojo updateEmployee(EmployeePojo employeePojo) throws SystemException {
		return employeeDao.updateEmployee(employeePojo);
	}
	
	
}
