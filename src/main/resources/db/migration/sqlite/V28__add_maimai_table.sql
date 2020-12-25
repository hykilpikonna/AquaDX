create table maimai_game_event
(
	id BIGINT,
	end_date VARCHAR(255) NULL,
	event_id INTEGER NOT NULL,
	start_date VARCHAR(255) NULL,
	type INTEGER NOT NULL,
    PRIMARY KEY (
             id
    )
);

create table maimai_user_data
(
	id BIGINT,
	challenge_track_phase INTEGER NOT NULL,
	combo_count INTEGER NOT NULL,
	event_point INTEGER NOT NULL,
	event_watched_date VARCHAR(255) NULL,
	fever_count INTEGER NOT NULL,
	first_play_bits INTEGER NOT NULL,
	frame_id INTEGER NOT NULL,
	help_count INTEGER NOT NULL,
	highest_rating INTEGER NOT NULL,
	icon_id INTEGER NOT NULL,
	last_client_id VARCHAR(255) NULL,
	last_country_code VARCHAR(255) NULL,
	last_data_version INTEGER NOT NULL,
	last_login_bonus_day INTEGER NOT NULL,
	last_place_id INTEGER NOT NULL,
	last_place_name VARCHAR(255) NULL,
	last_play_date VARCHAR(255) NULL,
	last_region_id INTEGER NOT NULL,
	last_region_name VARCHAR(255) NULL,
	last_survival_bonus_day INTEGER NOT NULL,
	login_bonus_lv INTEGER NOT NULL,
	nameplate_id INTEGER NOT NULL,
	play_count INTEGER NOT NULL,
	play_sync_count INTEGER NOT NULL,
	play_vs_count INTEGER NOT NULL,
	player_rating INTEGER NOT NULL,
	point INTEGER NOT NULL,
	rank_auth_tail_id INTEGER NOT NULL,
	total_advanced_high_score INTEGER NOT NULL,
	total_advanced_sync INTEGER NOT NULL,
	total_basic_high_score INTEGER NOT NULL,
	total_basic_sync INTEGER NOT NULL,
	total_easy_high_score INTEGER NOT NULL,
	total_easy_sync INTEGER NOT NULL,
	total_expert_high_score INTEGER NOT NULL,
	total_expert_sync INTEGER NOT NULL,
	total_hi_score INTEGER NOT NULL,
	total_high_sync INTEGER NOT NULL,
	total_lv INTEGER NOT NULL,
	total_master_high_score INTEGER NOT NULL,
	total_master_sync INTEGER NOT NULL,
	total_point INTEGER NOT NULL,
	total_re_master_high_score INTEGER NOT NULL,
	total_re_master_sync INTEGER NOT NULL,
	trophy_id INTEGER NOT NULL,
	user_name VARCHAR(255) NULL,
	web_limit_date VARCHAR(255) NULL,
	win_count INTEGER NOT NULL,
	aime_card_id BIGINT REFERENCES sega_card (id) ON DELETE CASCADE,
    PRIMARY KEY (
         id
    )
);

create table maimai_user_activity
(
	id BIGINT,
	activity_id INTEGER NOT NULL,
	kind INTEGER NOT NULL,
	param1 INTEGER NOT NULL,
	param2 INTEGER NOT NULL,
	param3 INTEGER NOT NULL,
	param4 INTEGER NOT NULL,
	sort_number BIGINT NOT NULL,
	user_id BIGINT REFERENCES maimai_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
             id
    )
);

create table maimai_user_boss
(
	id BIGINT,
	emblem_flag_list BIGINT NOT NULL,
	pandora_flag_list0 BIGINT NOT NULL,
	pandora_flag_list1 BIGINT NOT NULL,
	pandora_flag_list2 BIGINT NOT NULL,
	pandora_flag_list3 BIGINT NOT NULL,
	pandora_flag_list4 BIGINT NOT NULL,
	pandora_flag_list5 BIGINT NOT NULL,
	pandora_flag_list6 BIGINT NOT NULL,
	user_id BIGINT REFERENCES maimai_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
             id
    )
);

create table maimai_user_character
(
	id BIGINT,
	character_id INTEGER NOT NULL,
	level INTEGER NOT NULL,
	point INTEGER NOT NULL,
	user_id BIGINT REFERENCES maimai_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
             id
    )
);

create table maimai_user_general_data
(
	id BIGINT,
	property_key VARCHAR(255) NULL,
	property_value text NULL,
	user_id BIGINT REFERENCES maimai_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
             id
    )
);

create table maimai_user_item
(
	id BIGINT,
	item_id INTEGER NOT NULL,
	item_kind INTEGER NOT NULL,
	stock INTEGER NOT NULL,
	user_id BIGINT REFERENCES maimai_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
             id
    )
);

create table maimai_user_music_detail
(
	id BIGINT,
	achievement INTEGER NOT NULL,
	full_combo INTEGER NOT NULL,
	is_all_perfect BOOLEAN NOT NULL,
	is_all_perfect_plus INTEGER NOT NULL,
	level INTEGER NOT NULL,
	max_fever INTEGER NOT NULL,
	music_id INTEGER NOT NULL,
	play_count INTEGER NOT NULL,
	score_max INTEGER NOT NULL,
	sync_rate_max INTEGER NOT NULL,
	user_id BIGINT REFERENCES maimai_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
             id
    )
);

create table maimai_user_option
(
	id BIGINT,
	adjust_timing INTEGER NOT NULL,
	ans_vol INTEGER NOT NULL,
	appeal_flame INTEGER NOT NULL,
	bg_info INTEGER NOT NULL,
	break_se INTEGER NOT NULL,
	break_se_vol INTEGER NOT NULL,
	brightness INTEGER NOT NULL,
	disp_judge INTEGER NOT NULL,
	disp_timing INTEGER NOT NULL,
	dmg_vol INTEGER NOT NULL,
	filter_all_perfect INTEGER NOT NULL,
	filter_difficulty INTEGER NOT NULL,
	filter_full_combo INTEGER NOT NULL,
	filter_full_sync INTEGER NOT NULL,
	filter_genre INTEGER NOT NULL,
	filter_level INTEGER NOT NULL,
	filter_max_fever INTEGER NOT NULL,
	filter_rank INTEGER NOT NULL,
	filter_re_master INTEGER NOT NULL,
	filter_rec INTEGER NOT NULL,
	filter_version INTEGER NOT NULL,
	final_select_category INTEGER NOT NULL,
	final_select_id INTEGER NOT NULL,
	guide_speed INTEGER NOT NULL,
	hard_judge INTEGER NOT NULL,
	is_fever_disp INTEGER NOT NULL,
	is_star_rot INTEGER NOT NULL,
	is_tag_jump INTEGER NOT NULL,
	is_upper_disp INTEGER NOT NULL,
	judge_pos INTEGER NOT NULL,
	mirror_mode INTEGER NOT NULL,
	note_vol INTEGER NOT NULL,
	option_mode INTEGER NOT NULL,
	rating_guard INTEGER NOT NULL,
	select_chara INTEGER NOT NULL,
	simple_option_param INTEGER NOT NULL,
	slide_se INTEGER NOT NULL,
	slide_se_vol INTEGER NOT NULL,
	sort_type INTEGER NOT NULL,
	soud_effect INTEGER NOT NULL,
	timing_pos INTEGER NOT NULL,
	track_skip INTEGER NOT NULL,
	user_id BIGINT REFERENCES maimai_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
             id
    )
);

create table maimai_user_playlog
(
	id BIGINT,
	achievement INTEGER NOT NULL,
	break_bad INTEGER NOT NULL,
	break_good INTEGER NOT NULL,
	break_great INTEGER NOT NULL,
	break_perfect INTEGER NOT NULL,
	break_score INTEGER NOT NULL,
	challenge_life INTEGER NOT NULL,
	challenge_remain INTEGER NOT NULL,
	country VARCHAR(255) NULL,
	event_id INTEGER NOT NULL,
	full_combo INTEGER NOT NULL,
	game_mode INTEGER NOT NULL,
	hold_bad INTEGER NOT NULL,
	hold_good INTEGER NOT NULL,
	hold_great INTEGER NOT NULL,
	hold_perfect INTEGER NOT NULL,
	hold_score INTEGER NOT NULL,
	is_all_perfect BOOLEAN NOT NULL,
	is_all_perfect_plus INTEGER NOT NULL,
	is_challenge_track BOOLEAN NOT NULL,
	is_free_to_play BOOLEAN NOT NULL,
	is_high_score BOOLEAN NOT NULL,
	is_track_skip BOOLEAN NOT NULL,
	level INTEGER NOT NULL,
	max_combo INTEGER NOT NULL,
	max_fever INTEGER NOT NULL,
	music_id INTEGER NOT NULL,
	order_id INTEGER NOT NULL,
	place_id INTEGER NOT NULL,
	place_name VARCHAR(255) NULL,
	play_date VARCHAR(255) NULL,
	played_music_level1 INTEGER NOT NULL,
	played_music_level2 INTEGER NOT NULL,
	played_music_level3 INTEGER NOT NULL,
	played_user_id1 BIGINT NOT NULL,
	played_user_id2 BIGINT NOT NULL,
	played_user_id3 BIGINT NOT NULL,
	played_user_name1 VARCHAR(255) NULL,
	played_user_name2 VARCHAR(255) NULL,
	played_user_name3 VARCHAR(255) NULL,
	player_rating INTEGER NOT NULL,
	region_id INTEGER NOT NULL,
	rival_num INTEGER NOT NULL,
	score INTEGER NOT NULL,
	slide_bad INTEGER NOT NULL,
	slide_good INTEGER NOT NULL,
	slide_great INTEGER NOT NULL,
	slide_perfect INTEGER NOT NULL,
	slide_score INTEGER NOT NULL,
	sort_number BIGINT NOT NULL,
	sync_rate INTEGER NOT NULL,
	tap_bad INTEGER NOT NULL,
	tap_good INTEGER NOT NULL,
	tap_great INTEGER NOT NULL,
	tap_perfect INTEGER NOT NULL,
	tap_score INTEGER NOT NULL,
	track INTEGER NOT NULL,
	user_play_date VARCHAR(255) NULL,
	vs_win INTEGER NOT NULL,
	user_id BIGINT REFERENCES maimai_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
             id
    )
);

create table maimai_user_present_event
(
	id BIGINT,
	point INTEGER NOT NULL,
	present_count INTEGER NOT NULL,
	present_event_id INTEGER NOT NULL,
	rate INTEGER NOT NULL,
	user_id BIGINT REFERENCES maimai_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
             id
    )
);

create table maimai_user_survival
(
	id BIGINT,
	is_clear BOOLEAN NOT NULL,
	is_no_damage BOOLEAN NOT NULL,
	survival_id INTEGER NOT NULL,
	total_achieve INTEGER NOT NULL,
	total_score INTEGER NOT NULL,
	user_id BIGINT REFERENCES maimai_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
             id
    )
);

create table maimai_user_web_option
(
	id BIGINT,
	disp_home_ranker INTEGER NOT NULL,
	disp_judge_style INTEGER NOT NULL,
	disp_rank INTEGER NOT NULL,
	disp_rate INTEGER NOT NULL,
	disp_total_lv INTEGER NOT NULL,
	is_net_member BOOLEAN NOT NULL,
	user_id BIGINT REFERENCES maimai_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
             id
    )
);


INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (1, '2129-12-31 23:59:59.0', 100108, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (2, '2129-12-31 23:59:59.0', 100109, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (3, '2129-12-31 23:59:59.0', 100110, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (4, '2129-12-31 23:59:59.0', 100111, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (5, '2129-12-31 23:59:59.0', 100208, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (6, '2129-12-31 23:59:59.0', 100209, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (7, '2129-12-31 23:59:59.0', 100210, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (8, '2129-12-31 23:59:59.0', 100211, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (9, '2129-12-31 23:59:59.0', 100308, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (10, '2129-12-31 23:59:59.0', 100309, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (11, '2129-12-31 23:59:59.0', 100310, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (12, '2129-12-31 23:59:59.0', 100311, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (13, '2129-12-31 23:59:59.0', 100408, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (14, '2129-12-31 23:59:59.0', 100409, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (15, '2129-12-31 23:59:59.0', 100410, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (16, '2129-12-31 23:59:59.0', 100411, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (17, '2129-12-31 23:59:59.0', 100501, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (18, '2129-12-31 23:59:59.0', 100502, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (19, '2129-12-31 23:59:59.0', 100503, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (20, '2129-12-31 23:59:59.0', 100504, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (21, '2129-12-31 23:59:59.0', 100505, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (22, '2129-12-31 23:59:59.0', 100506, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (23, '2129-12-31 23:59:59.0', 100507, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (24, '2129-12-31 23:59:59.0', 100508, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (25, '2129-12-31 23:59:59.0', 100509, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (26, '2129-12-31 23:59:59.0', 100510, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (27, '2129-12-31 23:59:59.0', 100511, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (28, '2129-12-31 23:59:59.0', 100601, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (29, '2129-12-31 23:59:59.0', 100602, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (30, '2129-12-31 23:59:59.0', 100603, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (31, '2129-12-31 23:59:59.0', 100604, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (32, '2129-12-31 23:59:59.0', 100605, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (33, '2129-12-31 23:59:59.0', 100606, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (34, '2129-12-31 23:59:59.0', 100607, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (35, '2129-12-31 23:59:59.0', 100608, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (36, '2129-12-31 23:59:59.0', 100609, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (37, '2129-12-31 23:59:59.0', 100610, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (38, '2129-12-31 23:59:59.0', 100611, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (39, '2129-12-31 23:59:59.0', 100700, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (40, '2129-12-31 23:59:59.0', 100701, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (41, '2129-12-31 23:59:59.0', 100702, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (42, '2129-12-31 23:59:59.0', 100703, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (43, '2129-12-31 23:59:59.0', 100800, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (44, '2129-12-31 23:59:59.0', 100801, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (45, '2129-12-31 23:59:59.0', 100802, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (46, '2129-12-31 23:59:59.0', 100803, '2019-05-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (47, '2129-12-31 23:59:59.0', 300006, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (48, '2129-12-31 23:59:59.0', 300007, '2018-07-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (49, '2129-12-31 23:59:59.0', 300008, '2018-08-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (50, '2129-12-31 23:59:59.0', 300009, '2018-09-18 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (51, '2129-12-31 23:59:59.0', 300010, '2018-10-16 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (52, '2129-12-31 23:59:59.0', 300011, '2018-11-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (53, '2129-12-31 23:59:59.0', 400001, '2018-12-18 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (54, '2129-12-31 23:59:59.0', 400002, '2019-01-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (55, '2129-12-31 23:59:59.0', 400003, '2019-02-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (56, '2129-12-31 23:59:59.0', 400004, '2019-03-08 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (57, '2129-12-31 23:59:59.0', 400005, '2019-04-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (58, '2129-12-31 23:59:59.0', 400006, '2019-04-12 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (59, '2129-12-31 23:59:59.0', 400007, '2019-04-05 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (60, '2129-12-31 23:59:59.0', 400101, '2018-12-18 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (61, '2129-12-31 23:59:59.0', 400102, '2019-05-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (62, '2129-12-31 23:59:59.0', 400103, '2019-06-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (63, '2129-12-31 23:59:59.0', 400104, '2019-06-14 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (64, '2129-12-31 23:59:59.0', 400105, '2019-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (65, '2129-12-31 23:59:59.0', 400109, '2019-04-12 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (66, '2129-12-31 23:59:59.0', 400110, '2019-04-09 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (67, '2129-12-31 23:59:59.0', 999125, '2017-04-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (68, '2129-12-31 23:59:59.0', 999146, '2017-04-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (69, '2129-12-31 23:59:59.0', 1000000, '2017-04-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (70, '2129-12-31 23:59:59.0', 18500004, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (71, '2129-12-31 23:59:59.0', 18500006, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (72, '2129-12-31 23:59:59.0', 18500008, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (73, '2129-12-31 23:59:59.0', 18500010, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (74, '2129-12-31 23:59:59.0', 18500012, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (75, '2129-12-31 23:59:59.0', 18500020, '2017-08-10 13:30:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (76, '2129-12-31 23:59:59.0', 18500024, '2017-08-10 13:30:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (77, '2129-12-31 23:59:59.0', 18500028, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (78, '2129-12-31 23:59:59.0', 18500030, '2017-06-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (79, '2129-12-31 23:59:59.0', 18501000, '2017-07-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (80, '2129-12-31 23:59:59.0', 18501002, '2017-07-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (81, '2129-12-31 23:59:59.0', 18501004, '2017-07-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (82, '2129-12-31 23:59:59.0', 18501006, '2017-07-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (83, '2129-12-31 23:59:59.0', 18501008, '2017-07-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (84, '2129-12-31 23:59:59.0', 18501010, '2017-07-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (85, '2129-12-31 23:59:59.0', 18501012, '2017-07-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (86, '2129-12-31 23:59:59.0', 18501014, '2017-07-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (87, '2129-12-31 23:59:59.0', 18502000, '2017-08-03 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (88, '2129-12-31 23:59:59.0', 18502002, '2017-09-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (89, '2129-12-31 23:59:59.0', 18502004, '2017-08-03 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (90, '2129-12-31 23:59:59.0', 18502006, '2017-08-03 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (91, '2129-12-31 23:59:59.0', 18502012, '2017-08-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (92, '2129-12-31 23:59:59.0', 18502014, '2017-08-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (93, '2129-12-31 23:59:59.0', 18502016, '2017-08-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (94, '2129-12-31 23:59:59.0', 18502018, '2017-08-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (95, '2129-12-31 23:59:59.0', 18503000, '2017-09-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (96, '2129-12-31 23:59:59.0', 18503002, '2017-09-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (97, '2129-12-31 23:59:59.0', 18503008, '2017-09-19 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (98, '2129-12-31 23:59:59.0', 18503010, '2017-09-19 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (99, '2129-12-31 23:59:59.0', 18503012, '2017-09-19 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (100, '2129-12-31 23:59:59.0', 18504000, '2017-10-03 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (101, '2129-12-31 23:59:59.0', 18504002, '2017-10-03 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (102, '2129-12-31 23:59:59.0', 18504004, '2017-10-03 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (103, '2129-12-31 23:59:59.0', 18504010, '2017-10-17 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (104, '2129-12-31 23:59:59.0', 18504012, '2017-10-17 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (105, '2129-12-31 23:59:59.0', 18504014, '2017-10-31 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (106, '2129-12-31 23:59:59.0', 18505000, '2017-11-09 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (107, '2129-12-31 23:59:59.0', 18505002, '2017-11-09 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (108, '2129-12-31 23:59:59.0', 18505004, '2017-11-09 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (109, '2129-12-31 23:59:59.0', 18505006, '2017-11-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (110, '2129-12-31 23:59:59.0', 18505008, '2017-11-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (111, '2129-12-31 23:59:59.0', 18505010, '2017-11-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (112, '2129-12-31 23:59:59.0', 18505012, '2017-11-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (113, '2129-12-31 23:59:59.0', 18505014, '2017-11-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (114, '2129-12-31 23:59:59.0', 18505016, '2017-11-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (115, '2129-12-31 23:59:59.0', 18506000, '2017-12-05 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (116, '2129-12-31 23:59:59.0', 18506002, '2017-12-05 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (117, '2129-12-31 23:59:59.0', 18506004, '2017-12-05 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (118, '2129-12-31 23:59:59.0', 18506006, '2017-12-05 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (119, '2129-12-31 23:59:59.0', 18506008, '2017-12-05 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (120, '2129-12-31 23:59:59.0', 19000000, '2017-12-14 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (121, '2129-12-31 23:59:59.0', 19000002, '2017-12-14 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (122, '2129-12-31 23:59:59.0', 19000004, '2017-12-14 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (123, '2129-12-31 23:59:59.0', 19000006, '2017-12-14 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (124, '2129-12-31 23:59:59.0', 19000016, '2017-12-14 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (125, '2129-12-31 23:59:59.0', 19000018, '2018-01-23 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (126, '2129-12-31 23:59:59.0', 19000020, '2017-12-14 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (127, '2129-12-31 23:59:59.0', 19000022, '2017-12-14 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (128, '2129-12-31 23:59:59.0', 19000024, '2017-12-26 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (129, '2129-12-31 23:59:59.0', 19001000, '2018-01-09 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (130, '2129-12-31 23:59:59.0', 19001002, '2018-01-09 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (131, '2129-12-31 23:59:59.0', 19001004, '2018-01-23 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (132, '2129-12-31 23:59:59.0', 19001014, '2018-02-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (133, '2129-12-31 23:59:59.0', 19002000, '2018-02-06 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (134, '2129-12-31 23:59:59.0', 19002002, '2018-02-06 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (135, '2129-12-31 23:59:59.0', 19002004, '2018-02-06 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (136, '2129-12-31 23:59:59.0', 19002006, '2018-02-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (137, '2129-12-31 23:59:59.0', 19002008, '2018-02-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (138, '2129-12-31 23:59:59.0', 19002016, '2018-02-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (139, '2129-12-31 23:59:59.0', 19002018, '2018-02-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (140, '2129-12-31 23:59:59.0', 19002020, '2018-02-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (141, '2129-12-31 23:59:59.0', 19002022, '2018-03-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (142, '2129-12-31 23:59:59.0', 19002024, '2018-02-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (143, '2129-12-31 23:59:59.0', 19002026, '2018-02-06 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (144, '2129-12-31 23:59:59.0', 19003000, '2018-03-06 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (145, '2129-12-31 23:59:59.0', 19003002, '2018-03-06 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (146, '2129-12-31 23:59:59.0', 19003004, '2018-03-06 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (147, '2129-12-31 23:59:59.0', 19003006, '2018-03-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (148, '2129-12-31 23:59:59.0', 19003014, '2018-04-17 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (149, '2129-12-31 23:59:59.0', 19004000, '2018-05-08 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (150, '2129-12-31 23:59:59.0', 19004002, '2018-04-03 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (151, '2129-12-31 23:59:59.0', 19004006, '2018-04-17 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (152, '2129-12-31 23:59:59.0', 19004008, '2018-04-17 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (153, '2129-12-31 23:59:59.0', 19004010, '2018-04-17 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (154, '2129-12-31 23:59:59.0', 19004020, '2018-05-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (155, '2129-12-31 23:59:59.0', 19004022, '2018-04-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (156, '2129-12-31 23:59:59.0', 19005000, '2018-05-08 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (157, '2129-12-31 23:59:59.0', 19005004, '2018-05-08 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (158, '2129-12-31 23:59:59.0', 19005006, '2018-05-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (159, '2129-12-31 23:59:59.0', 19005012, '2018-05-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (160, '2129-12-31 23:59:59.0', 19005014, '2018-06-05 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (161, '2129-12-31 23:59:59.0', 19006000, '2018-06-05 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (162, '2129-12-31 23:59:59.0', 19006002, '2018-06-05 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (163, '2129-12-31 23:59:59.0', 19006006, '2018-06-05 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (164, '2129-12-31 23:59:59.0', 19500000, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (165, '2129-12-31 23:59:59.0', 19500002, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (166, '2129-12-31 23:59:59.0', 19500004, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (167, '2129-12-31 23:59:59.0', 19500006, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (168, '2129-12-31 23:59:59.0', 19500008, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (169, '2129-12-31 23:59:59.0', 19500012, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (170, '2129-12-31 23:59:59.0', 19500020, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (171, '2129-12-31 23:59:59.0', 19500022, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (172, '2129-12-31 23:59:59.0', 19500024, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (173, '2129-12-31 23:59:59.0', 19500026, '2018-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (174, '2129-12-31 23:59:59.0', 19501000, '2018-07-03 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (175, '2129-12-31 23:59:59.0', 19501002, '2018-07-03 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (176, '2129-12-31 23:59:59.0', 19501004, '2018-07-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (177, '2129-12-31 23:59:59.0', 19501008, '2018-07-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (178, '2129-12-31 23:59:59.0', 19501010, '2018-07-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (179, '2129-12-31 23:59:59.0', 19501012, '2018-07-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (180, '2129-12-31 23:59:59.0', 19501014, '2018-07-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (181, '2129-12-31 23:59:59.0', 19501016, '2018-07-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (182, '2129-12-31 23:59:59.0', 19501020, '2018-07-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (183, '2129-12-31 23:59:59.0', 19501024, '2018-07-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (184, '2129-12-31 23:59:59.0', 19501026, '2018-07-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (185, '2129-12-31 23:59:59.0', 19502000, '2018-08-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (186, '2129-12-31 23:59:59.0', 19502002, '2018-08-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (187, '2129-12-31 23:59:59.0', 19502004, '2018-08-09 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (188, '2129-12-31 23:59:59.0', 19502006, '2018-08-09 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (189, '2129-12-31 23:59:59.0', 19502008, '2018-08-09 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (190, '2129-12-31 23:59:59.0', 19502010, '2018-08-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (191, '2129-12-31 23:59:59.0', 19502012, '2018-08-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (192, '2129-12-31 23:59:59.0', 19502014, '2018-08-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (193, '2129-12-31 23:59:59.0', 19502018, '2018-08-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (194, '2129-12-31 23:59:59.0', 19503000, '2018-09-04 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (195, '2129-12-31 23:59:59.0', 19503002, '2018-09-04 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (196, '2129-12-31 23:59:59.0', 19503006, '2018-09-18 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (197, '2129-12-31 23:59:59.0', 19503008, '2018-09-18 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (198, '2129-12-31 23:59:59.0', 19503010, '2018-09-18 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (199, '2129-12-31 23:59:59.0', 19503014, '2018-09-18 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (200, '2129-12-31 23:59:59.0', 19503018, '2018-09-18 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (201, '2129-12-31 23:59:59.0', 19503022, '2018-09-18 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (202, '2129-12-31 23:59:59.0', 19503024, '2018-09-18 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (203, '2129-12-31 23:59:59.0', 19503026, '2018-09-18 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (204, '2129-12-31 23:59:59.0', 19504000, '2018-10-02 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (205, '2129-12-31 23:59:59.0', 19504004, '2018-10-16 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (206, '2129-12-31 23:59:59.0', 19504006, '2018-10-16 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (207, '2129-12-31 23:59:59.0', 19504010, '2018-10-16 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (208, '2129-12-31 23:59:59.0', 19504014, '2018-10-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (209, '2129-12-31 23:59:59.0', 19504018, '2018-10-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (210, '2129-12-31 23:59:59.0', 19505000, '2018-11-06 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (211, '2129-12-31 23:59:59.0', 19505002, '2018-11-06 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (212, '2129-12-31 23:59:59.0', 19505006, '2018-11-06 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (213, '2129-12-31 23:59:59.0', 19505008, '2018-11-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (214, '2129-12-31 23:59:59.0', 19505010, '2018-11-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (215, '2129-12-31 23:59:59.0', 19505014, '2018-11-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (216, '2129-12-31 23:59:59.0', 19505016, '2018-11-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (217, '2129-12-31 23:59:59.0', 19506000, '2018-12-04 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (218, '2129-12-31 23:59:59.0', 19506002, '2018-12-04 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (219, '2129-12-31 23:59:59.0', 19506004, '2018-12-04 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (220, '2129-12-31 23:59:59.0', 19598014, '2018-10-16 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (221, '2129-12-31 23:59:59.0', 19598018, '2018-11-20 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (222, '2129-12-31 23:59:59.0', 19900000, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (223, '2129-12-31 23:59:59.0', 19900002, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (224, '2129-12-31 23:59:59.0', 19900004, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (225, '2129-12-31 23:59:59.0', 19900006, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (226, '2129-12-31 23:59:59.0', 19900008, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (227, '2129-12-31 23:59:59.0', 19900010, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (228, '2129-12-31 23:59:59.0', 19900012, '2018-12-18 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (229, '2129-12-31 23:59:59.0', 19900020, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (230, '2129-12-31 23:59:59.0', 19900022, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (231, '2129-12-31 23:59:59.0', 19900024, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (232, '2129-12-31 23:59:59.0', 19900026, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (233, '2129-12-31 23:59:59.0', 19900028, '2018-12-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (234, '2129-12-31 23:59:59.0', 19900030, '2018-12-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (235, '2129-12-31 23:59:59.0', 19900032, '2018-12-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (236, '2129-12-31 23:59:59.0', 19900034, '2018-12-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (237, '2129-12-31 23:59:59.0', 19900036, '2018-12-13 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (238, '2129-12-31 23:59:59.0', 19900038, '2018-12-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (239, '2129-12-31 23:59:59.0', 19901000, '2019-01-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (240, '2129-12-31 23:59:59.0', 19901002, '2019-01-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (241, '2129-12-31 23:59:59.0', 19901004, '2019-01-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (242, '2129-12-31 23:59:59.0', 19901006, '2019-01-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (243, '2129-12-31 23:59:59.0', 19901008, '2019-01-25 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (244, '2129-12-31 23:59:59.0', 19902000, '2019-02-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (245, '2129-12-31 23:59:59.0', 19902002, '2019-02-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (246, '2129-12-31 23:59:59.0', 19902004, '2019-02-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (247, '2129-12-31 23:59:59.0', 19902006, '2019-02-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (248, '2129-12-31 23:59:59.0', 19902008, '2019-02-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (249, '2129-12-31 23:59:59.0', 19902012, '2019-02-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (250, '2129-12-31 23:59:59.0', 19903000, '2019-03-08 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (251, '2129-12-31 23:59:59.0', 19903002, '2019-03-08 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (252, '2129-12-31 23:59:59.0', 19903004, '2019-03-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (253, '2129-12-31 23:59:59.0', 19903006, '2019-03-22 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (254, '2129-12-31 23:59:59.0', 19904000, '2019-04-01 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (255, '2129-12-31 23:59:59.0', 19904002, '2019-04-01 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (256, '2129-12-31 23:59:59.0', 19904004, '2019-04-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (257, '2129-12-31 23:59:59.0', 19904006, '2019-04-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (258, '2129-12-31 23:59:59.0', 19904010, '2019-04-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (259, '2129-12-31 23:59:59.0', 19904016, '2019-04-11 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (260, '2129-12-31 23:59:59.0', 19904020, '2019-04-26 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (261, '2129-12-31 23:59:59.0', 19904022, '2019-04-26 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (262, '2129-12-31 23:59:59.0', 19904024, '2019-04-26 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (263, '2129-12-31 23:59:59.0', 19905000, '2019-05-10 09:50:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (264, '2129-12-31 23:59:59.0', 19905004, '2019-05-10 09:50:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (265, '2129-12-31 23:59:59.0', 19905006, '2019-05-10 09:50:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (266, '2129-12-31 23:59:59.0', 19905008, '2019-05-10 09:50:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (267, '2129-12-31 23:59:59.0', 19905010, '2019-05-10 09:50:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (268, '2129-12-31 23:59:59.0', 19905012, '2019-05-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (269, '2129-12-31 23:59:59.0', 19905014, '2019-05-24 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (270, '2129-12-31 23:59:59.0', 19906000, '2019-06-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (271, '2129-12-31 23:59:59.0', 19906006, '2019-06-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (272, '2129-12-31 23:59:59.0', 19906008, '2019-06-07 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (273, '2129-12-31 23:59:59.0', 19906010, '2019-06-21 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (274, '2129-12-31 23:59:59.0', 19907000, '2019-07-05 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (275, '2129-12-31 23:59:59.0', 19907002, '2019-07-05 07:00:00.0', 1);
INSERT INTO maimai_game_event (id, end_date, event_id, start_date, type) VALUES (276, '2129-12-31 23:59:59.0', 19907004, '2019-07-05 07:00:00.0', 1);

INSERT INTO property (id, property_key, property_value) VALUES (7, 'maimai_game_ranking_1', '607:2221:,288:1490:,475:1098:,792:983:,749:906:,336:830:,793:811:,125:798:,411:705:,299:653:,831:578:,829:498:,838:458:,791:432:,552:429:,181:413:,200:408:,710:388:,363:352:,647:351:,789:343:,500:335:,507:333:,521:320:,841:310:,706:307:,772:293:,21:272:,844:248:,711:221:,508:183:,447:176:,832:162:,501:151:,738:150:,499:147:,752:144:,675:143:,743:141:,188:132:,56:131:,750:131:,731:127:,811:127:,553:127:,191:120:,761:111:,315:109:,830:109:,409:105:,720:96:,204:94:,709:94:,717:93:,742:90:,843:89:,525:89:,282:89:,725:86:,371:84:,381:84:,38:82:,510:82:,412:82:,726:81:,432:80:,754:79:,670:78:,751:77:,356:74:,107:73:,536:73:,809:73:,198:72:,643:69:,199:68:,837:67:,580:66:,496:66:,535:65:,417:64:,258:64:,655:64:,694:64:,836:64:,746:62:,712:62:,781:62:,747:61:,734:60:,187:60:,509:59:,106:59:,382:58:,305:58:,379:58:,782:57:,513:57:,389:56:,227:55:');
INSERT INTO property (id, property_key, property_value) VALUES (8, 'maimai_game_ranking_2', '607:2248:,288:1504:,475:1128:,792:984:,749:896:,336:890:,125:816:,793:778:,411:724:,299:717:,831:608:,829:533:,838:476:,791:459:,181:434:,552:431:,200:408:,789:363:,710:357:,507:354:,647:346:,363:337:,841:316:,706:316:,500:309:,521:303:,772:281:,21:265:,844:236:,711:215:,447:178:,508:177:,499:157:,832:154:,738:154:,501:146:,743:145:,188:142:,675:142:,56:138:,553:137:,811:131:,752:129:,750:123:,731:122:,761:114:,191:113:,409:112:,315:106:,843:104:,830:99:,282:97:,720:94:,717:90:,725:90:,709:89:,356:89:,525:87:,726:87:,204:86:,809:83:,38:81:,381:80:,198:78:,107:77:,754:77:,643:76:,747:76:,742:76:,536:75:,670:75:,432:74:,510:73:,751:73:,509:71:,199:71:,412:70:,496:69:,746:68:,187:68:,371:68:,382:66:,305:64:,379:64:,655:64:,733:63:,837:63:,258:62:,580:61:,847:61:,601:60:,781:60:,389:59:,227:59:,598:58:,691:58:,712:57:,280:56:,836:56:,417:56:');