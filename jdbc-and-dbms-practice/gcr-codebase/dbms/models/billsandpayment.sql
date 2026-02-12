
CREATE TABLE prescriptions (
    prescription_id INT AUTO_INCREMENT PRIMARY KEY,
    visit_id INT NOT NULL,
    medicine_name VARCHAR(100) NOT NULL,
    dosage VARCHAR(100),
    duration VARCHAR(50),
    instructions TEXT,
    FOREIGN KEY (visit_id) REFERENCES visits(visit_id)
        ON DELETE CASCADE
);



CREATE TABLE bills (
    bill_id INT AUTO_INCREMENT PRIMARY KEY,
    visit_id INT UNIQUE NOT NULL,
    total_amount DECIMAL(10,2) NOT NULL,
    payment_status ENUM('PAID','UNPAID') DEFAULT 'UNPAID',
    bill_date DATE DEFAULT (CURRENT_DATE),
    payment_date DATE,
    payment_mode ENUM('CASH','CARD','UPI','ONLINE'),
    FOREIGN KEY (visit_id) REFERENCES visits(visit_id)
        ON DELETE CASCADE
);

CREATE TABLE payment_transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    bill_id INT NOT NULL,
    amount_paid DECIMAL(10,2) NOT NULL,
    payment_mode ENUM('CASH','CARD','UPI','ONLINE'),
    transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (bill_id) REFERENCES bills(bill_id)
        ON DELETE CASCADE
);