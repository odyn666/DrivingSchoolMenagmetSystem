CREATE TABLE IF NOT EXISTS exams (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    type VARCHAR(128),
    student_id INTEGER,
    passed BOOLEAN,
    score INTEGER,
    description TEXT,
    FOREIGN KEY(student_id) REFERENCES students(id)
)