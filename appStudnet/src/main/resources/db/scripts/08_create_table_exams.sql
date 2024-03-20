CREATE TABLE IF NOT EXISTS exams (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    type VARCHAR(128),
    student_id INTEGER,
    passed BOOLEAN,
    score INTEGER,
    description TEXT,
    FOREIGN KEY(student_id) REFERENCES students(id)
)