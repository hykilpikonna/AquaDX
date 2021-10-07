CREATE TABLE new_ongeki_user_activity (
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

INSERT INTO new_ongeki_user_activity
(id, activity_id, kind, param1, param2, param3, param4, sort_number, user_id)
SELECT id, activity_id, kind, param1, param2, param3, param4, sort_number, user_id FROM ongeki_user_activity;

ALTER TABLE ongeki_user_activity RENAME TO bak_ongeki_user_activity;
ALTER TABLE new_ongeki_user_activity RENAME TO ongeki_user_activity;

CREATE TABLE new_ongeki_user_card (
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

INSERT INTO new_ongeki_user_card
(id, analog_stock, card_id, cho_kaika_date, created, digital_stock, "exp", is_acquired, is_new, kaika_date, "level", max_level, print_count, skill_id, use_count, user_id)
SELECT id, analog_stock, card_id, cho_kaika_date, created, digital_stock, "exp", is_acquired, is_new, kaika_date, "level", max_level, print_count, skill_id, use_count, user_id FROM ongeki_user_card;

ALTER TABLE ongeki_user_card RENAME TO bak_ongeki_user_card;
ALTER TABLE new_ongeki_user_card RENAME TO ongeki_user_card;

CREATE TABLE new_ongeki_user_deck (
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

INSERT INTO new_ongeki_user_deck
(id, card_id1, card_id2, card_id3, deck_id, user_id)
SELECT id, card_id1, card_id2, card_id3, deck_id, user_id FROM ongeki_user_deck;

ALTER TABLE ongeki_user_deck RENAME TO bak_ongeki_user_deck;
ALTER TABLE new_ongeki_user_deck RENAME TO ongeki_user_deck;

CREATE TABLE new_ongeki_user_event_music (
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

INSERT INTO new_ongeki_user_event_music
(id, event_id, "type", music_id, "level", tech_score_max, platinum_score_max, tech_record_date, is_tech_new_record, user_id)
SELECT id, event_id, "type", music_id, "level", tech_score_max, platinum_score_max, tech_record_date, is_tech_new_record, user_id FROM ongeki_user_event_music;

ALTER TABLE ongeki_user_event_music RENAME TO bak_ongeki_user_event_music;
ALTER TABLE new_ongeki_user_event_music RENAME TO ongeki_user_event_music;

CREATE TABLE new_ongeki_user_event_point (
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

INSERT INTO new_ongeki_user_event_point
(id, event_id, is_ranking_rewarded, point, user_id)
SELECT id, event_id, is_ranking_rewarded, point, user_id FROM ongeki_user_event_point;

ALTER TABLE ongeki_user_event_point RENAME TO bak_ongeki_user_event_point;
ALTER TABLE new_ongeki_user_event_point RENAME TO ongeki_user_event_point;

CREATE TABLE new_ongeki_user_general_data (
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

INSERT INTO new_ongeki_user_general_data
(id, property_key, property_value, user_id)
SELECT id, property_key, property_value, user_id FROM ongeki_user_general_data;

ALTER TABLE ongeki_user_general_data RENAME TO bak_ongeki_user_general_data;
ALTER TABLE new_ongeki_user_general_data RENAME TO ongeki_user_general_data;

CREATE TABLE new_ongeki_user_item (
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

INSERT INTO new_ongeki_user_item
(id, is_valid, item_id, item_kind, stock, user_id)
SELECT id, is_valid, item_id, item_kind, stock, user_id FROM ongeki_user_item;

ALTER TABLE ongeki_user_item RENAME TO bak_ongeki_user_item;
ALTER TABLE new_ongeki_user_item RENAME TO ongeki_user_item;

CREATE TABLE new_ongeki_user_kop (
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

INSERT INTO new_ongeki_user_kop
(id, auth_key, kop_id, area_id, total_tech_score, total_platinum_score, tech_record_date, is_total_tech_new_record, user_id)
SELECT id, auth_key, kop_id, area_id, total_tech_score, total_platinum_score, tech_record_date, is_total_tech_new_record, user_id FROM ongeki_user_kop;

ALTER TABLE ongeki_user_kop RENAME TO bak_ongeki_user_kop;
ALTER TABLE new_ongeki_user_kop RENAME TO ongeki_user_kop;

CREATE TABLE new_ongeki_user_login_bonus (
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

INSERT INTO new_ongeki_user_login_bonus
(id, bonus_id, bonus_count, last_update_date, user_id)
SELECT id, bonus_id, bonus_count, last_update_date, user_id FROM ongeki_user_login_bonus;

ALTER TABLE ongeki_user_login_bonus RENAME TO bak2_ongeki_user_login_bonus;
ALTER TABLE new_ongeki_user_login_bonus RENAME TO ongeki_user_login_bonus;

CREATE TABLE new_ongeki_user_mission_point (
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

INSERT INTO new_ongeki_user_mission_point
(id, event_id, point, user_id)
SELECT id, event_id, point, user_id FROM ongeki_user_mission_point;


ALTER TABLE ongeki_user_mission_point RENAME TO bak_ongeki_user_mission_point;
ALTER TABLE new_ongeki_user_mission_point RENAME TO ongeki_user_mission_point;

CREATE TABLE new_ongeki_user_music_detail (
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

INSERT INTO new_ongeki_user_music_detail
(id, battle_score_max, battle_score_rank, clear_status, is_all_breake, is_full_bell, is_full_combo, is_lock, is_story_watched, "level", max_combo_count, max_over_kill, max_team_over_kill, music_id, play_count, tech_score_max, tech_score_rank, user_id)
SELECT id, battle_score_max, battle_score_rank, clear_status, is_all_breake, is_full_bell, is_full_combo, is_lock, is_story_watched, "level", max_combo_count, max_over_kill, max_team_over_kill, music_id, play_count, tech_score_max, tech_score_rank, user_id FROM ongeki_user_music_detail;

ALTER TABLE ongeki_user_music_detail RENAME TO bak_ongeki_user_music_detail;
ALTER TABLE new_ongeki_user_music_detail RENAME TO ongeki_user_music_detail;

CREATE TABLE new_ongeki_user_music_item (
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

INSERT INTO new_ongeki_user_music_item
(id, music_id, status, user_id)
SELECT id, music_id, status, user_id FROM ongeki_user_music_item;

ALTER TABLE ongeki_user_music_item RENAME TO bak_ongeki_user_music_item;
ALTER TABLE new_ongeki_user_music_item RENAME TO ongeki_user_music_item;

CREATE TABLE new_ongeki_user_option (
    id                   INTEGER,
    [abort]              INTEGER NOT NULL,
    color_field          INTEGER NOT NULL,
    color_lane           INTEGER NOT NULL,
    color_lane_bright    INTEGER NOT NULL,
    color_side           INTEGER NOT NULL,
    dispbp               INTEGER NOT NULL,
    disp_player_lv       INTEGER NOT NULL,
    disp_rating          INTEGER NOT NULL,
    effect_damage        INTEGER NOT NULL,
    effect_pos           INTEGER NOT NULL,
    headphone            INTEGER NOT NULL,
    judge_break          INTEGER NOT NULL,
    judge_disp           INTEGER NOT NULL,
    judge_hit            INTEGER NOT NULL,
    judge_pos            INTEGER NOT NULL,
    judge_timing         INTEGER NOT NULL,
    judge_adjustment     INTEGER,
    judge_critical_break INTEGER,
    matching             INTEGER NOT NULL,
    mirror               INTEGER NOT NULL,
    option_set           INTEGER NOT NULL,
    speed                INTEGER NOT NULL,
    tap_sound            INTEGER NOT NULL,
    platinum_break_disp  INTEGER,
    vol_all              INTEGER NOT NULL,
    vol_bell             INTEGER NOT NULL,
    vol_cr_tap           INTEGER NOT NULL,
    vol_damage           INTEGER NOT NULL,
    vol_enemy            INTEGER NOT NULL,
    vol_flick            INTEGER NOT NULL,
    vol_guide            INTEGER NOT NULL,
    vol_hold             INTEGER NOT NULL,
    vol_side             INTEGER NOT NULL,
    vol_skill            INTEGER NOT NULL,
    vol_tap              INTEGER NOT NULL,
    user_id              BIGINT  REFERENCES ongeki_user_data (id) ON DELETE CASCADE
                                 UNIQUE ON CONFLICT REPLACE,
    PRIMARY KEY (
        id
    )
);

INSERT INTO new_ongeki_user_option
(id, "abort", color_field, color_lane, color_lane_bright, color_side, dispbp, disp_player_lv, disp_rating, effect_damage, effect_pos, headphone, judge_break, judge_disp, judge_hit, judge_pos, judge_timing, judge_adjustment, judge_critical_break, matching, mirror, option_set, speed, tap_sound, platinum_break_disp, vol_all, vol_bell, vol_cr_tap, vol_damage, vol_enemy, vol_flick, vol_guide, vol_hold, vol_side, vol_skill, vol_tap, user_id)
SELECT id, "abort", color_field, color_lane, color_lane_bright, color_side, dispbp, disp_player_lv, disp_rating, effect_damage, effect_pos, headphone, judge_break, judge_disp, judge_hit, judge_pos, judge_timing, judge_adjustment, judge_critical_break, matching, mirror, option_set, speed, tap_sound, platinum_break_disp, vol_all, vol_bell, vol_cr_tap, vol_damage, vol_enemy, vol_flick, vol_guide, vol_hold, vol_side, vol_skill, vol_tap, user_id FROM ongeki_user_option;


ALTER TABLE ongeki_user_option RENAME TO bak3_ongeki_user_option;
ALTER TABLE new_ongeki_user_option RENAME TO ongeki_user_option;

CREATE TABLE new_ongeki_user_playlog (
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

INSERT INTO new_ongeki_user_playlog
(id, battle_point, battle_score, battle_score_rank, bell_count, boss_attribute, boss_chara_id, boss_level, card_attack1, card_attack2, card_attack3, card_id1, card_id2, card_id3, card_level1, card_level2, card_level3, clear_status, damage_count, event_id, event_name, event_point, is_all_break, is_battle_new_record, is_full_bell, is_full_combo, is_over_damage_new_record, is_tech_new_record, judge_break, judge_critical_break, judge_hit, judge_miss, "level", max_combo, music_id, over_damage, place_id, place_name, play_date, play_kind, played_music_level1, played_music_level2, played_music_level3, played_user_id1, played_user_id2, played_user_id3, played_user_name1, played_user_name2, played_user_name3, player_rating, rate_flick, rate_hold, rate_side_hold, rate_side_tap, rate_tap, sort_number, tech_score, tech_score_rank, total_bell_count, user_play_date, user_id)
SELECT id, battle_point, battle_score, battle_score_rank, bell_count, boss_attribute, boss_chara_id, boss_level, card_attack1, card_attack2, card_attack3, card_id1, card_id2, card_id3, card_level1, card_level2, card_level3, clear_status, damage_count, event_id, event_name, event_point, is_all_break, is_battle_new_record, is_full_bell, is_full_combo, is_over_damage_new_record, is_tech_new_record, judge_break, judge_critical_break, judge_hit, judge_miss, "level", max_combo, music_id, over_damage, place_id, place_name, play_date, play_kind, played_music_level1, played_music_level2, played_music_level3, played_user_id1, played_user_id2, played_user_id3, played_user_name1, played_user_name2, played_user_name3, player_rating, rate_flick, rate_hold, rate_side_hold, rate_side_tap, rate_tap, sort_number, tech_score, tech_score_rank, total_bell_count, user_play_date, user_id FROM ongeki_user_playlog;

ALTER TABLE ongeki_user_playlog RENAME TO bak_ongeki_user_playlog;
ALTER TABLE new_ongeki_user_playlog RENAME TO ongeki_user_playlog;

CREATE TABLE new_ongeki_user_scenario (
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

INSERT INTO new_ongeki_user_scenario
(id, scenario_id, play_count, user_id)
SELECT id, scenario_id, play_count, user_id FROM ongeki_user_scenario;

ALTER TABLE ongeki_user_scenario RENAME TO bak_ongeki_user_scenario;
ALTER TABLE new_ongeki_user_scenario RENAME TO ongeki_user_scenario;

CREATE TABLE new_ongeki_user_story (
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

INSERT INTO new_ongeki_user_story
(id, last_chapter_id, story_id, jewel_count, last_play_music_id, last_play_music_category, last_play_music_level, user_id)
SELECT id, last_chapter_id, story_id, jewel_count, last_play_music_id, last_play_music_category, last_play_music_level, user_id FROM ongeki_user_story;

ALTER TABLE ongeki_user_story RENAME TO bak2_ongeki_user_story;
ALTER TABLE new_ongeki_user_story RENAME TO ongeki_user_story;

CREATE TABLE new_ongeki_user_tech_count (
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

INSERT INTO new_ongeki_user_tech_count
(id, level_id, all_break_count, all_break_plus_count, user_id)
SELECT id, level_id, all_break_count, all_break_plus_count, user_id FROM ongeki_user_tech_count;

ALTER TABLE ongeki_user_tech_count RENAME TO bak_ongeki_user_tech_count;
ALTER TABLE new_ongeki_user_tech_count RENAME TO ongeki_user_tech_count;

CREATE TABLE new_ongeki_user_tech_event (
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

INSERT INTO new_ongeki_user_tech_event
(id, event_id, total_tech_score, total_platinum_score, tech_record_date, is_ranking_rewarded, is_total_tech_new_record, user_id)
SELECT id, event_id, total_tech_score, total_platinum_score, tech_record_date, is_ranking_rewarded, is_total_tech_new_record, user_id FROM ongeki_user_tech_event;

ALTER TABLE ongeki_user_tech_event RENAME TO bak_ongeki_user_tech_event;
ALTER TABLE new_ongeki_user_tech_event RENAME TO ongeki_user_tech_event;

CREATE TABLE new_ongeki_user_trade_item (
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

INSERT INTO new_ongeki_user_trade_item
(id, chapter_id, trade_item_id, trade_count, user_id)
SELECT id, chapter_id, trade_item_id, trade_count, user_id FROM ongeki_user_trade_item;

ALTER TABLE ongeki_user_trade_item RENAME TO bak_ongeki_user_trade_item;
ALTER TABLE new_ongeki_user_trade_item RENAME TO ongeki_user_trade_item;

CREATE TABLE new_ongeki_user_training_room (
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

INSERT INTO new_ongeki_user_training_room
(id, auth_key, card_id, room_id, value_date, user_id)
SELECT id, auth_key, card_id, room_id, value_date, user_id FROM ongeki_user_training_room;

ALTER TABLE ongeki_user_training_room RENAME TO bak_ongeki_user_training_room;
ALTER TABLE new_ongeki_user_training_room RENAME TO ongeki_user_training_room;
