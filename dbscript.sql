CREATE DATABASE ers;

CREATE TABLE employee_details(employee_id INT GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1), 
                            employee_first_name VARCHAR(20),
                            employee_last_name VARCHAR(20), 
                            employee_phone_number VARCHAR(20), 
                            employee_address VARCHAR(200), 
                            employee_password VARCHAR(20),
                            employee_image_url VARCHAR(200),
                            PRIMARY KEY(employee_id));
                                
CREATE TABLE manager_details(manager_id INT GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1), 
                            manager_first_name VARCHAR(20),
                            manager_last_name VARCHAR(20), 
                            manager_phone_number VARCHAR(20), 
                            manager_address VARCHAR(200), 
                            manager_password VARCHAR(20),
                            manager_image_url VARCHAR(200),
                            PRIMARY KEY(manager_id));

CREATE TABLE reimbursements_details(reimbursement_id INT GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1), 
                            requesting_employee_id INT,
                            reimbursement_amount NUMERIC(10,2), 
                            reimbursement_status VARCHAR(20),
                            date_of_request DATE NOT NULL DEFAULT CURRENT_DATE,
                            PRIMARY KEY(reimbursement_id));

CREATE TABLE resolved_reimbursements(resolved_reimbursement_id INT GENERATED ALWAYS AS IDENTITY (START WITH 100 INCREMENT BY 1), 
                            reimbursement_id INT
                            request_approved BOOLEAN, 
                            date_resolved DATE NOT NULL DEFAULT CURRENT_DATE,
                            PRIMARY KEY(reimbursement_id));

ALTER TABLE pending_reimbursements ADD 
   CONSTRAINT fk_requesting_employee_id 
      FOREIGN KEY (requesting_employee_id)
      REFERENCES employee_details (employee_id)
      ON DELETE CASCADE;

ALTER TABLE resolved_reimbursements ADD 
   CONSTRAINT fk_reimbursement_id 
      FOREIGN KEY (reimbursement_id)
      REFERENCES reimbursement_details (reimbursement_id)
      ON DELETE CASCADE;