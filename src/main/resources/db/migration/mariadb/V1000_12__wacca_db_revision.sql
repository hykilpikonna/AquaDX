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
    ADD p1 BIGINT NOT NULL,
    ADD p2 BIGINT NOT NULL,
    ADD p3 BIGINT NOT NULL,
    DROP COLUMN acquire_date,
    DROP COLUMN use_count,
    ADD acquired_date datetime NOT NULL,
    DROP CONSTRAINT wacca_user_item_unique,
    ADD CONSTRAINT wacca_user_item_unique UNIQUE (user_id, item_id, type);

ALTER TABLE wacca_user
    DROP COLUMN last_login_date,
    DROP COLUMN vip_expire_time,
    ADD last_login_date datetime NOT NULL,
    ADD vip_expire_time datetime NOT NULL;
