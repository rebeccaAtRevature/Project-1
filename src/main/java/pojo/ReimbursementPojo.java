package pojo;

import java.sql.Date;

public class ReimbursementPojo {
	private int reimbusermentId;
	private int requestingEmployeeId;
	private double reimbursementAmount;
	private boolean requestApproved;
	private Date dateOfRequest;
	private Date dateResolved;
	
	public ReimbursementPojo() {}
	
	// Constructor for Pending Requests
		public ReimbursementPojo(int reimbusermentId, int requestingEmployeeId, double reimbursementAmount) {
			this.reimbusermentId = reimbusermentId;
			this.requestingEmployeeId = requestingEmployeeId;
			this.reimbursementAmount = reimbursementAmount;
			
		}
	// Constructor for Pending Requests
	public ReimbursementPojo(int reimbusermentId, int requestingEmployeeId, double reimbursementAmount,
			 Date dateOfRequest) {
		this.reimbusermentId = reimbusermentId;
		this.requestingEmployeeId = requestingEmployeeId;
		this.reimbursementAmount = reimbursementAmount;
		this.dateOfRequest = dateOfRequest;
	}

	// Constructor for Resolved Requests
	public ReimbursementPojo(int reimbusermentId, int requestingEmployeeId, double reimbursementAmount,
			boolean requestApproved, Date dateResolved) {
		this.reimbusermentId = reimbusermentId;
		this.requestingEmployeeId = requestingEmployeeId;
		this.reimbursementAmount = reimbursementAmount;
		this.requestApproved = requestApproved;
		this.dateResolved = dateResolved;
	}



	public int getReimbusermentId() {
		return reimbusermentId;
	}

	public void setReimbusermentId(int reimbusermentId) {
		this.reimbusermentId = reimbusermentId;
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
