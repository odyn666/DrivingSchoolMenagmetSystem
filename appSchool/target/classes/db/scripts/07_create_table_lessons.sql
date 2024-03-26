CREATE TABLE IF NOT EXISTS lessons (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    trainer_id INTEGER,
    student_id INTEGER,
    date DATE,
    starting_hour VARCHAR(128),
    ending_hour VARCHAR(128),
    status VARCHAR(128),
    FOREIGN KEY (trainer_id) REFERENCES trainers(id),
    FOREIGN KEY (student_id) REFERENCES students(id)
)