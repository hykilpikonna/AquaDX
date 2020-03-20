-- Table: ongeki_user_data
CREATE TABLE ongeki_user_data
(
    id                             INTEGER,
    battle_point                   INTEGER NOT NULL,
    card_category_setting          INTEGER NOT NULL,
    card_id                        INTEGER NOT NULL,
    card_sort_setting              INTEGER NOT NULL,
    character_id                   INTEGER NOT NULL,
    compatible_cm_version          VARCHAR(255),
    event_watched_date             VARCHAR(255),
    exp                            BIGINT  NOT NULL,
    first_data_version             VARCHAR(255),
    first_game_id                  VARCHAR(255),
    first_play_date                VARCHAR(255),
    first_rom_version              VARCHAR(255),
    first_tutorial_cancel_num      INTEGER NOT NULL,
    highest_rating                 INTEGER NOT NULL,
    jewel_count                    INTEGER NOT NULL,
    last_all_net_id                INTEGER NOT NULL,
    last_client_id                 VARCHAR(255),
    last_data_version              VARCHAR(255),
    last_game_id                   VARCHAR(255),
    last_place_id                  INTEGER NOT NULL,
    last_place_name                VARCHAR(255),
    last_play_date                 VARCHAR(255),
    last_play_music_level          INTEGER NOT NULL,
    last_region_id                 INTEGER NOT NULL,
    last_region_name               VARCHAR(255),
    last_rom_version               VARCHAR(255),
    last_used_deck_id              INTEGER NOT NULL,
    level                          INTEGER NOT NULL,
    nameplate_id                   INTEGER NOT NULL,
    play_count                     INTEGER NOT NULL,
    played_tutorial_bit            INTEGER NOT NULL,
    player_rating                  INTEGER NOT NULL,
    point                          BIGINT  NOT NULL,
    reincarnation_num              INTEGER NOT NULL,
    sum_battle_advanced_high_score BIGINT  NOT NULL,
    sum_battle_basic_high_score    BIGINT  NOT NULL,
    sum_battle_expert_high_score   BIGINT  NOT NULL,
    sum_battle_high_score          BIGINT  NOT NULL,
    sum_battle_lunatic_high_score  BIGINT  NOT NULL,
    sum_battle_master_high_score   BIGINT  NOT NULL,
    sum_tech_advanced_high_score   BIGINT  NOT NULL,
    sum_tech_basic_high_score      BIGINT  NOT NULL,
    sum_tech_expert_high_score     BIGINT  NOT NULL,
    sum_tech_high_score            BIGINT  NOT NULL,
    sum_tech_lunatic_high_score    BIGINT  NOT NULL,
    sum_tech_master_high_score     BIGINT  NOT NULL,
    tab_setting                    INTEGER NOT NULL,
    tab_sort_setting               INTEGER NOT NULL,
    total_jewel_count              INTEGER NOT NULL,
    total_point                    BIGINT  NOT NULL,
    trophy_id                      INTEGER NOT NULL,
    user_name                      VARCHAR(255),
    aime_card_id                   BIGINT,
    PRIMARY KEY (
                 id
        )
);


-- Table: ongeki_user_activity
CREATE TABLE ongeki_user_activity
(
    id          INTEGER,
    activity_id INTEGER,
    kind        INTEGER NOT NULL,
    param1      INTEGER NOT NULL,
    param2      INTEGER NOT NULL,
    param3      INTEGER NOT NULL,
    param4      INTEGER NOT NULL,
    sort_number INTEGER NOT NULL,
    user_id     BIGINT,
    "" REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_activity_uq UNIQUE (
                                               activity_id,
                                               kind,
                                               user_id
        ) ON CONFLICT REPLACE
);


-- Table: ongeki_user_card
CREATE TABLE ongeki_user_card
(
    id             INTEGER,
    analog_stock   INTEGER NOT NULL,
    card_id        INTEGER NOT NULL,
    cho_kaika_date VARCHAR(255),
    created        VARCHAR(255),
    digital_stock  INTEGER NOT NULL,
    exp            INTEGER NOT NULL,
    is_acquired    BOOLEAN NOT NULL,
    is_new         BOOLEAN NOT NULL,
    kaika_date     VARCHAR(255),
    level          INTEGER NOT NULL,
    max_level      INTEGER NOT NULL,
    print_count    INTEGER NOT NULL,
    skill_id       INTEGER NOT NULL,
    use_count      INTEGER NOT NULL,
    user_id        BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_card_uq UNIQUE (
                                           card_id,
                                           user_id
        ) ON CONFLICT REPLACE
);


-- Table: ongeki_user_chapter
CREATE TABLE ongeki_user_chapter
(
    id                       INTEGER,
    chapter_id               INTEGER NOT NULL,
    is_clear                 BOOLEAN NOT NULL,
    is_story_watched         BOOLEAN NOT NULL,
    jewel_count              INTEGER NOT NULL,
    last_play_music_category INTEGER NOT NULL,
    last_play_music_id       INTEGER NOT NULL,
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


-- Table: ongeki_user_character
CREATE TABLE ongeki_user_character
(
    id                      INTEGER,
    character_id            INTEGER NOT NULL,
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


-- Table: ongeki_user_deck
CREATE TABLE ongeki_user_deck
(
    id       INTEGER,
    card_id1 INTEGER NOT NULL,
    card_id2 INTEGER NOT NULL,
    card_id3 INTEGER NOT NULL,
    deck_id  INTEGER NOT NULL,
    user_id  BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_deck_uq UNIQUE (
                                           deck_id,
                                           user_id
        ) ON CONFLICT REPLACE
);


-- Table: ongeki_user_event_point
CREATE TABLE ongeki_user_event_point
(
    id                  INTEGER,
    event_id            INTEGER NOT NULL,
    is_ranking_rewarded BOOLEAN NOT NULL,
    point               BIGINT  NOT NULL,
    user_id             BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_event_point_uq UNIQUE (
                                                  event_id,
                                                  user_id
        ) ON CONFLICT REPLACE
);


-- Table: ongeki_user_item
CREATE TABLE ongeki_user_item
(
    id        INTEGER,
    is_valid  BOOLEAN NOT NULL,
    item_id   INTEGER NOT NULL,
    item_kind INTEGER NOT NULL,
    stock     INTEGER NOT NULL,
    user_id   BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_item_uq UNIQUE (
                                           item_id,
                                           item_kind,
                                           user_id
        ) ON CONFLICT REPLACE
);


-- Table: ongeki_user_music_detail
CREATE TABLE ongeki_user_music_detail
(
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
    user_id            BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_music_detail_uq UNIQUE (
                                                   level,
                                                   music_id,
                                                   user_id
        ) ON CONFLICT REPLACE
);


-- Table: ongeki_user_music_item
CREATE TABLE ongeki_user_music_item
(
    id       INTEGER,
    music_id INTEGER NOT NULL,
    status   INTEGER NOT NULL,
    user_id  BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_music_item_uq UNIQUE (
                                                 music_id,
                                                 user_id
        ) ON CONFLICT REPLACE
);


-- Table: ongeki_user_option
CREATE TABLE ongeki_user_option
(
    id                INTEGER,
    "abort"           INTEGER NOT NULL,
    color_field       INTEGER NOT NULL,
    color_lane        INTEGER NOT NULL,
    color_lane_bright INTEGER NOT NULL,
    color_side        INTEGER NOT NULL,
    dispbp            INTEGER NOT NULL,
    disp_player_lv    INTEGER NOT NULL,
    disp_rating       INTEGER NOT NULL,
    effect_damage     INTEGER NOT NULL,
    effect_pos        INTEGER NOT NULL,
    headphone         INTEGER NOT NULL,
    judge_break       INTEGER NOT NULL,
    judge_disp        INTEGER NOT NULL,
    judge_hit         INTEGER NOT NULL,
    judge_pos         INTEGER NOT NULL,
    judge_timing      INTEGER NOT NULL,
    matching          INTEGER NOT NULL,
    mirror            INTEGER NOT NULL,
    option_set        INTEGER NOT NULL,
    speed             INTEGER NOT NULL,
    tap_sound         INTEGER NOT NULL,
    vol_all           INTEGER NOT NULL,
    vol_bell          INTEGER NOT NULL,
    vol_cr_tap        INTEGER NOT NULL,
    vol_damage        INTEGER NOT NULL,
    vol_enemy         INTEGER NOT NULL,
    vol_flick         INTEGER NOT NULL,
    vol_guide         INTEGER NOT NULL,
    vol_hold          INTEGER NOT NULL,
    vol_side          INTEGER NOT NULL,
    vol_skill         INTEGER NOT NULL,
    vol_tap           INTEGER NOT NULL,
    user_id           BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE
        UNIQUE ON CONFLICT REPLACE,
    PRIMARY KEY (
                 id
        )
);


-- Table: ongeki_user_playlog
CREATE TABLE ongeki_user_playlog
(
    id                        INTEGER,
    battle_point              INTEGER NOT NULL,
    battle_score              INTEGER NOT NULL,
    battle_score_rank         INTEGER NOT NULL,
    bell_count                INTEGER NOT NULL,
    boss_attribute            INTEGER NOT NULL,
    boss_chara_id             INTEGER NOT NULL,
    boss_level                INTEGER NOT NULL,
    card_attack1              INTEGER NOT NULL,
    card_attack2              INTEGER NOT NULL,
    card_attack3              INTEGER NOT NULL,
    card_id1                  INTEGER NOT NULL,
    card_id2                  INTEGER NOT NULL,
    card_id3                  INTEGER NOT NULL,
    card_level1               INTEGER NOT NULL,
    card_level2               INTEGER NOT NULL,
    card_level3               INTEGER NOT NULL,
    clear_status              INTEGER NOT NULL,
    damage_count              INTEGER NOT NULL,
    event_id                  INTEGER NOT NULL,
    event_name                VARCHAR(255),
    event_point               INTEGER NOT NULL,
    is_all_break              BOOLEAN NOT NULL,
    is_battle_new_record      BOOLEAN NOT NULL,
    is_full_bell              BOOLEAN NOT NULL,
    is_full_combo             BOOLEAN NOT NULL,
    is_over_damage_new_record BOOLEAN NOT NULL,
    is_tech_new_record        BOOLEAN NOT NULL,
    judge_break               INTEGER NOT NULL,
    judge_critical_break      INTEGER NOT NULL,
    judge_hit                 INTEGER NOT NULL,
    judge_miss                INTEGER NOT NULL,
    level                     INTEGER NOT NULL,
    max_combo                 INTEGER NOT NULL,
    music_id                  INTEGER NOT NULL,
    over_damage               INTEGER NOT NULL,
    place_id                  INTEGER NOT NULL,
    place_name                VARCHAR(255),
    play_date                 VARCHAR(255),
    play_kind                 INTEGER NOT NULL,
    played_music_level1       INTEGER NOT NULL,
    played_music_level2       INTEGER NOT NULL,
    played_music_level3       INTEGER NOT NULL,
    played_user_id1           INTEGER NOT NULL,
    played_user_id2           INTEGER NOT NULL,
    played_user_id3           INTEGER NOT NULL,
    played_user_name1         VARCHAR(255),
    played_user_name2         VARCHAR(255),
    played_user_name3         VARCHAR(255),
    player_rating             INTEGER NOT NULL,
    rate_flick                INTEGER NOT NULL,
    rate_hold                 INTEGER NOT NULL,
    rate_side_hold            INTEGER NOT NULL,
    rate_side_tap             INTEGER NOT NULL,
    rate_tap                  INTEGER NOT NULL,
    sort_number               INTEGER NOT NULL,
    tech_score                INTEGER NOT NULL,
    tech_score_rank           INTEGER NOT NULL,
    total_bell_count          INTEGER NOT NULL,
    user_play_date            VARCHAR(255),
    user_id                   BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        )
);


-- Table: ongeki_user_story
CREATE TABLE ongeki_user_story
(
    id              INTEGER,
    last_chapter_id INTEGER NOT NULL,
    story_id        INTEGER NOT NULL,
    user_id         BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_story_uq UNIQUE (
                                            story_id,
                                            user_id
        ) ON CONFLICT REPLACE
);


-- Table: ongeki_user_training_room
CREATE TABLE ongeki_user_training_room
(
    id         INTEGER,
    auth_key   VARCHAR(255),
    card_id    INTEGER NOT NULL,
    room_id    INTEGER NOT NULL,
    value_date VARCHAR(255),
    user_id    BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_training_room_uq UNIQUE (
                                                    card_id,
                                                    room_id,
                                                    user_id
        ) ON CONFLICT REPLACE
);


-- Table: ongeki_user_login_bonus
CREATE TABLE ongeki_user_login_bonus
(
    id          INTEGER,
    bonus_id    INTEGER NOT NULL,
    bonus_count INTEGER NOT NULL,
    user_id     BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_login_bonus_uq UNIQUE (
                                                  bonus_id,
                                                  user_id
        ) ON CONFLICT REPLACE
);