CREATE TABLE IF NOT EXISTS trainer_opinions (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    student_id INTEGER,
    trainer_id INTEGER,
    opinion TEXT,
    FOREIGN KEY (student_id) REFERENCES students(id),
    FOREIGN KEY (trainer_id) REFERENCES trainers(id)
)