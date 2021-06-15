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
    last_place_id               INTEGER,
    last_place_name             VARCHAR (255),
    last_all_net_id             INTEGER,
    last_region_id              INTEGER,
    last_region_name            VARCHAR (255),
    last_client_id              VARCHAR (255),
    last_country_code           VARCHAR (255),
    first_game_id               VARCHAR (255),
    first_rom_version           VARCHAR (255),
    first_data_version          VARCHAR (255),
    first_play_date             VARCHAR (255),
    compatible_cm_version       VARCHAR (255),
    daily_bonus_date            VARCHAR (255),
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

CREATE TABLE maimai2_user_npc_encount (
    id        INTEGER,
    npc_id    INTEGER,
    music_id  INTEGER,
    extend_id BIGINT  REFERENCES maimai2_user_extend (id) ON DELETE CASCADE,
    user_id   BIGINT  REFERENCES maimai2_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

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


CREATE TABLE maimai2_user_rate (
    id          INTEGER,
    music_id    INTEGER,
    level       INTEGER,
    rom_version INTEGER,
    achievement INTEGER,
    user_id     BIGINT  REFERENCES maimai2_user_detail (id) ON DELETE CASCADE,
    PRIMARY KEY (
        id
    )
);

CREATE TABLE maimai2_user_udemae (
    id                 INTEGER,
    rate               INTEGER,
    max_rate           INTEGER,
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
