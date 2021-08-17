-- Ongeki user option: platinum_break_disp, judge_critical_break, judge_adjustment (nullable)

CREATE TABLE "ongeki_user_option_new"
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
    judge_adjustment  INTEGER,
    judge_critical_break INTEGER,
    matching          INTEGER NOT NULL,
    mirror            INTEGER NOT NULL,
    option_set        INTEGER NOT NULL,
    speed             INTEGER NOT NULL,
    tap_sound         INTEGER NOT NULL,
    platinum_break_disp INTEGER,
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

INSERT INTO ongeki_user_option_new (
    id,
    "abort",
    color_field,
    color_lane,
    color_lane_bright,
    color_side,
    dispbp,
    disp_player_lv,
    disp_rating,
    effect_damage,
    effect_pos,
    headphone,
    judge_break,
    judge_disp,
    judge_hit,
    judge_pos,
    judge_timing,
    matching,
    mirror,
    option_set,
    speed,
    tap_sound,
    vol_all,
    vol_bell,
    vol_cr_tap,
    vol_damage,
    vol_enemy,
    vol_flick,
    vol_guide,
    vol_hold,
    vol_side,
    vol_skill,
    vol_tap,
    user_id,
    judge_adjustment,
    platinum_break_disp,
    judge_critical_break
)
SELECT id,
       "abort",
       color_field,
       color_lane,
       color_lane_bright,
       color_side,
       dispbp,
       disp_player_lv,
       disp_rating,
       effect_damage,
       effect_pos,
       headphone,
       judge_break,
       judge_disp,
       judge_hit,
       judge_pos,
       judge_timing,
       matching,
       mirror,
       option_set,
       speed,
       tap_sound,
       vol_all,
       vol_bell,
       vol_cr_tap,
       vol_damage,
       vol_enemy,
       vol_flick,
       vol_guide,
       vol_hold,
       vol_side,
       vol_skill,
       vol_tap,
       user_id,
       judge_adjustment,
       1,
       0
FROM ongeki_user_option;

ALTER TABLE ongeki_user_option RENAME TO bak2_ongeki_user_option;
ALTER TABLE ongeki_user_option_new RENAME TO ongeki_user_option;

-- Ongeki user login bonus: last_update_date

CREATE TABLE ongeki_user_login_bonus_new
(
    id          INTEGER,
    bonus_id    INTEGER NOT NULL,
    bonus_count INTEGER NOT NULL,
    last_update_date VARCHAR(255),
    user_id     BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_login_bonus_uq UNIQUE (
                                                  bonus_id,
                                                  user_id
        ) ON CONFLICT REPLACE
);

INSERT INTO ongeki_user_login_bonus_new
(
    id,
    bonus_id,
    bonus_count,
    user_id,
    last_update_date
)
SELECT id,
       bonus_id,
       bonus_count,
       user_id,
       "2000-01-01 05:00:00.0"
FROM ongeki_user_login_bonus;

ALTER TABLE ongeki_user_login_bonus RENAME TO bak_ongeki_user_login_bonus;
ALTER TABLE ongeki_user_login_bonus_new RENAME TO ongeki_user_login_bonus;

-- Ongeki user data: medal_count, cm_event_watched_date

CREATE TABLE ongeki_user_data_new
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
    medal_count                    INTEGER,
    cm_event_watched_date          VARCHAR(255),
    user_name                      VARCHAR(255),
    aime_card_id                   BIGINT,
    PRIMARY KEY (
                 id
        )
);

INSERT INTO ongeki_user_data_new
(id, battle_point, card_category_setting, card_id, card_sort_setting, character_id, compatible_cm_version, event_watched_date, "exp", first_data_version, first_game_id, first_play_date, first_rom_version, first_tutorial_cancel_num, highest_rating, jewel_count, last_all_net_id, last_client_id, last_data_version, last_game_id, last_place_id, last_place_name, last_play_date, last_play_music_level, last_region_id, last_region_name, last_rom_version, last_used_deck_id, "level", nameplate_id, play_count, played_tutorial_bit, player_rating, point, reincarnation_num, sum_battle_advanced_high_score, sum_battle_basic_high_score, sum_battle_expert_high_score, sum_battle_high_score, sum_battle_lunatic_high_score, sum_battle_master_high_score, sum_tech_advanced_high_score, sum_tech_basic_high_score, sum_tech_expert_high_score, sum_tech_high_score, sum_tech_lunatic_high_score, sum_tech_master_high_score, tab_setting, tab_sort_setting, total_jewel_count, total_point, trophy_id, user_name, aime_card_id, medal_count, cm_event_watched_date)
SELECT id, battle_point, card_category_setting, card_id, card_sort_setting, character_id, compatible_cm_version, event_watched_date, "exp", first_data_version, first_game_id, first_play_date, first_rom_version, first_tutorial_cancel_num, highest_rating, jewel_count, last_all_net_id, last_client_id, last_data_version, last_game_id, last_place_id, last_place_name, last_play_date, last_play_music_level, last_region_id, last_region_name, last_rom_version, last_used_deck_id, "level", nameplate_id, play_count, played_tutorial_bit, player_rating, point, reincarnation_num, sum_battle_advanced_high_score, sum_battle_basic_high_score, sum_battle_expert_high_score, sum_battle_high_score, sum_battle_lunatic_high_score, sum_battle_master_high_score, sum_tech_advanced_high_score, sum_tech_basic_high_score, sum_tech_expert_high_score, sum_tech_high_score, sum_tech_lunatic_high_score, sum_tech_master_high_score, tab_setting, tab_sort_setting, total_jewel_count, total_point, trophy_id, user_name, aime_card_id, 0, "2000-01-01 05:00:00.0"
FROM ongeki_user_data;

ALTER TABLE ongeki_user_data RENAME TO bak_ongeki_user_data;
ALTER TABLE ongeki_user_data_new RENAME TO ongeki_user_data;

-- Ongeki user character: costume_id, attachment_id

CREATE TABLE ongeki_user_character_new
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

INSERT INTO ongeki_user_character_new
(id, character_id, intimate_count, intimate_count_date, intimate_count_rewarded, intimate_level, is_new, play_count, user_id, costume_id, attachment_id)
SELECT id, character_id, intimate_count, intimate_count_date, intimate_count_rewarded, intimate_level, is_new, play_count, user_id, 0, 0
FROM ongeki_user_character;

ALTER TABLE ongeki_user_character RENAME TO bak_ongeki_user_character;
ALTER TABLE ongeki_user_character_new RENAME TO ongeki_user_character;

-- Ongeki user chapter: last_play_music_level

CREATE TABLE ongeki_user_chapter_new
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

INSERT INTO ongeki_user_chapter_new
(id, chapter_id, is_clear, is_story_watched, jewel_count, last_play_music_category, last_play_music_id, skip_timing1, skip_timing2, user_id, last_play_music_level)
SELECT id, chapter_id, is_clear, is_story_watched, jewel_count, last_play_music_category, last_play_music_id, skip_timing1, skip_timing2, user_id, 0
FROM ongeki_user_chapter;

ALTER TABLE ongeki_user_chapter RENAME TO bak_ongeki_user_chapter;
ALTER TABLE ongeki_user_chapter_new RENAME TO ongeki_user_chapter;

-- Ongeki user boss: event_id

CREATE TABLE ongeki_user_boss_new
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

INSERT INTO ongeki_user_boss_new
(id, music_id, damage, is_clear, user_id, event_id)
SELECT id, music_id, damage, is_clear, user_id, 0
FROM ongeki_user_boss;

ALTER TABLE ongeki_user_boss RENAME TO bak_ongeki_user_boss;
ALTER TABLE ongeki_user_boss_new RENAME TO ongeki_user_boss;