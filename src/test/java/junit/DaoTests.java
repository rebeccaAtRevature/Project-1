package junit;

import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import exceptions.SystemException;
import pojo.ReimbursementPojo;

public class DaoTests {
	
	// Create a mock of the Statement class to avoid accessing the database
	Statement stmt = mock(Statement.class);
	
	
	static EmployeeDao employeeDao;
	
	
	@BeforeAll
	public static void setUp() {
		employeeDao = new EmployeeJdbcDaoImpl();
	}
	
	@Test
	public void testSubmitRequest() {
		ReimbursementPojo argument = new ReimbursementPojo(100,112,65);
		try {
			ReimbursementPojo actualResult = employeeDao.submitRequest(argument);
			ReimbursementPojo expectedResult = new ReimbursementPojo(100,112,65);
			
		} catch (SystemException e) {
			
			e.printStackTrace();
		}
		
	}
}
