package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import exceptions.SystemException;
import pojo.EmployeePojo;

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
	
	
}
