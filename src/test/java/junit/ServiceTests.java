package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import exceptions.SystemException;
import pojo.EmployeePojo;
import service.EmployeeService;
import service.EmployeeServiceImpl;

public class ServiceTests {
	
	EmployeeDao employeeDao = mock(EmployeeJdbcDaoImpl.class);
	
	static EmployeeService employeeService;
	
	@BeforeAll
	public static void setUp() {
		// Instantiate Service Layer Objects
		employeeService = new EmployeeServiceImpl();
	}
	
	@Test
	public void testEmployeeLogin() {
		try {
			EmployeePojo actualResult = employeeService.employeeLogin(100,"1234");
			EmployeePojo expectedResult = new EmployeePojo(100, "Celia", "Mae",  "(546)309-3823" , "566 Weelia Ct." , "1234", "");
			when(employeeDao.fetchEmployee(100)).thenReturn(new EmployeePojo(100, "Celia", "Mae",  "(546)309-3823" , "566 Weelia Ct." , "1234", ""));
			assertEquals(actualResult,expectedResult);
			//verify(employeeDao).fetchEmployee(100);
			
		} catch (SystemException e) {
			e.printStackTrace();
		} 
		
		
	}
}
