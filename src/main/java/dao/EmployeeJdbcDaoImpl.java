package dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import exceptions.SystemException;
import pojo.EmployeePojo;
import pojo.ReimbursementPojo;

public class EmployeeJdbcDaoImpl implements EmployeeDao {
	
	public static final Logger LOG = LogManager.getLogger(EmployeeJdbcDaoImpl.class);
	
	List<ReimbursementPojo> pendingRequests;
	List<ReimbursementPojo> resolvedRequests;
	
	// SUBMIT A REIMBURSEMENT REQUEST
	public ReimbursementPojo submitRequest(ReimbursementPojo reimbursementPojo) throws SystemException {
		LOG.info("Entering submitRequest() in DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		try {
			// Create a statement
			Statement stmt = conn.createStatement();
			
			// Add customer information to SQL table
			String query1 = "INSERT INTO pending_reimbursements( requesting_employee_id, reimbursement_amount) VALUES("+reimbursementPojo.getRequestingEmployeeId()+", "+reimbursementPojo.getReimbursementAmount()+")";
			int rows = stmt.executeUpdate(query1);
			System.out.println("INSERT query in submitRequest() was successful");
			// Add reimbursement ID and date ID to reimbursement POJO for use in upper layers
			String query2 = "SELECT reimbursement_id, date_of_request FROM pending_reimbursements WHERE reimbursement_id=MAX(reimbursement_id)";
			ResultSet rs = stmt.executeQuery(query2);
			System.out.println("SELECT query in submitRequest() was successful");
			if(rs.next()) {
				reimbursementPojo.setReimbursementId(rs.getInt(1));
				reimbursementPojo.setDateOfRequest(rs.getDate(2));
			}
			
		} catch (SQLException e) {
			
			throw new SystemException();
		}
		
		LOG.info("Exiting submitRequest() in DAO");
		return reimbursementPojo;
	}
	
	// VIEW PENDING REIMBUSEMENT REQUEST FOR LOGGED IN EMPLOYEE
	public List<ReimbursementPojo> viewPendingRequests(int employeeId) throws SystemException {
		LOG.info("Entering viewPendingRequests in DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		List<ReimbursementPojo> pendingRequests = new ArrayList<ReimbursementPojo>();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM pending_requests WHERE requesting_employee_id="+employeeId;
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the result set
			while(rs.next()) {
				// copy each record into a ReinbursementPojo object
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4));
				// add the POJO to the collection
				pendingRequests.add(reimbursementPojo);
			}
			
		} catch (SQLException e) {
			
			throw new SystemException();
		}
		
		
		LOG.info("Entering viewPendingRequests in DAO");
		return pendingRequests;
	}
	
	// VIEW RESOLVED REIMBURSEMENT REQUESTS FOR LOGGED IN EMPLOYEE
	public List<ReimbursementPojo> viewResolvedRequests(int employeeId) throws SystemException {
		LOG.info("Entering viewResolvedRequests in DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		List<ReimbursementPojo> resolvedRequests = new ArrayList<ReimbursementPojo>();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM resolved_requests WHERE requesting_employee_id="+employeeId;
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the result set
			while(rs.next()) {
				// copy each record into a ReinbursementPojo object
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4));
				// add the POJO to the collection
				resolvedRequests.add(reimbursementPojo);
			}
			
		} catch (SQLException e) {
			
			throw new SystemException();
		}
		
		LOG.info("Entering viewResolvedRequests in DAO");
		return resolvedRequests;
	}
	
	// READ FROM EMPLOYEE DETAILS TABLE
	public EmployeePojo fetchEmployee(int employeeId) throws SystemException {
		LOG.info("Entering fetchEmployee() in Dao");
		EmployeePojo employeePojo = null;
		// Step 2 - pass the connection from DBUtil to conn
		Connection conn = DBUtil.obtainConnection();
		
		try {
			// Create a statement
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM employee_details WHERE employee_id="+employeeId;
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next()) {
				
				employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
			
		} catch (SQLException e){
			throw new SystemException();
		}
		
		LOG.info("Exiting fetchEmployee() in Dao");
		return employeePojo;
	}
	
	// UPDATE EMPLOYEE DETAILS TABLE
	public EmployeePojo updateEmployee(EmployeePojo employeePojo) throws SystemException {
		LOG.info("Entering updateEmployee() in DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		try {
			Statement stmt = conn.createStatement();
			
			// Update all values in employee_details table
			String query = "UPDATE employee_details SET employee_first_name="+employeePojo.getEmployeeFirstName()+", employee_last_name="+employeePojo.getEmployeeLastName()+", employee_contact="+employeePojo.getEmployeeFirstName()+", employee_address="+employeePojo.getEmployeeFirstName()+", employee_password="+employeePojo.getEmployeeFirstName()+" WHERE employeeId="+employeePojo.getEmployeeId();
						
			int rows = stmt.executeUpdate(query);
			
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exiting updateEmployee() in DAO");
		return null;
	}
	
}
