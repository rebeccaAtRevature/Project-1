package content;

import java.util.List;

import io.javalin.Javalin;
import pojo.EmployeePojo;
import pojo.ManagerPojo;
import pojo.ReimbursementPojo;
import service.ManagerService;
import service.ManagerServiceImpl;

public class ERSMain {

	public static void main(String[] args) {
		Javalin myServer = Javalin.create((config)->config.enableCorsForAll).start(4040);

		System.out.println("Server listening at port 4040...");

//		// This is a catch block for SystemException
//		myServer.exception(SystemException.class,(se,ctx) -> {
//			Map<String, String> error = new HashMap<String,String>();
//			error.put("message",se.getMessage());
//			error.put("datetime", LocalDateTime.now()+"");
//			ctx.json(error);
//		});

		ManagerService managerService = new ManagerServiceImpl();
		EmployeeService employeeService = new EmployeeServiceImpl();

		// ***********************************************************
		// Manager Operations
		// ***********************************************************
		// LOGIN
		myServer.get("/api/mlogin/{manid}/{pswd}", ctx -> {
			String managerId = ctx.pathParam("manid");
			String password = ctx.pathParam("pswd");
			ManagerPojo loginAttempt = managerService.managerLogin(Integer.parseInt(managerId),password);
			ctx.json(loginAttempt);
		});

		// READ FROM PENDING REIMBURSEMENTS TABLE
		myServer.get("/api/p-reim/{reimid}", ctx -> {
			String reimbursementId = ctx.pathParam("reimid");
			ReimbursementPojo reimbursement = managerService.readPendingRequest(Integer.parseInt(reimbursementId));
			ctx.json(reimbursement);
		});

		// APPROVE OR DENY PENDING REIMBURSEMENT REQUESTS
		myServer.put("/api/p-reim/{reim}", ctx -> {
			// there is an incoming book json in the request body, fetch the request body and store it in the POJO
			ReimbursementPojo reimbursmentPojo = ctx.bodyAsClass(ReimbursementPojo.class);
			// send the request to the service layer
			ReimbursementPojo reimbursement = managerService.approveOrDeny(reimbursmentPojo);
			ctx.json(reimbursement);
		});

		// READ ALL VALUES FROM PENDING REQUESTS TABLE
		myServer.get("/api/all-p-reims", ctx -> {
			List<ReimbursementPojo> reimbursement = managerService.viewAllPendingRequests();
			ctx.json(reimbursement);
		});

		// READ ALL VALUES FROM RESOLVED REQUESTS TABLE
		myServer.get("/api/all-r-reims", ctx -> {;
		List<ReimbursementPojo> allResolvedReimbursement = managerService.viewAllResolvedRequests();
		ctx.json(allResolvedReimbursement);
		});

		// READ ALL PENDING AND RESOLVED REIMBURSEMENTS FOR ANY SINGLE EMPLOYEE
		myServer.get("/api/all-reims/{empid}", ctx -> {
			String employeeId = ctx.pathParam("empid");
			List<ReimbursementPojo> allPendingReimbursement = managerService.viewAllRequests(Integer.parseInt(employeeId));
			ctx.json(allPendingReimbursement);
		});

		// VIEW ALL EMPLOYEES
		myServer.get("/api/Employees", ctx -> {
			List<EmployeePojo> allEmployees = managerService.viewAllEmployees();
			ctx.json(allEmployees);
		});

		// *****************************************************************
		// EMPLOYEE OPERATIONs
		// *****************************************************************
		// LOGIN
		myServer.get("/api/elogin/{empid}/{epswd}", ctx -> {
			System.out.println("Entered elogin lambda function");
			String employeeId = ctx.pathParam("empid");
			String password = ctx.pathParam("epswd");
			EmployeePojo loginAttempt = employeeService.employeeLogin(Integer.parseInt(employeeId),password);
			ctx.json(loginAttempt);
		});

		//GET EMPLOYEE
		myServer.get("/api/empl/{empid}", ctx ->{
			System.out.println("Entered getEmpl lambda function");
			String employeeId = ctx.pathParam("empid");
			EmployeePojo fetchEmployee = employeeService.fetchEmployee(Integer.parseInt(employeeId));
			ctx.json(fetchEmployee);
		});

		//SUBMIT REIMBURSEMENT REQUEST
		myServer.post("/api/s-reim/{reim}", ctx ->{
			System.out.println("Entered submit lambda function");
			ReimbursementPojo reimbursementPojo = ctx.bodyAsClass(ReimbursementPojo.class);
			ReimbursementPojo submitRequest = employeeService.submitRequest(ReimbursementPojo);
			ctx.json(submitRequest);
		});

		//VIEW PENDING REQUEST
		myServer.get("/api/v-p-reim/{empid}", ctx ->{
			System.out.println("Entered pendreq lambda function");
			String employeeId = ctx.pathParam("empid");
			List<ReimbursementPojo> viewPendingRequests = employeeService.viewPendingRequests(Integer.parseInt(employeeId));
			ctx.json(viewPendingRequests);
		});

		//VIEW RESOLVED REQUEST
		myServer.get("/api/v-r-reim/{empid}", ctx ->{
			System.out.println("Entered resreq lambda function");
			String employeeId = ctx.pathParam("empid");
			List<ReimbursementPojo> viewResolvedRequests = employeeService.viewResolvedRequests(Integer.parseInt(employeeId));
			ctx.json(viewResolvedRequests);
		});

		//UPDATE EMPLOYEE INFORMATION
		myServer.put("/api/u-emp/{empid}", ctx ->{
			System.out.println("Entered emplinfo lambda function");
			EmployeePojo employeePojo = ctx.bodyAsClass(EmployeePojo.class);
			EmployeePojo updateEmployee = employeeService.updateEmployee();
			ctx.json(updateEmployee);
		});
	}
}
