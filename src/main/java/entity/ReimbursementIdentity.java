package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pending_reimbursements")
public class ReimbursementIdentity{

    @Id
    @Column(name = "reimbursement_id")
    private int reimbursementId;

    @Column(name = "requesting_employee_id")
    private int 

}