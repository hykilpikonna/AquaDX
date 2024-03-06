CREATE TABLE chusan_user_cmission
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    user_id    BIGINT                NULL,
    mission_id INT                   NULL,
    point      INT                   NULL,
    CONSTRAINT pk_chusan_user_cmission PRIMARY KEY (id)
);

ALTER TABLE chusan_user_cmission
    ADD CONSTRAINT FK_CHUSAN_USER_CMISSION_ON_USER FOREIGN KEY (user_id) REFERENCES chusan_user_data (id);

CREATE TABLE chusan_user_cmission_progress
(
    id         BIGINT AUTO_INCREMENT NOT NULL,
    user_id    BIGINT                NULL,
    mission_id INT                   NULL,
    `order`    INT                   NOT NULL,
    stage      INT                   NULL,
    progress   INT                   NULL,
    CONSTRAINT pk_chusan_user_cmission_progress PRIMARY KEY (id)
);

ALTER TABLE chusan_user_cmission_progress
    ADD CONSTRAINT uc_6ab791e9e8fee2b3fab35d3d2 UNIQUE (user_id, mission_id, `order`);

ALTER TABLE chusan_user_cmission_progress
    ADD CONSTRAINT FK_CHUSAN_USER_CMISSION_PROGRESS_ON_USER FOREIGN KEY (user_id) REFERENCES chusan_user_data (id);