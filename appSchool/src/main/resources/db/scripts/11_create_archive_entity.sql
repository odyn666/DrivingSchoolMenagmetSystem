CREATE TABLE archive (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    delete_threshold INT NOT NULL DEFAULT 24
);

ALTER TABLE students
ADD COLUMN archive_id INT;

-- changeset you:2-add-archive-fk
ALTER TABLE students
ADD CONSTRAINT fk_students_archive
FOREIGN KEY (archive_id)
REFERENCES archive(id)
ON DELETE SET NULL;