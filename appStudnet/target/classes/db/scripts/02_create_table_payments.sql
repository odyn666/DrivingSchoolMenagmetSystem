CREATE TABLE IF NOT EXISTS payments (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    payment_uid UID,
    student_id INTEGER,
    amount INTEGER,
    hours INTEGER,
    due_date TIMESTAMP,
    status VARCHAR(128),
    FOREIGN KEY (student_id) REFERENCES students (id)
)