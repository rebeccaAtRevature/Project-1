package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
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
		try {
			ReimbursementPojo reimbursementPojo = new ReimbursementPojo(100,112,65);
			ReimbursementPojo actualResult = employeeDao.submitRequest(reimbursementPojo);
			ReimbursementPojo expectedResult = new ReimbursementPojo(100,112,65);
			when(stmt.executeUpdate("INSERT INTO pending_reimbursements( requesting_employee_id, reimbursement_amount) VALUES("+reimbursementPojo.getRequestingEmployeeId()+", "+reimbursementPojo.getReimbursementAmount()+")")).thenReturn(1);
			// make sure to comment out the ResultSet portion, we are only testing the insert functionality
			assertEquals(actualResult, expectedResult);
		} catch (SystemException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}
}
