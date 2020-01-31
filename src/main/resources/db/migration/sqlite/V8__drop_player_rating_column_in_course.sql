CREATE TABLE chuni_user_course_new
(
    id             INTEGER,
    class_id       INTEGER NOT NULL,
    course_id      INTEGER NOT NULL,
    event_id       INTEGER NOT NULL,
    is_all_justice BOOLEAN NOT NULL,
    is_clear       BOOLEAN NOT NULL,
    is_full_combo  BOOLEAN NOT NULL,
    is_success     BOOLEAN NOT NULL,
    last_play_date DATETIME,
    param1         INTEGER NOT NULL,
    param2         INTEGER NOT NULL,
    param3         INTEGER NOT NULL,
    param4         INTEGER NOT NULL,
    play_count     INTEGER NOT NULL,
    score_max      INTEGER NOT NULL,
    score_rank     INTEGER NOT NULL,
    user_id        BIGINT REFERENCES chuni_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT chuni_user_course_uq UNIQUE (
                                            course_id,
                                            user_id
        )
);

INSERT INTO chuni_user_course_new (id,
                                   class_id,
                                   course_id,
                                   event_id,
                                   is_all_justice,
                                   is_clear,
                                   is_full_combo,
                                   is_success,
                                   last_play_date,
                                   param1,
                                   param2,
                                   param3,
                                   param4,
                                   play_count,
                                   score_max,
                                   score_rank,
                                   user_id)
SELECT id,
       class_id,
       course_id,
       event_id,
       is_all_justice,
       is_clear,
       is_full_combo,
       is_success,
       last_play_date,
       param1,
       param2,
       param3,
       param4,
       play_count,
       score_max,
       score_rank,
       user_id
FROM chuni_user_course;

DROP TABLE chuni_user_course;
ALTER TABLE chuni_user_course_new RENAME TO chuni_user_course;