CREATE TABLE IF NOT EXISTS time_tracker_user(
    id bigserial PRIMARY KEY,
    first_name varchar(50) NOT NULL,
    last_name varchar(50) NOT NULL,
    login varchar(100) NOT NULL UNIQUE,
    password varchar(100) NOT NULL,
    user_role varchar(100) NOT NULL
);

CREATE  TABLE IF NOT EXISTS task(
    id bigserial PRIMARY KEY,
    task_name varchar(100) NOT NULL,
);

CREATE TABLE IF NOT EXISTS record(
    id bigserial PRIMARY KEY,
    spent DOUBLE NOT NULL,
    user_id INT NOT NULL,
    project_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES time_tracker_user (id),
    FOREIGN KEY (task_id) REFERENCES task (id)
);