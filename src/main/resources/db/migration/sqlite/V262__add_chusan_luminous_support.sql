CREATE TABLE chusan_user_cmission
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    user_id    INTEGER,
    mission_id INTEGER,
    point      INTEGER,
    FOREIGN KEY(user_id) REFERENCES chusan_user_data(id)
);

CREATE TABLE chusan_user_cmission_progress
(
    id         INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    user_id    INTEGER,
    mission_id INTEGER,
    `order`    INTEGER NOT NULL,
    stage      INTEGER,
    progress   INTEGER,
    UNIQUE(user_id, mission_id, `order`),
    FOREIGN KEY(user_id) REFERENCES chusan_user_data(id)
);