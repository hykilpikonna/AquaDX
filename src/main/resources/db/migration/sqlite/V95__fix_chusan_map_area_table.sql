-- chusan_user_map_area

CREATE TEMPORARY TABLE temp AS SELECT * FROM chusan_user_map_area;
DROP TABLE chusan_user_map_area;

CREATE TABLE chusan_user_map_area (
    id                INTEGER,
    is_clear          BOOLEAN NOT NULL,
    is_locked         BOOLEAN NOT NULL,
    map_area_id       INTEGER NOT NULL,
    position          INTEGER NOT NULL,
    rate              INTEGER NOT NULL,
    status_count      INTEGER NOT NULL,
    remain_grid_count INTEGER NOT NULL,
    user_id           BIGINT  REFERENCES chusan_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT chusan_user_map_uq UNIQUE (
        map_area_id,
        user_id
    )
);

INSERT INTO chusan_user_map_area SELECT * FROM temp;
DROP TABLE temp;