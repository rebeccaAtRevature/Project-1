package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import dao.ManagerDao;
import dao.ManagerJdbcDaoImpl;
import exceptions.SystemException;
import pojo.ManagerPojo;

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

	public ManagerPojo fetchManager(int managerId) throws SystemException {
		// TODO Auto-generated method stub
		return null;
	}
 
}
