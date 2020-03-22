-- Table: ongeki_user_mission_point
CREATE TABLE ongeki_user_mission_point
(
    id                  INTEGER,
    event_id            INTEGER NOT NULL,
    point               BIGINT  NOT NULL,
    user_id             BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_mission_point_uq UNIQUE (
                                                  event_id,
                                                  user_id
        ) ON CONFLICT REPLACE
);

CREATE TABLE ongeki_game_point
(
    id         BIGINT,
    type       INTEGER      NOT NULL,
    cost       INTEGER      NOT NULL,
    start_date VARCHAR(255) NOT NULL,
    end_date   VARCHAR(255) NOT NULL,
    PRIMARY KEY (
                 id
        )
);

CREATE TABLE ongeki_game_present
(
    id           BIGINT,
    present_name VARCHAR(255) NOT NULL,
    reward_id    INTEGER      NOT NULL,
    stock        INTEGER      NOT NULL,

    message      VARCHAR(255) NOT NULL,
    start_date   VARCHAR(255) NOT NULL,
    end_date     VARCHAR(255) NOT NULL,
    PRIMARY KEY (
                 id
        )
);

CREATE TABLE ongeki_game_reward
(
    id        BIGINT,
    item_kind INTEGER NOT NULL,
    item_id   INTEGER NOT NULL,
    PRIMARY KEY (
                 id
        )
);

CREATE TABLE ongeki_user_general_data
(
    id             INTEGER,
    property_key   VARCHAR NOT NULL,
    property_value VARCHAR NOT NULL,
    user_id        BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_general_data_uq UNIQUE (
                                                   property_key,
                                                   user_id
        ) ON CONFLICT REPLACE
);

INSERT INTO ongeki_game_point (id, type, cost, start_date, end_date) VALUES (1, 0, 100, '2000-01-01 05:00:00.0', '2099-01-01 05:00:00.0');
INSERT INTO ongeki_game_point (id, type, cost, start_date, end_date) VALUES (2, 1, 200, '2000-01-01 05:00:00.0', '2099-01-01 05:00:00.0');
INSERT INTO ongeki_game_point (id, type, cost, start_date, end_date) VALUES (3, 2, 300, '2000-01-01 05:00:00.0', '2099-01-01 05:00:00.0');
INSERT INTO ongeki_game_point (id, type, cost, start_date, end_date) VALUES (4, 3, 333, '2000-01-01 05:00:00.0', '2099-01-01 05:00:00.0');
INSERT INTO ongeki_game_point (id, type, cost, start_date, end_date) VALUES (5, 4, 666, '2000-01-01 05:00:00.0', '2099-01-01 05:00:00.0');
INSERT INTO ongeki_game_point (id, type, cost, start_date, end_date) VALUES (6, 5, 999, '2000-01-01 05:00:00.0', '2099-01-01 05:00:00.0');
