CREATE TABLE IF NOT EXISTS cars (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    plates VARCHAR(255) NOT NULL,
    brand VARCHAR(128) NOT NULL,
    model VARCHAR(128) NOT NULL,
    prod_year INTEGER NOT NULL,
    mileage INTEGER NOT NULL,
    last_maintenance_mileage INTEGER NOT NULL
--     dorzucić tankowanie i usterki
)