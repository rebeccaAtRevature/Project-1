package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.ManagerDao;
import dao.ManagerJdbcDaoImpl;
import exceptions.SystemException;
import pojo.ReimbursementPojo;
import service.ManagerService;
import service.ManagerServiceImpl;

public class ManagerServiceTests {
	
	// add mocking for Dao methods
	ManagerDao managerDao = mock(ManagerJdbcDaoImpl.class);
	
	// Declare Service variables
	static ManagerService managerService = null;
	
	@BeforeAll
	public static void setUp() {
		managerService = new ManagerServiceImpl();
	}
	
	@Test
	public void testReadPendingReimbursement() {
		try {
			ReimbursementPojo expectedResult = managerService.readPendingRequest(125);
			ReimbursementPojo actualResult = new ReimbursementPojo(125,100,35,true);
			when(managerDao.readPendingRequest(125)).thenReturn(new ReimbursementPojo(125,100,35,true));
			assertEquals(expectedResult, actualResult);
		} catch (SystemException e) {
			
		}
		
	}
}
