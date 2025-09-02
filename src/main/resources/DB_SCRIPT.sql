CREATE DATABASE IF NOT EXISTS g56_student_db

DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;
-- - Uses UTF-8 encoding (utf8mb4) to support special characters
-- - Uses Unicode collation (utf8mb4_unicode_ci) for proper sorting

USE g56_student_db;

CREATE TABLE IF NOT EXISTS student (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- Unique ID for each student (Auto-increments automatically)
    name VARCHAR(100) NOT NULL,                  -- Student's full name (Cannot be NULL)
    class_group VARCHAR(50) NOT NULL,            -- Class or section name (Renamed from `_group` to avoid MySQL keyword conflict)
    create_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Stores the date & time when the student was added
);

CREATE TABLE IF NOT EXISTS attendance (
    id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,  -- Unique ID for each attendance record
    student_id INT NOT NULL,                     -- Links attendance to a specific student (Foreign Key)
    attendance_date DATE NOT NULL DEFAULT (CURRENT_DATE), -- Stores the attendance date (Defaults to today)
    status ENUM('Present', 'Absent') NOT NULL,   -- Restricts attendance values to either "Present" or "Absent"

    FOREIGN KEY (student_id) REFERENCES student(id) ON DELETE CASCADE, -- Links attendance records to the student table through id, Ensures attendance records are deleted if a student is removed
    UNIQUE (student_id, attendance_date) -- Prevents duplicate attendance records for the same student on the same day
);

INSERT INTO student (name, class_group) VALUES
('Alice Johnson', 'G1'),
('Bob Smith', 'G1'),
('Charlie Brown', 'G2'),
('David Wilson', 'G2');