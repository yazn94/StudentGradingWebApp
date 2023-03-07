DROP DATABASE IF EXISTS studentGradingDB;

CREATE DATABASE studentGradingDB;

USE studentGradingDB;

CREATE TABLE students
(
    id        BIGINT      NOT NULL AUTO_INCREMENT,
    firstName VARCHAR(20) NOT NULL,
    lastName  VARCHAR(20) NOT NULL,
    age       INT         NOT NULL,
    phone     BIGINT(10)  NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE courses
(
    id   BIGINT      NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);
CREATE TABLE AcademicStaffCredentials
(
    id       BIGINT      NOT NULL AUTO_INCREMENT,
    email    VARCHAR(50) NOT NULL,
    password VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE grades
(
    studentId BIGINT NOT NULL,
    courseId  BIGINT NOT NULL,
    grade     INT    NOT NULL,

    Foreign Key (studentId) REFERENCES students (id) ON DELETE CASCADE,
    Foreign Key (courseId) REFERENCES courses (id) ON DELETE CASCADE,
    PRIMARY KEY (studentId, courseId)
);

# Inserting some records into the tables
INSERT INTO students (firstName, lastName, age, phone)
VALUES ('John', 'Doe', 20, 5551234567),
       ('Jane', 'Doe', 19, 5559876543),
       ('Bob', 'Smith', 21, 5555551212),
       ('Alice', 'Johnson', 18, 5555551234),
       ('Mike', 'Jones', 22, 5557778888),
       ('Emily', 'Davis', 20, 5555554321),
       ('Alex', 'Garcia', 19, 5559990000),
       ('Maria', 'Rodriguez', 21, 5551234567),
       ('David', 'Wilson', 18, 5555557890),
       ('Sarah', 'Lee', 22, 5554443333);

INSERT INTO courses (name)
VALUES ('Math'),
       ('English'),
       ('Science'),
       ('History'),
       ('Art'),
       ('Music'),
       ('Physical Education'),
       ('Computer Science'),
       ('Biology'),
       ('Chemistry');

INSERT INTO AcademicStaffCredentials (email, password)
VALUES ('admin1@gmail.com', '123456'),
       ('john.doe@gmail.com', 'password1'),
       ('jane.doe@gmail.com', 'password2');


INSERT INTO grades (studentId, courseId, grade)
VALUES (1, 1, 85),
       (1, 2, 90),
       (1, 3, 80),
       (2, 2, 90),
       (2, 4, 80),
       (2, 7, 85),
       (3, 4, 75),
       (3, 5, 95),
       (3, 6, 90),
       (4, 10, 80),
       (4, 9, 85),
       (4, 8, 95),
       (5, 4, 70),
       (5, 6, 90),
       (5, 2, 75),
       (6, 4, 90),
       (6, 1, 80),
       (7, 1, 85),
       (8, 3, 90),
       (8, 1, 80),
       (8, 4, 85),
       (9, 2, 75),
       (9, 9, 95),
       (9, 5, 90),
       (10, 7, 80),
       (10, 8, 85),
       (10, 2, 95);