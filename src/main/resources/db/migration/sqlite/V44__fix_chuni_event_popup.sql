UPDATE `chuni_game_event` SET `enable` = false WHERE `type` = 1 AND `enable` = true;
DELETE FROM `chuni_game_event` WHERE `id` = 19000;
