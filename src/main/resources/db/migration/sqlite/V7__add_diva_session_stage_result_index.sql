DROP TABLE diva_game_session;

CREATE TABLE diva_game_session
(
    id               INTEGER,
    accept_id        INTEGER NOT NULL,
    last_pv_id       INTEGER,
    last_update_time DATETIME,
    level_exp        INTEGER,
    level_number     INTEGER,
    old_level_exp    INTEGER,
    old_level_number INTEGER,
    stage_index      INTEGER,
    stage_result_index INTEGER,
    start_mode       VARCHAR(255),
    start_time       DATETIME,
    vp               INTEGER,
    pd_id            BIGINT UNIQUE
        REFERENCES diva_player_profile (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        )
);