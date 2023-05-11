CREATE TABLE chusan_game_login_bonus_preset
(
    id        INTEGER NOT NULL,
    version   INTEGER NOT NULL,
    preset_name VARCHAR(255) NOT NULL,
    is_enabled  INTEGER DEFAULT 1,
    PRIMARY KEY (
             id
    )
);

CREATE TABLE chusan_game_login_bonus
(
    id        INTEGER NOT NULL,
    version   INTEGER NOT NULL,
    preset_id  INTEGER NOT NULL,
    login_bonus_id  INTEGER NOT NULL,
    login_bonus_name VARCHAR(255) NOT NULL,
    present_id INTEGER NOT NULL,
    present_name VARCHAR(255) NOT NULL,
    item_num INTEGER NOT NULL,
    need_login_day_count INTEGER NOT NULL,
    login_bonus_category_type INTEGER NOT NULL,
    PRIMARY KEY (
             id
    )
);

CREATE TABLE chusan_user_login_bonus
(
    id        INTEGER NOT NULL,
    user   INTEGER NOT NULL,
    version INTEGER NOT NULL,
    preset_id  INTEGER NOT NULL,
    bonus_count  INTEGER NOT NULL DEFAULT 0,
    last_update_date DATETIME NOT NULL default '2018-01-01 00:00:00.0',
    is_watched INTEGER DEFAULT 0,
    is_finished INTEGER DEFAULT 0,
    PRIMARY KEY (
             id
    )
);