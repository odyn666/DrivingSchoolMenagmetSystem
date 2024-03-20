CREATE TABLE IF NOT EXISTS payments (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    payment_uid VARCHAR(255),
    student_id INTEGER,
    amount INTEGER,
    hours INTEGER,
    due_date TIMESTAMP,
    status VARCHAR(128),
    FOREIGN KEY (student_id) REFERENCES students (id)
)