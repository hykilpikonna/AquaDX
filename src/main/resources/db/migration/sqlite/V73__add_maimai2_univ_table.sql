-- Sqlite doesn't provide a way to edit FK so this mess always happens
-- maimai2_user_detail

CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_detail;
DROP TABLE maimai2_user_detail;

CREATE TABLE maimai2_user_detail (
    id                          INTEGER,
    aime_card_id                BIGINT        REFERENCES sega_card (id) ON DELETE CASCADE,
    user_name                   VARCHAR (255),
    is_net_member               INTEGER,
    icon_id                     INTEGER,
    plate_id                    INTEGER,
    title_id                    INTEGER,
    partner_id                  INTEGER,
    frame_id                    INTEGER,
    select_map_id               INTEGER,
    total_awake                 INTEGER,
    grade_rating                INTEGER,
    music_rating                INTEGER,
    player_rating               INTEGER,
    highest_rating              INTEGER,
    grade_rank                  INTEGER,
    class_rank                  INTEGER,
    course_rank                 INTEGER,
    chara_slot                  VARCHAR (255),
    chara_lock_slot             VARCHAR (255),
    content_bit                 BIGINT,
    play_count                  INTEGER,
    event_watched_date          VARCHAR (255),
    last_game_id                VARCHAR (255),
    last_rom_version            VARCHAR (255),
    last_data_version           VARCHAR (255),
    last_login_date             VARCHAR (255),
    last_play_date              VARCHAR (255),
    last_play_credit            INTEGER,
    last_play_mode              INTEGER,
    last_place_id               INTEGER,
    last_place_name             VARCHAR (255),
    last_all_net_id             INTEGER,
    last_region_id              INTEGER,
    last_region_name            VARCHAR (255),
    last_client_id              VARCHAR (255),
    last_country_code           VARCHAR (255),
    last_selectemoney           INTEGER,
    last_select_ticket          INTEGER,
    last_select_course          INTEGER,
    last_count_course           INTEGER,
    first_game_id               VARCHAR (255),
    first_rom_version           VARCHAR (255),
    first_data_version          VARCHAR (255),
    first_play_date             VARCHAR (255),
    compatible_cm_version       VARCHAR (255),
    daily_bonus_date            VARCHAR (255),
    daily_course_bonus_date     VARCHAR (255),
    play_vs_count               INTEGER,
    play_sync_count             INTEGER,
    win_count                   INTEGER,
    help_count                  INTEGER,
    combo_count                 INTEGER,
    total_deluxscore            BIGINT,
    total_basic_deluxscore      BIGINT,
    total_advanced_deluxscore   BIGINT,
    total_expert_deluxscore     BIGINT,
    total_master_deluxscore     BIGINT,
    total_re_master_deluxscore  BIGINT,
    total_sync                  INTEGER,
    total_basic_sync            INTEGER,
    total_advanced_sync         INTEGER,
    total_expert_sync           INTEGER,
    total_master_sync           INTEGER,
    total_re_master_sync        INTEGER,
    total_achievement           BIGINT,
    total_basic_achievement     BIGINT,
    total_advanced_achievement  BIGINT,
    total_expert_achievement    BIGINT,
    total_master_achievement    BIGINT,
    total_re_master_achievement BIGINT,
    date_time                   BIGINT,
    player_old_rating           BIGINT,
    player_new_rating           BIGINT,
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_detail
(id, aime_card_id, user_name, is_net_member, icon_id, plate_id, title_id, partner_id, frame_id, select_map_id, total_awake, grade_rating, music_rating, player_rating, highest_rating, grade_rank, class_rank, course_rank, chara_slot, chara_lock_slot, content_bit, play_count, event_watched_date, last_game_id, last_rom_version, last_data_version, last_login_date, last_play_date, last_play_credit, last_play_mode, last_place_id, last_place_name, last_all_net_id, last_region_id, last_region_name, last_client_id, last_country_code, last_selectemoney, last_select_ticket, last_select_course, last_count_course, first_game_id, first_rom_version, first_data_version, first_play_date, compatible_cm_version, daily_bonus_date, daily_course_bonus_date, play_vs_count, play_sync_count, win_count, help_count, combo_count, total_deluxscore, total_basic_deluxscore, total_advanced_deluxscore, total_expert_deluxscore, total_master_deluxscore, total_re_master_deluxscore, total_sync, total_basic_sync, total_advanced_sync, total_expert_sync, total_master_sync, total_re_master_sync, total_achievement, total_basic_achievement, total_advanced_achievement, total_expert_achievement, total_master_achievement, total_re_master_achievement, date_time, player_old_rating, player_new_rating)
SELECT id, aime_card_id, user_name, is_net_member, icon_id, plate_id, title_id, partner_id, frame_id, select_map_id, total_awake, grade_rating, music_rating, player_rating, highest_rating, grade_rank, class_rank, course_rank, chara_slot, chara_lock_slot, content_bit, play_count, event_watched_date, last_game_id, last_rom_version, last_data_version, last_login_date, last_play_date, last_play_credit, last_play_mode, last_place_id, last_place_name, last_all_net_id, last_region_id, last_region_name, last_client_id, last_country_code, last_selectemoney, last_select_ticket, last_select_course, last_count_course, first_game_id, first_rom_version, first_data_version, first_play_date, compatible_cm_version, daily_bonus_date, daily_course_bonus_date, play_vs_count, play_sync_count, win_count, help_count, combo_count, total_deluxscore, total_basic_deluxscore, total_advanced_deluxscore, total_expert_deluxscore, total_master_deluxscore, total_re_master_deluxscore, total_sync, total_basic_sync, total_advanced_sync, total_expert_sync, total_master_sync, total_re_master_sync, total_achievement, total_basic_achievement, total_advanced_achievement, total_expert_achievement, total_master_achievement, total_re_master_achievement, date_time, 0, 0
FROM temp;
DROP TABLE temp;

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
    ON CONFLICT REPLACE
);

INSERT INTO maimai2_user_charge SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_course
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_course;
DROP TABLE maimai2_user_course;

CREATE TABLE maimai2_user_course (
    id                    INTEGER,
    course_id             INTEGER,
    is_last_clear         BOOLEAN,
    total_restlife        INTEGER,
    total_achievement     INTEGER,
    total_deluxscore      INTEGER,
    play_count            INTEGER,
    clear_date            VARCHAR (255),
    last_play_date        VARCHAR (255),
    best_achievement      INTEGER,
    best_achievement_date VARCHAR (255),
    best_deluxscore       INTEGER,
    best_deluxscore_date  VARCHAR (255),
    user_id               BIGINT        REFERENCES maimai2_user_detail (id),
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_course SELECT * FROM temp;
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

-- maimai2_user_general_data
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_general_data;
DROP TABLE maimai2_user_general_data;

CREATE TABLE maimai2_user_general_data (
    id             INTEGER,
    property_key   VARCHAR NOT NULL,
    property_value VARCHAR NOT NULL,
    user_id        BIGINT  REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT maimai2_user_general_data_uq UNIQUE (
        property_key,
        user_id
    )
    ON CONFLICT REPLACE
);

INSERT INTO maimai2_user_general_data SELECT * FROM temp;
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

-- maimai2_user_option
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_option;
DROP TABLE maimai2_user_option;

CREATE TABLE maimai2_user_option (
    id                   INTEGER,
    option_kind          INTEGER,
    note_speed           INTEGER,
    slide_speed          INTEGER,
    touch_speed          INTEGER,
    tap_design           INTEGER,
    hold_design          INTEGER,
    slide_design         INTEGER,
    star_type            INTEGER,
    outline_design       INTEGER,
    note_size            INTEGER,
    slide_size           INTEGER,
    touch_size           INTEGER,
    star_rotate          INTEGER,
    disp_center          INTEGER,
    disp_chain           INTEGER,
    disp_rate            INTEGER,
    disp_bar             INTEGER,
    touch_effect         INTEGER,
    submonitor_animation INTEGER,
    submonitor_achive    INTEGER,
    submonitor_appeal    INTEGER,
    matching             INTEGER,
    track_skip           INTEGER,
    brightness           INTEGER,
    mirror_mode          INTEGER,
    disp_judge           INTEGER,
    disp_judge_pos       INTEGER,
    disp_judge_touch_pos INTEGER,
    adjust_timing        INTEGER,
    judge_timing         INTEGER,
    ans_volume           INTEGER,
    tap_hold_volume      INTEGER,
    critical_se          INTEGER,
    break_se             INTEGER,
    break_volume         INTEGER,
    ex_se                INTEGER,
    ex_volume            INTEGER,
    slide_se             INTEGER,
    slide_volume         INTEGER,
    touch_hold_volume    INTEGER,
    damage_se_volume     INTEGER,
    head_phone_volume    INTEGER,
    sort_tab             INTEGER,
    sort_music           INTEGER,
    user_id              BIGINT  REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);
INSERT INTO maimai2_user_option SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_playlog
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_playlog;
DROP TABLE maimai2_user_playlog;

CREATE TABLE maimai2_user_playlog (
    id                       INTEGER,
    order_id                 INTEGER,
    playlog_id               BIGINT,
    version                  INTEGER,
    place_id                 INTEGER,
    place_name               VARCHAR (255),
    login_date               BIGINT,
    play_date                VARCHAR (255),
    user_play_date           VARCHAR (255),
    type                     INTEGER,
    music_id                 INTEGER,
    level                    INTEGER,
    track_no                 INTEGER,
    vs_mode                  INTEGER,
    vs_user_name             VARCHAR (255),
    vs_status                INTEGER,
    vs_user_rating           INTEGER,
    vs_user_achievement      INTEGER,
    vs_user_grade_rank       INTEGER,
    vs_rank                  INTEGER,
    player_num               INTEGER,
    played_user_id1          BIGINT,
    played_user_name1        VARCHAR (255),
    played_music_level1      INTEGER,
    played_user_id2          BIGINT,
    played_user_name2        VARCHAR (255),
    played_music_level2      INTEGER,
    played_user_id3          BIGINT,
    played_user_name3        VARCHAR (255),
    played_music_level3      INTEGER,
    character_id1            INTEGER,
    character_level1         INTEGER,
    character_awakening1     INTEGER,
    character_id2            INTEGER,
    character_level2         INTEGER,
    character_awakening2     INTEGER,
    character_id3            INTEGER,
    character_level3         INTEGER,
    character_awakening3     INTEGER,
    character_id4            INTEGER,
    character_level4         INTEGER,
    character_awakening4     INTEGER,
    character_id5            INTEGER,
    character_level5         INTEGER,
    character_awakening5     INTEGER,
    achievement              INTEGER,
    deluxscore               INTEGER,
    score_rank               INTEGER,
    max_combo                INTEGER,
    total_combo              INTEGER,
    max_sync                 INTEGER,
    total_sync               INTEGER,
    tap_critical_perfect     INTEGER,
    tap_perfect              INTEGER,
    tap_great                INTEGER,
    tap_good                 INTEGER,
    tap_miss                 INTEGER,
    hold_critical_perfect    INTEGER,
    hold_perfect             INTEGER,
    hold_great               INTEGER,
    hold_good                INTEGER,
    hold_miss                INTEGER,
    slide_critical_perfect   INTEGER,
    slide_perfect            INTEGER,
    slide_great              INTEGER,
    slide_good               INTEGER,
    slide_miss               INTEGER,
    touch_critical_perfect   INTEGER,
    touch_perfect            INTEGER,
    touch_great              INTEGER,
    touch_good               INTEGER,
    touch_miss               INTEGER,
    break_critical_perfect   INTEGER,
    break_perfect            INTEGER,
    break_great              INTEGER,
    break_good               INTEGER,
    break_miss               INTEGER,
    is_tap                   BOOLEAN,
    is_hold                  BOOLEAN,
    is_slide                 BOOLEAN,
    is_touch                 BOOLEAN,
    is_break                 BOOLEAN,
    is_critical_disp         BOOLEAN,
    is_fast_late_disp        BOOLEAN,
    fast_count               INTEGER,
    late_count               INTEGER,
    is_achieve_new_record    BOOLEAN,
    is_deluxscore_new_record BOOLEAN,
    combo_status             INTEGER,
    sync_status              INTEGER,
    is_clear                 BOOLEAN,
    before_rating            INTEGER,
    after_rating             INTEGER,
    before_grade             INTEGER,
    after_grade              INTEGER,
    after_grade_rank         INTEGER,
    before_delux_rating      INTEGER,
    after_delux_rating       INTEGER,
    is_play_tutorial         BOOLEAN,
    is_event_mode            BOOLEAN,
    is_freedom_mode          BOOLEAN,
    play_mode                INTEGER,
    is_new_free              BOOLEAN,
    ext_num1                 INTEGER,
    ext_num2                 INTEGER,
    user_id                  BIGINT        REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_playlog SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_udemae
CREATE TEMPORARY TABLE temp AS SELECT * FROM maimai2_user_udemae;
DROP TABLE maimai2_user_udemae;

CREATE TABLE maimai2_user_udemae (
    id                 INTEGER,
    rate               INTEGER,
    max_rate           INTEGER,
    class_value        INTEGER,
    max_class_value    INTEGER,
    total_win_num      INTEGER,
    total_lose_num     INTEGER,
    max_win_num        INTEGER,
    max_lose_num       INTEGER,
    win_num            INTEGER,
    lose_num           INTEGER,
    npc_total_win_num  INTEGER,
    npc_total_lose_num INTEGER,
    npc_max_win_num    INTEGER,
    npc_max_lose_num   INTEGER,
    npc_win_num        INTEGER,
    npc_lose_num       INTEGER,
    user_id            BIGINT  REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_udemae SELECT * FROM temp;
DROP TABLE temp;

-- maimai2_user_friend_season_ranking

CREATE TABLE maimai2_user_friend_season_ranking (
    id          INTEGER,
    season_id   INTEGER,
    point       INTEGER,
    rank        INTEGER,
    reward_get  BOOLEAN,
    user_name   VARCHAR (255),
    record_date VARCHAR (255),
    user_id     BIGINT        REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    ),
    CONSTRAINT maimai2_user_friend_season_ranking_uq UNIQUE (
        season_id,
        user_id
    )
    ON CONFLICT REPLACE
);
