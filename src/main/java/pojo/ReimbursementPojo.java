package pojo;

import java.sql.Date;

public class ReimbursementPojo {
	private int reimbursementId;
	private int requestingEmployeeId;
	private double reimbursementAmount;
	private boolean requestApproved;
	private Date dateOfRequest;
	private Date dateResolved;
	
	public ReimbursementPojo() {}
	
	// Constructor for Pending Requests
	public ReimbursementPojo(int reimbusermentId, int requestingEmployeeId, double reimbursementAmount) {
			this.reimbursementId = reimbusermentId;
			this.requestingEmployeeId = requestingEmployeeId;
			this.reimbursementAmount = reimbursementAmount;
			
		}
	// Constructor for Pending Requests
	public ReimbursementPojo(int reimbusermentId, int requestingEmployeeId, double reimbursementAmount,
			 Date dateOfRequest) {
		this.reimbursementId = reimbusermentId;
		this.requestingEmployeeId = requestingEmployeeId;
		this.reimbursementAmount = reimbursementAmount;
		this.dateOfRequest = dateOfRequest;
	}

	// Constructor for Resolved Requests
	public ReimbursementPojo( int requestingEmployeeId, double reimbursementAmount,
				boolean requestApproved) {
			this.requestingEmployeeId = requestingEmployeeId;
			this.reimbursementAmount = reimbursementAmount;
			this.requestApproved = requestApproved;
		}
		
	// Constructor for Resolved Requests
	public ReimbursementPojo(int reimbursementId, int requestingEmployeeId, double reimbursementAmount,
			boolean requestApproved, Date dateResolved) {
		this.reimbursementId = reimbursementId;
		this.requestingEmployeeId = requestingEmployeeId;
		this.reimbursementAmount = reimbursementAmount;
		this.requestApproved = requestApproved;
		this.dateResolved = dateResolved;
	}

	public int getReimbursementId() {
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId) {
		this.reimbursementId = reimbursementId;
	}

	public int getRequestingEmployeeId() {
		return requestingEmployeeId;
	}

	public void setRequestingEmployeeId(int requestingEmployeeId) {
		this.requestingEmployeeId = requestingEmployeeId;
	}

	public double getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(double reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public boolean isRequestApproved() {
		return requestApproved;
	}

	public void setRequestApproved(boolean requestApproved) {
		this.requestApproved = requestApproved;
	}

	public Date getDateOfRequest() {
		return dateOfRequest;
	}

	public void setDateOfRequest(Date dateOfRequest) {
		this.dateOfRequest = dateOfRequest;
	}

	public Date getDateResolved() {
		return dateResolved;
	}

	public void setDateResolved(Date dateResolved) {
		this.dateResolved = dateResolved;
	}

}
