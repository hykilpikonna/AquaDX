create table ongeki_user_boss
(
	id INTEGER,
	music_id INTEGER NOT NULL,
	damage INTEGER NOT NULL,
	is_clear BOOLEAN NOT NULL,
    user_id BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_boss_uq UNIQUE (
                                           music_id,
                                           user_id
        ) ON CONFLICT REPLACE
);

create table ongeki_user_scenario
(
	id INTEGER,
	scenario_id INTEGER NOT NULL,
	play_count INTEGER NOT NULL,
    user_id BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_scenario_uq UNIQUE (
                                           scenario_id,
                                           user_id
        ) ON CONFLICT REPLACE
);

create table ongeki_user_tech_count
(
	id INTEGER,
	level_id INTEGER NOT NULL,
	all_break_count INTEGER NOT NULL,
	all_break_plus_count INTEGER NOT NULL,
    user_id BIGINT REFERENCES ongeki_user_data (id) ON DELETE CASCADE,
    PRIMARY KEY (
                 id
        ),
    CONSTRAINT ongeki_user_tech_count_uq UNIQUE (
                                           level_id,
                                           user_id
        ) ON CONFLICT REPLACE
);