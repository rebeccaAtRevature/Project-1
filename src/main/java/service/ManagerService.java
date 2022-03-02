package service;

import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;

public interface ManagerService {
	
	// LOGIN
	public ManagerPojo managerLogin(int managerId, String managerPassword) throws SystemException;
	
	// READ FROM MANAGER DETAILS TABLE
	public ManagerPojo fetchManager(int managerId) throws SystemException;

}
