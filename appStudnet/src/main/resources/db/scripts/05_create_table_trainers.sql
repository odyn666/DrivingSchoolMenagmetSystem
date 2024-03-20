CREATE TABLE IF NOT EXISTS trainers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    first_name VARCHAR(128),
    last_name VARCHAR(128),
    identifier VARCHAR(255),
    phoneNumber VARCHAR(128),
    email VARCHAR(128),
    password VARCHAR(128),
    status VARCHAR(128),
    courstantsPassability INTEGER,
    car_id INTEGER,
    FOREIGN KEY(car_id) REFERENCES cars(id)
)