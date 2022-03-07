package junit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dao.EmployeeDao;
import dao.EmployeeJdbcDaoImpl;
import dao.ManagerDao;
import dao.ManagerJdbcDaoImpl;
import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import service.EmployeeService;
import service.EmployeeServiceImpl;
import service.ManagerService;
import service.ManagerServiceImpl;

public class ServiceTests {
	
	EmployeeDao employeeDao = mock(EmployeeJdbcDaoImpl.class);
	ManagerDao managerDao = mock(ManagerJdbcDaoImpl.class);
	
	static EmployeeService employeeService;
	static ManagerService managerService;
	@BeforeAll
	public static void setUp() {
		// Instantiate Service Layer Objects
		employeeService = new EmployeeServiceImpl();
		managerService = new ManagerServiceImpl();
	}
	
	@Test
	public void testEmployeeLogin() {
		try {
			EmployeePojo actualResult = employeeService.employeeLogin(100,"1234");
			EmployeePojo expectedResult = new EmployeePojo(100, "Celia", "Mae",  "(546)309-3823" , "566 Weelia Ct." , "1234", "https://static.wikia.nocookie.net/monstersincmovies/images/8/8e/Celiamonsters%2Cinc..png/revision/latest/scale-to-width-down/323?cb=20130515201302");
			when(employeeDao.fetchEmployee(100)).thenReturn(new EmployeePojo(100, "Celia", "Mae",  "(546)309-3823" , "566 Weelia Ct." , "1234", "https://static.wikia.nocookie.net/monstersincmovies/images/8/8e/Celiamonsters%2Cinc..png/revision/latest/scale-to-width-down/323?cb=20130515201302"));
			assertEquals(actualResult,expectedResult);
			//verify(employeeDao).fetchEmployee(100);
			
		} catch (SystemException e) {
			e.printStackTrace();
		} 
		
		
	}
	
	@Test
	public void testManagerLogin() {
		try {
			ManagerPojo actualResult = managerService.managerLogin(100,"1234");
			ManagerPojo expectedResult = new ManagerPojo(100, "Roz", "Slug",  "(546)354-3218" , "168 Watching Ct." , "2468", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Monsters%2C_Inc._Mike_%26_Sulley_to_the_Rescue%21_02.jpg/800px-Monsters%2C_Inc._Mike_%26_Sulley_to_the_Rescue%21_02.jpg?20220222130835");
			when(managerDao.fetchManager(100)).thenReturn(new ManagerPojo(100, "Roz", "Slug",  "(546)354-3218" , "168 Watching Ct." , "2468", "https://upload.wikimedia.org/wikipedia/commons/thumb/b/bb/Monsters%2C_Inc._Mike_%26_Sulley_to_the_Rescue%21_02.jpg/800px-Monsters%2C_Inc._Mike_%26_Sulley_to_the_Rescue%21_02.jpg?20220222130835"));
			assertEquals(actualResult,expectedResult);
			//verify(employeeDao).fetchEmployee(100);
			
		} catch (SystemException e) {
			e.printStackTrace();
		} 
		
		
	}
}
