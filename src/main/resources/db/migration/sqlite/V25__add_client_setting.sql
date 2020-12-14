create table sega_game_version
(
	uuid varchar
		primary key,
	rom_version varchar not null,
	data_version varchar not null,
	last_time DATETIME not null
);