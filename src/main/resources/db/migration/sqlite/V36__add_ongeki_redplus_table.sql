CREATE TABLE ongeki_user_trade_item (
    id            INTEGER,
    chapter_id    INTEGER,
    trade_item_id INTEGER,
    trade_count   INTEGER,
    user_id       BIGINT  REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_trade_item_uq UNIQUE (
        chapter_id,
        trade_item_id,
        user_id
    )
    ON CONFLICT REPLACE
);

CREATE TABLE ongeki_user_tech_event (
    id                       INTEGER,
    event_id                 INTEGER,
    total_tech_score         INTEGER,
    total_platinum_score     INTEGER,
    tech_record_date         VARCHAR (255),
    is_ranking_rewarded      BOOLEAN,
    is_total_tech_new_record BOOLEAN,
    user_id                  BIGINT        REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_tech_event_uq UNIQUE (
        event_id,
        user_id
    )
    ON CONFLICT REPLACE
);

CREATE TABLE ongeki_user_kop (
    id                       INTEGER,
    auth_key                 VARCHAR (255),
    kop_id                   INTEGER,
    area_id                  INTEGER,
    total_tech_score         INTEGER,
    total_platinum_score     INTEGER,
    tech_record_date         VARCHAR (255),
    is_total_tech_new_record BOOLEAN,
    user_id                  BIGINT        REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_kop_uq UNIQUE (
        kop_id,
        area_id,
        user_id
    )
    ON CONFLICT REPLACE
);

CREATE TABLE ongeki_user_event_music (
    id                 INTEGER,
    event_id           INTEGER,
    type               INTEGER,
    music_id           INTEGER,
    level              INTEGER,
    tech_score_max     INTEGER,
    platinum_score_max INTEGER,
    tech_record_date   VARCHAR (255),
    is_tech_new_record BOOLEAN,
    user_id            BIGINT        REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_event_music_uq UNIQUE (
        event_id,
        type,
        music_id,
        user_id
    )
    ON CONFLICT REPLACE
);