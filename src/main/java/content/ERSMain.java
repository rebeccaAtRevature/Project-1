package content;

import java.util.List;

import io.javalin.Javalin;
import pojo.EmployeePojo;
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
		
		
		
		
		// READ FROM PENDING REIMBURSEMENTS TABLE
		myServer.get("/api/p-reim/{reimid}", ctx -> {
			String reimbursementId = ctx.pathParam("reimid");
			ReimbursementPojo reimbursement = managerService.readPendingRequest(Integer.parseInt(reimbursementId));
			ctx.json(reimbursement);
		});
		
//		// APPROVE OR DENY PENDING REIMBURSEMENT REQUESTS
//		myServer.put("/api/p-reim/{reimid}/{appdeny}", ctx -> {
//			String reimbursmentId = ctx.pathParam("reimid");
//			String isApproved = ctx.pathParam 
//			ReimbursementPojo reimbursement
//		})

		// READ ALL PENDING REIMBURSEMENTS FOR ANY SINGLE EMPLOYEE
		myServer.get("/api/p-reims/{empid}", ctx -> {
			String employeeId = ctx.pathParam("empid");
			List<ReimbursementPojo> reimbursement = managerService.viewPendingRequests(Integer.parseInt(employeeId));
			ctx.json(reimbursement);
		});
		
		// View All Employees
		myServer.get("/api/Employees", ctx -> {
			List<EmployeePojo> allEmployees = managerService.viewAllEmployees();
		});
	}

}
