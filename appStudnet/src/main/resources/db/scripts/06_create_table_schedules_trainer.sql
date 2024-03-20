CREATE TABLE IF NOT EXISTS schedules_trainers (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    trainer_id INTEGER,
    date DATE,
    starting_hour VARCHAR(128),
    ending_hour VARCHAR(128),
    FOREIGN KEY (trainer_id) REFERENCES trainers(id)
)