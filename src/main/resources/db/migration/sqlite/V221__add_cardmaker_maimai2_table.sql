-- maimai2 card maker support

CREATE TABLE maimai2_game_selling_card (
    id                INTEGER,
    card_id           INTEGER,
    start_date        DATETIME,
    end_date          DATETIME,
    notice_start_date DATETIME,
    notice_end_date   DATETIME,
    PRIMARY KEY (
        id
    )
);

CREATE TABLE maimai2_user_card (
    id           INTEGER,
    card_id      INTEGER,
    card_type_id INTEGER,
    chara_id     INTEGER,
    map_id       INTEGER,
    start_date   VARCHAR,
    end_date     VARCHAR,
    user_id      BIGINT  REFERENCES maimai2_user_detail (id),
    PRIMARY KEY (
        id
    )
);

CREATE TABLE maimai2_user_print_detail (
    id                INTEGER,
    order_id          BIGINT,
    print_number      INTEGER,
    print_date        VARCHAR,
    serial_id         VARCHAR,
    place_id          INTEGER,
    client_id         VARCHAR,
    printer_serial_id VARCHAR,
    user_card_id      INTEGER REFERENCES maimai2_user_card (id),
    card_rom_version  INTEGER,
    is_holograph      BOOLEAN,
    print_option1     BOOLEAN,
    print_option2     BOOLEAN,
    print_option3     BOOLEAN,
    print_option4     BOOLEAN,
    print_option5     BOOLEAN,
    print_option6     BOOLEAN,
    print_option7     BOOLEAN,
    print_option8     BOOLEAN,
    print_option9     BOOLEAN,
    print_option10    BOOLEAN,
    created           VARCHAR,
    user_id           BIGINT REFERENCES maimai2_user_detail (id),
    PRIMARY KEY (
        id
    )
);
