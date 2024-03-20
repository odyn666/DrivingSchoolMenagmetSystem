CREATE TABLE IF NOT EXISTS schedules_students (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    student_id INTEGER,
    date DATE,
    starting_hour VARCHAR(128),
    ending_hour VARCHAR(128),
    FOREIGN KEY (student_id) REFERENCES students (id)
)