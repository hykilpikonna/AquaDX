-- I got zero bug report about this before so.. just assume that user never used this table and API.

DROP TABLE `maimai2_user_course`;

CREATE TABLE `maimai2_user_course` (
    id                    INTEGER,
    course_id             INTEGER,
    is_last_clear         BOOLEAN,
    total_restlife       INTEGER,
    total_achievement     INTEGER,
    total_deluxscore      INTEGER,
    play_count            INTEGER,
    clear_date            VARCHAR (255),
    last_play_date        VARCHAR (255),
    best_achievement      INTEGER,
    best_achievement_date VARCHAR (255),
    best_deluxscore       INTEGER,
    best_deluxscore_date  VARCHAR (255),
    user_id               BIGINT        REFERENCES maimai2_user_detail (id),
    PRIMARY KEY (
        id
    )
);

