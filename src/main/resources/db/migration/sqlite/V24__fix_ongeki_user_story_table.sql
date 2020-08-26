PRAGMA foreign_keys = 0;

CREATE TABLE ongeki_user_story_new (
    id                        INTEGER,
    last_chapter_id           INTEGER NOT NULL,
    story_id                  INTEGER NOT NULL,
    jewel_count               INTEGER,
    last_play_music_id        INTEGER,
    last_play_music_category  INTEGER,
    last_play_music_level     INTEGER,
    user_id                   BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_story_uq UNIQUE (
                                            story_id,
                                            user_id
        ) ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_story_new (
    id,
    last_chapter_id,
    story_id,
    user_id
)
SELECT  id,
        last_chapter_id,
        story_id,
        user_id
FROM ongeki_user_story;

ALTER TABLE ongeki_user_story RENAME TO bak_ongeki_user_story;
ALTER TABLE ongeki_user_story_new RENAME TO ongeki_user_story;

PRAGMA foreign_keys = 1;
