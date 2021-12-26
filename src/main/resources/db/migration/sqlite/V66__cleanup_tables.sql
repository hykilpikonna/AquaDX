-- Affected game table: maimai2, ongeki
-- This fixes reference table (FK entries) mismatch due to "create-copy-drop-rename" nature of sqlite
-- Sqlite doesn't provide a way to edit FK so this mess always happens

-- maimai2_user_activity
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_activity;
DROP TABLE maimai2_user_activity;

CREATE TABLE maimai2_user_activity (
    id          INTEGER,
    kind        INTEGER,
    activity_id INTEGER,
    sort_number BIGINT,
    param1      INTEGER,
    param2      INTEGER,
    param3      INTEGER,
    param4      INTEGER,
    user_id     BIGINT  REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_activity SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_character
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_character;
DROP TABLE maimai2_user_character;

CREATE TABLE maimai2_user_character (
    id           INTEGER,
    character_id INTEGER,
    level        INTEGER,
    awakening    INTEGER,
    use_count    INTEGER,
    user_id      BIGINT  REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_character SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_charge
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_charge;
DROP TABLE maimai2_user_charge;

CREATE TABLE maimai2_user_charge (
    id            INTEGER,
    charge_id     INTEGER,
    stock         INTEGER,
    purchase_date VARCHAR (255),
    valid_date    VARCHAR (255),
    user_id       BIGINT        REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT maimai2_user_change_uq UNIQUE (
        charge_id,
        user_id
    )
);

INSERT INTO maimai2_user_charge SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_extend
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_extend;
DROP TABLE maimai2_user_extend;

CREATE TABLE maimai2_user_extend (
    id                    INTEGER,
    select_music_id       INTEGER,
    select_difficulty_id  INTEGER,
    category_index        INTEGER,
    music_index           INTEGER,
    extra_flag            INTEGER,
    select_score_type     INTEGER,
    extend_content_bit    BIGINT,
    is_photo_agree        BOOLEAN,
    is_goto_code_read     BOOLEAN,
    select_result_details BOOLEAN,
    sort_category_setting INTEGER,
    sort_music_setting    INTEGER,
    selected_card_list    VARCHAR (255),
    user_id               BIGINT        REFERENCES maimai2_user_detail (id),
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_extend SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_favorite
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_favorite;
DROP TABLE maimai2_user_favorite;

CREATE TABLE maimai2_user_favorite (
    id           INTEGER,
    fav_user_id  BIGINT,
    item_kind    INTEGER,
    item_id_list VARCHAR (255),
    user_id      BIGINT        REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_favorite SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_item
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_item;
DROP TABLE maimai2_user_item;

CREATE TABLE maimai2_user_item (
    id        INTEGER,
    item_kind INTEGER,
    item_id   INTEGER,
    stock     INTEGER,
    is_valid  BOOLEAN,
    user_id   BIGINT  REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_item SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_login_bonus
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_login_bonus;
DROP TABLE maimai2_user_login_bonus;

CREATE TABLE maimai2_user_login_bonus (
    id          INTEGER,
    bonus_id    INTEGER,
    point       INTEGER,
    is_current  BOOLEAN,
    is_complete BOOLEAN,
    user_id     BIGINT  REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_login_bonus SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_map
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_map;
DROP TABLE maimai2_user_map;

CREATE TABLE maimai2_user_map (
    id          INTEGER,
    map_id      INTEGER,
    distance    INTEGER,
    is_lock     BOOLEAN,
    is_clear    BOOLEAN,
    is_complete BOOLEAN,
    user_id     BIGINT  REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_map SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_music_detail
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_music_detail;
DROP TABLE maimai2_user_music_detail;

CREATE TABLE maimai2_user_music_detail (
    id             INTEGER,
    music_id       INTEGER,
    level          INTEGER,
    play_count     INTEGER,
    achievement    INTEGER,
    combo_status   INTEGER,
    sync_status    INTEGER,
    deluxscore_max INTEGER,
    score_rank     INTEGER,
    user_id        BIGINT  REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_music_detail SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_npc_encount
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_npc_encount;
DROP TABLE maimai2_user_npc_encount;

CREATE TABLE maimai2_user_npc_encount (
    id        INTEGER,
    npc_id    INTEGER,
    music_id  INTEGER,
    extend_id BIGINT  REFERENCES maimai2_user_extend (id) ON DELETE CASCADE,
    user_id   BIGINT  REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_npc_encount SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_activity
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_activity;
DROP TABLE ongeki_user_activity;

CREATE TABLE ongeki_user_activity (
    id          INTEGER,
    activity_id INTEGER,
    kind        INTEGER NOT NULL,
    param1      INTEGER NOT NULL,
    param2      INTEGER NOT NULL,
    param3      INTEGER NOT NULL,
    param4      INTEGER NOT NULL,
    sort_number INTEGER NOT NULL,
    user_id     BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_activity_uq UNIQUE (
        activity_id,
        kind,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_activity SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_boss
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_boss;
DROP TABLE ongeki_user_boss;

CREATE TABLE ongeki_user_boss
(
	id INTEGER,
	music_id INTEGER NOT NULL,
	damage INTEGER NOT NULL,
	is_clear BOOLEAN NOT NULL,
    event_id INTEGER,
    user_id BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_boss_uq UNIQUE (
                                           music_id,
                                           user_id
        ) ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_boss SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_card
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_card;
DROP TABLE ongeki_user_card;

CREATE TABLE ongeki_user_card (
    id             INTEGER,
    analog_stock   INTEGER       NOT NULL,
    card_id        INTEGER       NOT NULL,
    cho_kaika_date VARCHAR (255),
    created        VARCHAR (255),
    digital_stock  INTEGER       NOT NULL,
    exp            INTEGER       NOT NULL,
    is_acquired    BOOLEAN       NOT NULL,
    is_new         BOOLEAN       NOT NULL,
    kaika_date     VARCHAR (255),
    level          INTEGER       NOT NULL,
    max_level      INTEGER       NOT NULL,
    print_count    INTEGER       NOT NULL,
    skill_id       INTEGER       NOT NULL,
    use_count      INTEGER       NOT NULL,
    user_id        BIGINT        REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_card_uq UNIQUE (
        card_id,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_card SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_chapter
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_chapter;
DROP TABLE ongeki_user_chapter;

CREATE TABLE ongeki_user_chapter
(
    id                       INTEGER,
    chapter_id               INTEGER NOT NULL,
    is_clear                 BOOLEAN NOT NULL,
    is_story_watched         BOOLEAN NOT NULL,
    jewel_count              INTEGER NOT NULL,
    last_play_music_category INTEGER NOT NULL,
    last_play_music_id       INTEGER NOT NULL,
    last_play_music_level    INTEGER,
    skip_timing1             INTEGER NOT NULL,
    skip_timing2             INTEGER NOT NULL,
    user_id                  BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_chapter_uq UNIQUE (
                                              chapter_id,
                                              user_id
        ) ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_chapter SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_character
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_character;
DROP TABLE ongeki_user_character;

CREATE TABLE ongeki_user_character
(
    id                      INTEGER,
    character_id            INTEGER NOT NULL,
    costume_id              INTEGER,
    attachment_id           INTEGER,
    intimate_count          INTEGER NOT NULL,
    intimate_count_date     VARCHAR(255),
    intimate_count_rewarded INTEGER NOT NULL,
    intimate_level          INTEGER NOT NULL,
    is_new                  BOOLEAN NOT NULL,
    play_count              INTEGER NOT NULL,
    user_id                 BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_character_uq UNIQUE (
                                                character_id,
                                                user_id
        ) ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_character SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_deck
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_deck;
DROP TABLE ongeki_user_deck;

CREATE TABLE ongeki_user_deck (
    id       INTEGER,
    card_id1 INTEGER NOT NULL,
    card_id2 INTEGER NOT NULL,
    card_id3 INTEGER NOT NULL,
    deck_id  INTEGER NOT NULL,
    user_id  BIGINT  REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_deck_uq UNIQUE (
        deck_id,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_deck SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_event_music
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_event_music;
DROP TABLE ongeki_user_event_music;

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

INSERT INTO ongeki_user_event_music SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_event_point
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_event_point;
DROP TABLE ongeki_user_event_point;

CREATE TABLE ongeki_user_event_point (
    id                  INTEGER,
    event_id            INTEGER NOT NULL,
    is_ranking_rewarded BOOLEAN NOT NULL,
    point               BIGINT  NOT NULL,
    user_id             BIGINT  REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_event_point_uq UNIQUE (
        event_id,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_event_point SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_general_data
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_general_data;
DROP TABLE ongeki_user_general_data;

CREATE TABLE ongeki_user_general_data (
    id             INTEGER,
    property_key   VARCHAR NOT NULL,
    property_value VARCHAR NOT NULL,
    user_id        BIGINT  REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_general_data_uq UNIQUE (
        property_key,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_general_data SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_item
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_item;
DROP TABLE ongeki_user_item;

CREATE TABLE ongeki_user_item (
    id        INTEGER,
    is_valid  BOOLEAN NOT NULL,
    item_id   INTEGER NOT NULL,
    item_kind INTEGER NOT NULL,
    stock     INTEGER NOT NULL,
    user_id   BIGINT  REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_item_uq UNIQUE (
        item_id,
        item_kind,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_item SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_kop
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_kop;
DROP TABLE ongeki_user_kop;

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

INSERT INTO ongeki_user_kop SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_login_bonus
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_login_bonus;
DROP TABLE ongeki_user_login_bonus;

CREATE TABLE ongeki_user_login_bonus (
    id               INTEGER,
    bonus_id         INTEGER       NOT NULL,
    bonus_count      INTEGER       NOT NULL,
    last_update_date VARCHAR (255),
    user_id          BIGINT        REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_login_bonus_uq UNIQUE (
        bonus_id,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_login_bonus SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_mission_point
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_mission_point;
DROP TABLE ongeki_user_mission_point;

CREATE TABLE ongeki_user_mission_point (
    id       INTEGER,
    event_id INTEGER NOT NULL,
    point    BIGINT  NOT NULL,
    user_id  BIGINT  REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_mission_point_uq UNIQUE (
        event_id,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_mission_point SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_music_detail
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_music_detail;
DROP TABLE ongeki_user_music_detail;

CREATE TABLE ongeki_user_music_detail (
    id                 INTEGER,
    battle_score_max   INTEGER NOT NULL,
    battle_score_rank  INTEGER NOT NULL,
    clear_status       INTEGER NOT NULL,
    is_all_breake      BOOLEAN NOT NULL,
    is_full_bell       BOOLEAN NOT NULL,
    is_full_combo      BOOLEAN NOT NULL,
    is_lock            BOOLEAN NOT NULL,
    is_story_watched   BOOLEAN NOT NULL,
    level              INTEGER NOT NULL,
    max_combo_count    INTEGER NOT NULL,
    max_over_kill      INTEGER NOT NULL,
    max_team_over_kill INTEGER NOT NULL,
    music_id           INTEGER NOT NULL,
    play_count         INTEGER NOT NULL,
    tech_score_max     INTEGER NOT NULL,
    tech_score_rank    INTEGER NOT NULL,
    user_id            BIGINT  REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_music_detail_uq UNIQUE (
        level,
        music_id,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_music_detail SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_music_item
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_music_item;
DROP TABLE ongeki_user_music_item;

CREATE TABLE ongeki_user_music_item (
    id       INTEGER,
    music_id INTEGER NOT NULL,
    status   INTEGER NOT NULL,
    user_id  BIGINT  REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_music_item_uq UNIQUE (
        music_id,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_music_item SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_option
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_option;
DROP TABLE ongeki_user_option;

CREATE TABLE "ongeki_user_option" (
    id INTEGER,
    "abort" INTEGER NOT NULL,
    color_field INTEGER NOT NULL,
    color_lane INTEGER NOT NULL,
    color_lane_bright INTEGER NOT NULL,
    color_side INTEGER NOT NULL,
    color_wall_bright INTEGER NOT NULL,
    dispbp INTEGER NOT NULL,
    disp_player_lv INTEGER NOT NULL,
    disp_rating INTEGER NOT NULL,
    effect_damage INTEGER NOT NULL,
    effect_pos INTEGER NOT NULL,
    headphone INTEGER NOT NULL,
    judge_break INTEGER NOT NULL,
    judge_disp INTEGER NOT NULL,
    judge_hit INTEGER NOT NULL,
    judge_pos INTEGER NOT NULL,
    judge_timing INTEGER NOT NULL,
    judge_adjustment INTEGER,
    judge_critical_break INTEGER,
    matching INTEGER NOT NULL,
    mirror INTEGER NOT NULL,
    option_set INTEGER NOT NULL,
    speed INTEGER NOT NULL,
    stealth_field INTEGER NOT NULL,
    tap_sound INTEGER NOT NULL,
    platinum_break_disp INTEGER,
    vol_all INTEGER NOT NULL,
    vol_bell INTEGER NOT NULL,
    vol_cr_tap INTEGER NOT NULL,
    vol_damage INTEGER NOT NULL,
    vol_enemy INTEGER NOT NULL,
    vol_flick INTEGER NOT NULL,
    vol_guide INTEGER NOT NULL,
    vol_hold INTEGER NOT NULL,
    vol_side INTEGER NOT NULL,
    vol_skill INTEGER NOT NULL,
    vol_tap INTEGER NOT NULL,
    user_id BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE UNIQUE ON CONFLICT REPLACE,
    PRIMARY KEY (id)
);

INSERT INTO ongeki_user_option SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_playlog
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_playlog;
DROP TABLE ongeki_user_playlog;

CREATE TABLE ongeki_user_playlog (
    id                        INTEGER,
    battle_point              INTEGER       NOT NULL,
    battle_score              INTEGER       NOT NULL,
    battle_score_rank         INTEGER       NOT NULL,
    bell_count                INTEGER       NOT NULL,
    boss_attribute            INTEGER       NOT NULL,
    boss_chara_id             INTEGER       NOT NULL,
    boss_level                INTEGER       NOT NULL,
    card_attack1              INTEGER       NOT NULL,
    card_attack2              INTEGER       NOT NULL,
    card_attack3              INTEGER       NOT NULL,
    card_id1                  INTEGER       NOT NULL,
    card_id2                  INTEGER       NOT NULL,
    card_id3                  INTEGER       NOT NULL,
    card_level1               INTEGER       NOT NULL,
    card_level2               INTEGER       NOT NULL,
    card_level3               INTEGER       NOT NULL,
    clear_status              INTEGER       NOT NULL,
    damage_count              INTEGER       NOT NULL,
    event_id                  INTEGER       NOT NULL,
    event_name                VARCHAR (255),
    event_point               INTEGER       NOT NULL,
    is_all_break              BOOLEAN       NOT NULL,
    is_battle_new_record      BOOLEAN       NOT NULL,
    is_full_bell              BOOLEAN       NOT NULL,
    is_full_combo             BOOLEAN       NOT NULL,
    is_over_damage_new_record BOOLEAN       NOT NULL,
    is_tech_new_record        BOOLEAN       NOT NULL,
    judge_break               INTEGER       NOT NULL,
    judge_critical_break      INTEGER       NOT NULL,
    judge_hit                 INTEGER       NOT NULL,
    judge_miss                INTEGER       NOT NULL,
    level                     INTEGER       NOT NULL,
    max_combo                 INTEGER       NOT NULL,
    music_id                  INTEGER       NOT NULL,
    over_damage               INTEGER       NOT NULL,
    place_id                  INTEGER       NOT NULL,
    place_name                VARCHAR (255),
    play_date                 VARCHAR (255),
    play_kind                 INTEGER       NOT NULL,
    played_music_level1       INTEGER       NOT NULL,
    played_music_level2       INTEGER       NOT NULL,
    played_music_level3       INTEGER       NOT NULL,
    played_user_id1           INTEGER       NOT NULL,
    played_user_id2           INTEGER       NOT NULL,
    played_user_id3           INTEGER       NOT NULL,
    played_user_name1         VARCHAR (255),
    played_user_name2         VARCHAR (255),
    played_user_name3         VARCHAR (255),
    player_rating             INTEGER       NOT NULL,
    rate_flick                INTEGER       NOT NULL,
    rate_hold                 INTEGER       NOT NULL,
    rate_side_hold            INTEGER       NOT NULL,
    rate_side_tap             INTEGER       NOT NULL,
    rate_tap                  INTEGER       NOT NULL,
    sort_number               INTEGER       NOT NULL,
    tech_score                INTEGER       NOT NULL,
    tech_score_rank           INTEGER       NOT NULL,
    total_bell_count          INTEGER       NOT NULL,
    user_play_date            VARCHAR (255),
    user_id                   BIGINT        REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

INSERT INTO ongeki_user_playlog SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_scenario
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_scenario;
DROP TABLE ongeki_user_scenario;

CREATE TABLE ongeki_user_scenario (
    id          INTEGER,
    scenario_id INTEGER NOT NULL,
    play_count  INTEGER NOT NULL,
    user_id     BIGINT  REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_scenario_uq UNIQUE (
        scenario_id,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_scenario SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_story
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_story;
DROP TABLE ongeki_user_story;

CREATE TABLE ongeki_user_story (
    id                       INTEGER,
    last_chapter_id          INTEGER NOT NULL,
    story_id                 INTEGER NOT NULL,
    jewel_count              INTEGER,
    last_play_music_id       INTEGER,
    last_play_music_category INTEGER,
    last_play_music_level    INTEGER,
    user_id                  BIGINT  REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_story_uq UNIQUE (
        story_id,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_story SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_tech_count
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_tech_count;
DROP TABLE ongeki_user_tech_count;

CREATE TABLE ongeki_user_tech_count (
    id                   INTEGER,
    level_id             INTEGER NOT NULL,
    all_break_count      INTEGER NOT NULL,
    all_break_plus_count INTEGER NOT NULL,
    user_id              BIGINT  REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_tech_count_uq UNIQUE (
        level_id,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_tech_count SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_tech_event
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_tech_event;
DROP TABLE ongeki_user_tech_event;

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

INSERT INTO ongeki_user_tech_event SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_trade_item
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_trade_item;
DROP TABLE ongeki_user_trade_item;

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

INSERT INTO ongeki_user_trade_item SELECT * FROM temp;
DROP TABLE temp;

-- ongeki_user_training_room
CREATE TEMPORARY TABLE temp AS SELECT * FROM ongeki_user_training_room;
DROP TABLE ongeki_user_training_room;

CREATE TABLE ongeki_user_training_room (
    id         INTEGER,
    auth_key   VARCHAR (255),
    card_id    INTEGER       NOT NULL,
    room_id    INTEGER       NOT NULL,
    value_date VARCHAR (255),
    user_id    BIGINT        REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT ongeki_user_training_room_uq UNIQUE (
        card_id,
        room_id,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_training_room SELECT * FROM temp;
DROP TABLE temp;

-- Drop previous backup tables, it's better to not keep since no use for ordinary users
DROP TABLE bak2_ongeki_user_login_bonus;
DROP TABLE bak2_ongeki_user_option;
DROP TABLE bak2_ongeki_user_story;
DROP TABLE bak3_ongeki_user_option;
DROP TABLE bak4_ongeki_user_option;
DROP TABLE bak_chuni_user_music_detail;
DROP TABLE bak_diva_pv_entry;
DROP TABLE bak_maimai2_user_option;
DROP TABLE bak_maimai2_user_rate;
DROP TABLE bak_maimai2_user_udemae;
DROP TABLE bak_ongeki_user_activity;
DROP TABLE bak_ongeki_user_boss;
DROP TABLE bak_ongeki_user_card;
DROP TABLE bak_ongeki_user_chapter;
DROP TABLE bak_ongeki_user_character;
DROP TABLE bak_ongeki_user_deck;
DROP TABLE bak_ongeki_user_event_music;
DROP TABLE bak_ongeki_user_event_point;
DROP TABLE bak_ongeki_user_general_data;
DROP TABLE bak_ongeki_user_item;
DROP TABLE bak_ongeki_user_kop;
DROP TABLE bak_ongeki_user_login_bonus;
DROP TABLE bak_ongeki_user_mission_point;
DROP TABLE bak_ongeki_user_music_detail;
DROP TABLE bak_ongeki_user_music_item;
DROP TABLE bak_ongeki_user_option;
DROP TABLE bak_ongeki_user_playlog;
DROP TABLE bak_ongeki_user_scenario;
DROP TABLE bak_ongeki_user_story;
DROP TABLE bak_ongeki_user_tech_count;
DROP TABLE bak_ongeki_user_tech_event;
DROP TABLE bak_ongeki_user_trade_item;
DROP TABLE bak_ongeki_user_training_room;
DROP TABLE bak_diva_player_profile;
DROP TABLE bak_maimai2_user_detail;
DROP TABLE bak_ongeki_user_data;
DROP TABLE bak2_ongeki_user_data;