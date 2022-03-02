package junit;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;

public class DaoTests {
	
	// Create a mock of the Statement class to avoid accessing the database
	Class<Statement> stmt = (Statement.class);
	
	
	static EmployeeDao employeeDao;
	
	
	@BeforeAll
	public static void setUp() {
		employeeDao = new EmployeeJdbcDaoImpl();
	}
	
	@Test
	public void testSubmitRequest() {
		try {
			ReimbursementPojo actualResult = employeeDao.submitRequest(new ReimbursementPojo(100,112,65));
			ReimbursementPojo expectedResult = new ReimbursementPojo(100,112,65);
			when(stmt.executeQuery(String)).thenReturn(new ReimbursementPojo(100,112,65));
		} catch (SystemException e) {
			
			e.printStackTrace();
		}
		
	}
}
