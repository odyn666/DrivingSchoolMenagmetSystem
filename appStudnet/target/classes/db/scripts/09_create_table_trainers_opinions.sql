CREATE TABLE IF NOT EXISTS trainers_opinions (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    student_id INTEGER,
    trainer_id INTEGER,
    opinion TEXT,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (trainer_id) REFERENCES trainers(id)
)