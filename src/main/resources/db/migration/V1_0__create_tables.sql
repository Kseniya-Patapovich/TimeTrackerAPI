CREATE TABLE IF NOT EXISTS time_tracker_user(
                                                id bigserial PRIMARY KEY,
                                                first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    login varchar(100) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    user_role varchar(100) NOT NULL,
    locked BOOLEAN DEFAULT false
    );

CREATE TABLE IF NOT EXISTS task (
                                    id bigserial PRIMARY KEY,
                                    task_name varchar(100) NOT NULL,
    description varchar(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS user_to_task
(
    user_id INT NOT NULL,
    task_id INT NOT NULL,
    PRIMARY KEY (user_id, task_id),
    FOREIGN KEY (user_id) REFERENCES time_tracker_user (id),
    FOREIGN KEY (task_id) REFERENCES task (id)
    );

CREATE TABLE IF NOT EXISTS record(
                                     id bigserial PRIMARY KEY,
                                     spent DOUBLE PRECISION NOT NULL,
                                     user_id INT NOT NULL,
                                     task_id INT NOT NULL,
                                     FOREIGN KEY (user_id) REFERENCES time_tracker_user (id),
    FOREIGN KEY (task_id) REFERENCES task (id)
    );

INSERT INTO time_tracker_user (first_name, last_name, login, password, user_role, locked)
VALUES ('Admin', 'Admin', 'adminAdmin', '$2a$12$U3EmgUUzLqpBofHzlb4Cjelu8W0QS6ltxi0Y9Lm2oz5q4WTq6ZKM.', 'ADMIN', false);