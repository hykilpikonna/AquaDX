ALTER TABLE wacca_user_song_unlock
    DROP FOREIGN KEY fku_wacca_user_song_unlock;

ALTER TABLE wacca_user_ticket
    DROP FOREIGN KEY fku_wacca_user_ticket;

ALTER TABLE wacca_user_trophy
    DROP FOREIGN KEY fku_wacca_user_trophy;

DROP TABLE maimai2_game_ticket;

DROP TABLE wacca_user_song_unlock;

DROP TABLE wacca_user_ticket;

DROP TABLE wacca_user_trophy;

ALTER TABLE wacca_user_gate
    ADD last_used datetime NOT NULL;

ALTER TABLE wacca_user_item
    ADD p1 BIGINT NOT NULL;

ALTER TABLE wacca_user_item
    ADD p2 BIGINT NOT NULL;

ALTER TABLE wacca_user_item
    ADD p3 BIGINT NOT NULL;

ALTER TABLE wacca_user_item
    DROP COLUMN acquire_date;

ALTER TABLE wacca_user_item
    DROP COLUMN use_count;

ALTER TABLE wacca_user
    DROP COLUMN last_login_date;

ALTER TABLE wacca_user
    DROP COLUMN vip_expire_time;

ALTER TABLE wacca_user
    ADD last_login_date time NOT NULL;

ALTER TABLE wacca_user
    ADD vip_expire_time time NOT NULL;
