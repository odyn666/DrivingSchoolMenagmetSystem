CREATE TABLE IF NOT EXISTS students (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(128),
    last_name VARCHAR(128),
    pkk_number VARCHAR(128),
    status VARCHAR(128),
    phone_number VARCHAR(128),
    email VARCHAR(128),
    password VARCHAR(128),
    hours_left INTEGER DEFAULT 0,
    hours_driven INTEGER DEFAULT 0,
    last_login TIMESTAMP,
    exams_failed INTEGER,
    lessons_attended INTEGER,
    lessons_omitted INTEGER,
    is_final_exam_passed BOOLEAN
)