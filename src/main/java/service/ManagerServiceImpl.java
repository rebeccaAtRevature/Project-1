package service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ManagerDao;
import dao.ManagerJdbcDaoImpl;
import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.ReimbursementPojo;

public class ManagerServiceImpl implements ManagerService{
	
	public static final Logger LOG = LogManager.getLogger(ManagerServiceImpl.class);
	
	ManagerDao managerDao = new ManagerJdbcDaoImpl();
	
	public ManagerPojo managerLogin(int managerId, String managerPassword) throws SystemException {
		LOG.info("Entering managerLogin() in Service Layer");
		
		ManagerPojo managerPojo = null;
		ManagerPojo loginAttempt = managerDao.fetchManager(managerId);
		if (loginAttempt.getManagerPassword().equals(managerPassword)) {
			managerPojo = loginAttempt;
		}
		
		LOG.info("Exiting managerLogin() in Service Layer");
		return managerPojo;
	}
	
	public ReimbursementPojo readPendingRequest(int reimbursementId) throws SystemException {
		return managerDao.readPendingRequest(reimbursementId);
	}
	
	public ReimbursementPojo approveOrDeny(int reimbursementId, boolean approved) throws SystemException {
		return managerDao.approveOrDeny(reimbursementId, approved);
	}

	public List<ReimbursementPojo> viewAllPendingRequests() throws SystemException {
		return managerDao.viewAllPendingRequests();
	}

	public List<ReimbursementPojo> veiwAllResolvedRequests() throws SystemException {
		return managerDao.veiwAllResolvedRequests();
	}

	public List<ReimbursementPojo> viewPendingRequests(int employeeId) throws SystemException {
		return managerDao.viewPendingRequests(employeeId);
	}

	public List<EmployeePojo> viewAllEmployees() throws SystemException {
		return managerDao.viewAllEmployees();
	}


 
}
