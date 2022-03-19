CREATE TABLE chusan_music (
    music_id        INTEGER       NOT NULL,
    artist_name     VARCHAR (255),
    genre           INTEGER,
    name            VARCHAR (255),
    release_version VARCHAR (255),
    sort_name       VARCHAR (255),
    PRIMARY KEY (
        music_id
    )
);

CREATE TABLE chusan_music_level (
    id            INTEGER,
    diff          INTEGER NOT NULL,
    enable        BOOLEAN NOT NULL,
    level         INTEGER NOT NULL,
    level_decimal INTEGER NOT NULL,
    music_id      INTEGER,
    PRIMARY KEY (
        id
    )
);

CREATE TABLE chusan_game_character (
    id               INTEGER,
    name             VARCHAR NOT NULL,
    release_tag      VARCHAR NOT NULL,
    works_name       VARCHAR,
    illustrator_name VARCHAR,
    add_images       VARCHAR NOT NULL,
    PRIMARY KEY (
        id
    )
);

CREATE TABLE chusan_trophy (
    id   INTEGER,
    name VARCHAR (255),
    PRIMARY KEY (
        id
    )
);

CREATE TABLE chusan_nameplate (
    id   INTEGER,
    name VARCHAR (255),
    PRIMARY KEY (
        id
    )
);

CREATE TABLE chusan_voice (
    id   INTEGER,
    name VARCHAR (255),
    PRIMARY KEY (
        id
    )
);

CREATE TABLE chusan_mapicon (
    id   INTEGER,
    name VARCHAR (255),
    PRIMARY KEY (
        id
    )
);

CREATE TABLE chusan_frame (
    id   INTEGER,
    name VARCHAR (255),
    PRIMARY KEY (
        id
    )
);

CREATE TABLE chusan_avatar (
    id   INTEGER,
    name VARCHAR (255),
    category   INTEGER,
    PRIMARY KEY (
        id
    )
);