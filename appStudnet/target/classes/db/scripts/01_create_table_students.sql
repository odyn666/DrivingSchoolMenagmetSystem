CREATE TABLE IF NOT EXISTS students (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(128),
    last_name VARCHAR(128),
    status VARCHAR(128),
    phone_number VARCHAR(128),
    email VARCHAR(128),
    password VARCHAR(128),
    hours_left INTEGER,
    hours_driven INTEGER,
    last_login TIMESTAMP,
    exams_failed INTEGER,
    lessons_attended INTEGER,
    lessons_ommited INTEGER,
    exam_passed VARCHAR(128)
)