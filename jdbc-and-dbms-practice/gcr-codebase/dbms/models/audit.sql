
CREATE TABLE appointment_audit (
    audit_id INT AUTO_INCREMENT PRIMARY KEY,
    appointment_id INT,
    action_type ENUM('CREATED','UPDATED','CANCELLED'),
    action_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    remarks TEXT
);

CREATE TABLE audit_log (
    log_id INT AUTO_INCREMENT PRIMARY KEY,
    table_name VARCHAR(100),
    operation_type ENUM('INSERT','UPDATE','DELETE'),
    record_id INT,
    changed_by VARCHAR(100),
    change_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DELIMITER //

CREATE TRIGGER trg_appointments_insert
AFTER INSERT ON appointments
FOR EACH ROW
BEGIN
    INSERT INTO audit_log
    (table_name, operation_type, record_id, changed_by)
    VALUES
    ('appointments', 'INSERT', NEW.appointment_id, 'SYSTEM');
END//

DELIMITER ;

DELIMITER //




DELIMITER //

CREATE TRIGGER trg_appointments_delete
AFTER DELETE ON appointments
FOR EACH ROW
BEGIN
    INSERT INTO audit_log
    (table_name, operation_type, record_id, changed_by)
    VALUES
    ('appointments', 'DELETE', OLD.appointment_id, 'SYSTEM');
END//

DELIMITER ;



use hospital_db;
INSERT INTO appointment_audit (appointment_id, action_type, remarks)
VALUES 
(1, 'CREATED', 'Initial appointment booking'),
(2, 'CREATED', 'Initial appointment booking');

