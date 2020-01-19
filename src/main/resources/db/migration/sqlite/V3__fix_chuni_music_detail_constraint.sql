
CREATE TABLE chuni_user_music_detail_new (
                                         id                INTEGER,
                                         full_chain        INTEGER NOT NULL,
                                         is_all_justice    BOOLEAN NOT NULL,
                                         is_full_combo     BOOLEAN NOT NULL,
                                         is_lock           BOOLEAN NOT NULL,
                                         is_success        BOOLEAN NOT NULL,
                                         level             INTEGER NOT NULL,
                                         max_chain         INTEGER NOT NULL,
                                         max_combo_count   INTEGER NOT NULL,
                                         miss_count        INTEGER NOT NULL,
                                         music_id          INTEGER NOT NULL,
                                         play_count        INTEGER NOT NULL,
                                         res_accept_count  INTEGER NOT NULL,
                                         res_request_count INTEGER NOT NULL,
                                         res_success_count INTEGER NOT NULL,
                                         score_max         INTEGER NOT NULL,
                                         score_rank        INTEGER NOT NULL,
                                         user_id           BIGINT  REFERENCES chuni_user_data (id) ON DELETE CASCADE,
                                         PRIMARY KEY (
                                                      id
                                             ),
                                         CONSTRAINT chuni_user_music_detail_uq UNIQUE (
                                                                                       level,
                                                                                       music_id,
                                                                                       user_id
                                             )
);

INSERT INTO chuni_user_music_detail_new (
    id,
    full_chain,
    is_all_justice,
    is_full_combo,
    is_lock,
    is_success,
    level,
    max_chain,
    max_combo_count,
    miss_count,
    music_id,
    play_count,
    res_accept_count,
    res_request_count,
    res_success_count,
    score_max,
    score_rank,
    user_id
)
SELECT id,
       full_chain,
       is_all_justice,
       is_full_combo,
       is_lock,
       is_success,
       level,
       max_chain,
       max_combo_count,
       miss_count,
       music_id,
       play_count,
       res_accept_count,
       res_request_count,
       res_success_count,
       score_max,
       score_rank,
       user_id
FROM chuni_user_music_detail;

ALTER TABLE chuni_user_music_detail RENAME TO bak_chuni_user_music_detail;
ALTER TABLE chuni_user_music_detail_new RENAME TO chuni_user_music_detail;
