CREATE TABLE chuni_game_event_new
(
    id         INTEGER NOT NULL,
    end_date   DATETIME,
    start_date DATETIME,
    type       INTEGER NOT NULL,
    enable     BOOLEAN NOT NULL,
    PRIMARY KEY (
                 id
        )
);

INSERT INTO chuni_game_event_new(id,
                                 end_date,
                                 start_date,
                                 type,
                                 enable)
SELECT id,
       end_date,
       start_date,
       type,
       true
FROM chuni_game_event;

DROP TABLE chuni_game_event;
ALTER TABLE chuni_game_event_new RENAME TO chuni_game_event;

UPDATE `chuni_game_event`
SET `enable`= false
WHERE `type` = 1;