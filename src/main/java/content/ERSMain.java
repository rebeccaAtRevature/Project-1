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
		Javalin myServer = Javalin.create().start(4040);
		
		System.out.println("Server listening at port 4040...");
		
		ManagerService managerService = new ManagerServiceImpl();
		
		// ***********************************************************
		// Manager Operations
		// ***********************************************************
		// LOGIN
		myServer.get("/api/login/{manid}/{pswd}", ctx -> {
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
	}
	
}
