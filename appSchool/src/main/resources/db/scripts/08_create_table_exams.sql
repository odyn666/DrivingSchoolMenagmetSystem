CREATE TABLE IF NOT EXISTS exams (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    exam_type VARCHAR(128),
    student_id INTEGER,
    is_passed BOOLEAN DEFAULT FALSE,
    score INTEGER DEFAULT 0,
    description TEXT,
    FOREIGN KEY(student_id) REFERENCES students(id)
)