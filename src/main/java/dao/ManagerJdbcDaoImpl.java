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
import pojo.ManagerPojo;
import pojo.ReimbursementPojo;
import service.EmployeeServiceImpl;

public class ManagerJdbcDaoImpl implements ManagerDao {
	
	public static final Logger LOG = LogManager.getLogger(EmployeeServiceImpl.class);

	// Add collections here for any methods that return a list
	
	public ManagerPojo fetchManager(int managerId) throws SystemException {
		
		LOG.info("Entering fetchManager in DAO");
		ManagerPojo managerPojo = null;
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			String query = "SELECT * FROM manager_details WHERE manager_id="+managerId;
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				
				managerPojo = new ManagerPojo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7));
			}
		} catch (SQLException e) {
			throw new SystemException();
		}
		
		LOG.info("Exting fetchManager in DAO");
		
		return managerPojo;
	}

	public ReimbursementPojo deletePendingRequest(int reimbursementId) throws SystemException {
		LOG.info("Entered deletePendingRequest() in DAO");
		ReimbursementPojo reimbursementPojo = null;
		Connection conn = DBUtil.obtainConnection();
		try {
			Statement stmt = conn.createStatement();
			reimbursementPojo = readPendingRequest(reimbursementId);
			String query = "DELETE FROM pending_requests WHERE reimbursement_id = " + reimbursementId;
			int rows = stmt.executeUpdate(query);
		} catch (SQLException e) {
			throw new SystemException();
		}
		LOG.info("Exited deletePendingRequest() in DAO");
		return reimbursementPojo;
	}
	
	public ReimbursementPojo readPendingRequest(int reimbursementId) throws SystemException {
		
		LOG.info("Entering readPendingRequests in Manager DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		ReimbursementPojo pendingRequests = null;
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM pending_reimbursements WHERE reimbursement_id="+reimbursementId;
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				 pendingRequests = new ReimbursementPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4));
			}
			
		} catch (SQLException e) {
			throw new SystemException();
		}
		
		
		LOG.info("Exiting readPendingRequests in Manager DAO");
		
		return pendingRequests;
	}


	public ReimbursementPojo addResolvedRequest(ReimbursementPojo reimbursementPojo) throws SystemException {
		
		LOG.info("Entering addResolvedRequests in Manager DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		ReimbursementPojo resolvedRequest = null;
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "INSERT INTO resolved_reimbursements(requesting_employee_id, reimbursement_amount, request_approved, date_resolved) VALUES(" + 
			reimbursementPojo.getRequestingEmployeeId() + "," + reimbursementPojo.getReimbursementAmount() + "," + reimbursementPojo.isRequestApproved() + "," 
					+ reimbursementPojo.getDateResolved() + ")";
			int rows = stmt.executeUpdate(query);
			System.out.println("INSERT query in addResolvedRequest() was successful");
			String query2 = "SELECT reimbursement_id, date_resolved FROM resolved_reimbursements WHERE reimbursement_id=MAX(reimbursement_id)";
			ResultSet rs = stmt.executeQuery(query2);
			System.out.println("SELECT query in addResolvedRequest() was successful");
			if(rs.next()) {
				reimbursementPojo.setReimbursementId(rs.getInt(1));
				reimbursementPojo.setDateResolved(rs.getDate(2));
			}
			
		} catch (SQLException e) {
			throw new SystemException();
		}
		
		
		LOG.info("Exiting addResolvedRequests in Manager DAO");
		
		return resolvedRequest;
	}
		

	public ReimbursementPojo approveOrDeny(ReimbursementPojo reimbursementPojo) throws SystemException {
		// Step 2 - pass the connection from DBUtil to conn
		Connection conn = DBUtil.obtainConnection();
		ReimbursementPojo returnPojo = null;
		try {
			conn.setAutoCommit(false);
			reimbursementPojo = deletePendingRequest(reimbursementPojo.getReimbursementId());
			
			if(reimbursementPojo.isRequestApproved()) {
				reimbursementPojo.setRequestApproved(true);
			} else {
				reimbursementPojo.setRequestApproved(false);
			}
			
			addResolvedRequest(new ReimbursementPojo(reimbursementPojo.getRequestingEmployeeId(), reimbursementPojo.getReimbursementAmount(), reimbursementPojo.isRequestApproved()));
			conn.commit();
		} catch (SQLException e) {
			throw new SystemException();
		}
		
		return reimbursementPojo;
	}

	public List<ReimbursementPojo> viewAllPendingRequests() throws SystemException {
		
		LOG.info("Entering viewAllPendingRequests in Manager DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		List<ReimbursementPojo> pendingRequests = new ArrayList<ReimbursementPojo>();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM pending_reimbursements";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4));
				pendingRequests.add(reimbursementPojo);
			}
			
		} catch (SQLException e) {
			throw new SystemException();
		}
		
		
		LOG.info("Exiting viewAllPendingRequests in Manager DAO");
		
		return pendingRequests;
	}
	

	public List<ReimbursementPojo> viewAllResolvedRequests() throws SystemException {
		
		LOG.info("Entering viewResolvedRequests in Manager DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		List<ReimbursementPojo> resolvedRequest = new ArrayList<ReimbursementPojo>();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM resolved_reimbursement";
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getBoolean(4), rs.getDate(5));
				resolvedRequest.add(reimbursementPojo);
			}
			
		} catch (SQLException e) {
			throw new SystemException();
		}
		
		
		LOG.info("Exiting viewResolvingRequests in Manager DAO");
		
		return resolvedRequest;
	}
	
	
	public List<ReimbursementPojo> viewPendingRequests(int employeeId) throws SystemException {
		LOG.info("Entering viewPendingRequests in Manager DAO");
		
		Connection conn = DBUtil.obtainConnection();
		
		List<ReimbursementPojo> pendingRequests = new ArrayList<ReimbursementPojo>();
		
		try {
			Statement stmt = conn.createStatement();
			
			String query = "SELECT * FROM pending_requests WHERE requesting_employee_id="+employeeId;
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()) {
				ReimbursementPojo reimbursementPojo = new ReimbursementPojo(rs.getInt(1), rs.getInt(2), rs.getDouble(3), rs.getDate(4));
				pendingRequests.add(reimbursementPojo);
			}
			
		} catch (SQLException e) {
			throw new SystemException();
		}
		
		
		LOG.info("Exiting viewPendingRequests in Manager DAO");
		
		return pendingRequests;
	}
	
	public List<EmployeePojo> viewAllEmployees() throws SystemException {

		List<EmployeePojo> allEmployees = new ArrayList<EmployeePojo>();

		Connection conn = DBUtil.obtainConnection();

		try {
			Statement stmt = conn.createStatement();

			String query = "SELECT * FROM employee_details";

			ResultSet rs = stmt.executeQuery(query);


			while (rs.next()) {

				EmployeePojo employeePojo = new EmployeePojo(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getNString(7));

				allEmployees.add(employeePojo);
			}

		} catch (SQLException e) {
			throw new SystemException();
		}

		return allEmployees;
	}
		
}
