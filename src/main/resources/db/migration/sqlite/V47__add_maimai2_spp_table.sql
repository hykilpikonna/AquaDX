CREATE TABLE maimai2_game_charge (
    id         INTEGER,
    order_id   INTEGER,
    charge_id  INTEGER       UNIQUE,
    price      INTEGER,
    start_date VARCHAR (255),
    end_date   VARCHAR (255),
    PRIMARY KEY (
        id
    )
);

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

CREATE TABLE maimai2_user_course (
    id                    INTEGER,
    course_id             INTEGER,
    is_last_clear         BOOLEAN,
    total_rest_life       INTEGER,
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

-- maimai2_user_detail: class_rank, course_rank, last_play_credit, last_play_mode, last_selectemoney, last_select_ticket, last_select_course, last_count_course, daily_course_bonus_date
CREATE TABLE maimai2_user_detail_new (
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
    PRIMARY KEY (
        id
    )
);

INSERT INTO maimai2_user_detail_new
(id, aime_card_id, user_name, is_net_member, icon_id, plate_id, title_id, partner_id, frame_id, select_map_id, total_awake, grade_rating, music_rating, player_rating, highest_rating, grade_rank, chara_slot, chara_lock_slot, content_bit, play_count, event_watched_date, last_game_id, last_rom_version, last_data_version, last_login_date, last_play_date, last_place_id, last_place_name, last_all_net_id, last_region_id, last_region_name, last_client_id, last_country_code, first_game_id, first_rom_version, first_data_version, first_play_date, compatible_cm_version, daily_bonus_date, play_vs_count, play_sync_count, win_count, help_count, combo_count, total_deluxscore, total_basic_deluxscore, total_advanced_deluxscore, total_expert_deluxscore, total_master_deluxscore, total_re_master_deluxscore, total_sync, total_basic_sync, total_advanced_sync, total_expert_sync, total_master_sync, total_re_master_sync, total_achievement, total_basic_achievement, total_advanced_achievement, total_expert_achievement, total_master_achievement, total_re_master_achievement, date_time, class_rank, course_rank, last_play_credit, last_play_mode, last_selectemoney, last_select_ticket, last_select_course, last_count_course, daily_course_bonus_date)
SELECT id, aime_card_id, user_name, is_net_member, icon_id, plate_id, title_id, partner_id, frame_id, select_map_id, total_awake, grade_rating, music_rating, player_rating, highest_rating, grade_rank, chara_slot, chara_lock_slot, content_bit, play_count, event_watched_date, last_game_id, last_rom_version, last_data_version, last_login_date, last_play_date, last_place_id, last_place_name, last_all_net_id, last_region_id, last_region_name, last_client_id, last_country_code, first_game_id, first_rom_version, first_data_version, first_play_date, compatible_cm_version, daily_bonus_date, play_vs_count, play_sync_count, win_count, help_count, combo_count, total_deluxscore, total_basic_deluxscore, total_advanced_deluxscore, total_expert_deluxscore, total_master_deluxscore, total_re_master_deluxscore, total_sync, total_basic_sync, total_advanced_sync, total_expert_sync, total_master_sync, total_re_master_sync, total_achievement, total_basic_achievement, total_advanced_achievement, total_expert_achievement, total_master_achievement, total_re_master_achievement, date_time, 0, 0, 1, 0, 0, 0, 0, 0, "2000-01-01 05:00:00.0"
FROM maimai2_user_detail;

ALTER TABLE maimai2_user_detail RENAME TO bak_maimai2_user_detail;
ALTER TABLE maimai2_user_detail_new RENAME TO maimai2_user_detail;

-- maimai2_user_option: judge_timing
CREATE TABLE maimai2_user_option_new (
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

INSERT INTO maimai2_user_option_new
(id, option_kind, note_speed, slide_speed, touch_speed, tap_design, hold_design, slide_design, star_type, outline_design, note_size, slide_size, touch_size, star_rotate, disp_center, disp_chain, disp_rate, disp_bar, touch_effect, submonitor_animation, submonitor_achive, submonitor_appeal, matching, track_skip, brightness, mirror_mode, disp_judge, disp_judge_pos, disp_judge_touch_pos, adjust_timing, ans_volume, tap_hold_volume, critical_se, break_se, break_volume, ex_se, ex_volume, slide_se, slide_volume, touch_hold_volume, damage_se_volume, head_phone_volume, sort_tab, sort_music, user_id, judge_timing)
SELECT id, option_kind, note_speed, slide_speed, touch_speed, tap_design, hold_design, slide_design, star_type, outline_design, note_size, slide_size, touch_size, star_rotate, disp_center, disp_chain, disp_rate, disp_bar, touch_effect, submonitor_animation, submonitor_achive, submonitor_appeal, matching, track_skip, brightness, mirror_mode, disp_judge, disp_judge_pos, disp_judge_touch_pos, adjust_timing, ans_volume, tap_hold_volume, critical_se, break_se, break_volume, ex_se, ex_volume, slide_se, slide_volume, touch_hold_volume, damage_se_volume, head_phone_volume, sort_tab, sort_music, user_id, 0
FROM maimai2_user_option;

ALTER TABLE maimai2_user_option RENAME TO bak_maimai2_user_option;
ALTER TABLE maimai2_user_option_new RENAME TO maimai2_user_option;

-- maimai2_user_udemae: class_value, max_class_value
CREATE TABLE maimai2_user_udemae_new (
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

INSERT INTO maimai2_user_udemae_new
(id, rate, max_rate, total_win_num, total_lose_num, max_win_num, max_lose_num, win_num, lose_num, npc_total_win_num, npc_total_lose_num, npc_max_win_num, npc_max_lose_num, npc_win_num, npc_lose_num, user_id, class_value, max_class_value)
SELECT id, rate, max_rate, total_win_num, total_lose_num, max_win_num, max_lose_num, win_num, lose_num, npc_total_win_num, npc_total_lose_num, npc_max_win_num, npc_max_lose_num, npc_win_num, npc_lose_num, user_id, 0, 0
FROM maimai2_user_udemae;

ALTER TABLE maimai2_user_udemae RENAME TO bak_maimai2_user_udemae;
ALTER TABLE maimai2_user_udemae_new RENAME TO maimai2_user_udemae;