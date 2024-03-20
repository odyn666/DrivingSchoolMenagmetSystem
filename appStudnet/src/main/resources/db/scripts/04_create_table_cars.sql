CREATE TABLE IF NOT EXISTS cars (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    plates VARCHAR(255) NOT NULL,
    brand TEXT NOT NULL,
    model TEXT NOT NULL,
    prod_year INTEGER NOT NULL,
    mileage INTEGER NOT NULL,
    last_maintenance_mileage INTEGER NOT NULL
)