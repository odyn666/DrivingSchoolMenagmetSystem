DROP TABLE IF EXISTS GOALS;

CREATE TABLE goals (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(128),
    description TEXT,
    creation_date TIMESTAMP,
    start_date TIMESTAMP,
    planned_end_date TIMESTAMP,
    end_date TIMESTAMP,
    category varchar(64),
    state varchar(64)
)