CREATE TABLE IF NOT EXISTS trainers (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(128),
    last_name VARCHAR(128),
    identifier VARCHAR(255),
    phone_number VARCHAR(128),
    email VARCHAR(128),
    password VARCHAR(128),
    status VARCHAR(128),
    students_pass_rate INTEGER,
    car_id INTEGER,
    FOREIGN KEY(car_id) REFERENCES cars(id)
)