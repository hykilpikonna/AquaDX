# Aqua Server
An multipurpose game server power by Spring Boot.

### Supported Game:
* CHUNITHM Paradise Lost (and below)
* Maimai DX Splash
* Maimai Finale (not tested)
* Project DIVA Arcade Future Tone
* O.N.G.E.K.I. RED PLUS
* O.N.G.E.K.I. SUMMER
* O.N.G.E.K.I. PLUS

### Usage:
Requirements:
* Java 11 or above
* MySQL (Optional)

Edit `application.properties` , change the `allnet.server.host` to your IP address or hostname.
DIVA won't work with localhost and 127.0.0.1

Then run `java -jar aqua.jar`

or use the `start.bat` if you are using windows.

User data will be save in data/db.sqlite.
If you switch to MySQL, it will auto create the table and import some initial data.

Please go to the database migration tool's website to check if the Database version is being supported https://flywaydb.org/documentation/database/mariadb 

### Configuration:
Configuration is save in `application.properties`

If you are going to deploy on other machine, you must change the `allnet.server.host` and `allnet.server.port` to the IP or Hostname of the hosting machine.
This will be send to the game at booting and being used by following request.

And you can switch to MySQL(MariaDB) database by commenting the Sqlite part.

#### Game specific setting:

##### CHUNITHM:
- Support auto profile downgrade now. You can just run any legacy version, but works better if you set a different keychip serial.
- You can enable team function and customize team name by changing `game.chunithm.team-name` value. Leave this blank to disable team function.

### Other Information:
This server provide a simple API for changing some DIVA's setting.

And DIVA screenshot will be save in data folder.


### Changelog:

v0.0.22c:

[maimai2] Fix play saving on first entry session

[chuni] Add game data: chara, skill, event, music, music level

[ongeki] Add game data: event, music

v0.0.22b:

[chuni] Add game data: event, music, music level

[ongeki] Add game data: event, music

v0.0.22a:

[ongeki] Add game data: event, music

v0.0.22: **[This will do database update]**

[chuni] Implement GetGameRankingApi

[maimai2] Enable isNetUser and implement UploadUserPhotoApi

[maimai2] Implement GetGameEventApi and UploadUserPlaylogApi

[chuni] Add game data: chara, skill, event, music, music level

v0.0.21: **[This will do database update]**

Update to Spring Boot 2.5

[maimai2] Experimental Splash Plus Support

v0.0.20a:

[chuni] Add game data: chara, skill, event, music, music level

v0.0.20: **[This will do database update]**

[chuni] Fix: make event popup to not show

[ongeki] Add table properties for Red Plus

[maimai2] Fix play record saving when guest is involved

v0.0.19e:

[chuni] Add game data: chara, skill, event, music, music level

[ongeki] Add game data: event, music

v0.0.19d:

[chuni] Add game data: chara, skill, event, music, music level

v0.0.19c:

[maimai2] Fix incorrect scope during save UserRating

v0.0.19b:

[chuni] Add game data: event, music, music level

v0.0.19a: **[This will do database update]**

[ongeki] Add game data: card, music, event

[ongeki] Fix judgement offset saving

v0.0.19: **[This will do database update]**

[chuni] Add missing data: skill, character, music, music level

[ongeki] Add missing data: card, character, music, event

[ongeki] Add proper endpoint for new APIs

[chuni] Add team name customization feature

[api] Fix broken chunithm API

v0.0.18:

[ONGEKI] Add support for ONGEKI Red Plus

v0.0.17: **[This will do database update]**

[maimai2] Add support for Maimai DX Splash

[chuni] Enable standard course and team function

[chuni] Add support for CHUNITHM Paradise Lost

[maimai] Add Maimai Finale support

v0.0.16: **[This will do database update]**

[chuni] Add support for CHUNITHM Amazon Plus

[chuni] Support auto profile downgrade now.

[ONGEKI] Fix jewel not being saved (bbs)

[ONGEKI] Better choKaika method (bbs)

v0.0.15:

[ONGEKI] Add support for ONGEKI Summer

v0.0.14:

[general] Reduce connection pool size to 1 to prevent dead lock with sqlite

[ONGEKI & chuni] Fix score missing again

[chuni] Read reboot time from database

[api] Set level to max when chouKaika a card


v0.0.13: **[This will do database update]**

[ONGEKI & chuni] Fix rating drop

[aimedb] Allow bind to specific interface

[API] Allow export and import ongeki and chuni profile. More feature to chuni's api

v0.0.12:

[ONGEKI] Save UserMissionPoint, UserTrainingRoom, UserGeneralData, GamePoint, GamePresent, GameReward to database

[ONGEKI] Add custom maintenance time to database

[ONGEKI] Save the battle point and rating info send by the game to database

[API] Read database from general table

v0.0.11a:

[API] Add more ongeki feature

v0.0.11: **[This will do database update]**

[ONGEKI] Add support to ongeki plus

v0.0.10: **[This will do database update]**

[DIVA] Add mega39's pv list

[DIVA] Configurable contest pv limit and reward

[chuni] Add all old version event

[chuni] Disable all type 1 event by default

[chuni] Allow game version overwrite to play the same profile across all version

v0.0.9: **[This will do database update]**

[API] Fix rating fail to calculate due to lack of music level info

[API] Move diva music list to database

[DIVA] Fix continue not work

[DIVA] Clear status now will count lower clear rank

v0.0.8: **[This will do database update]**

[chuni] Fix a course table column

[API] Force unlock diva session

[API] Get screenshot

v0.0.7: **[This will do database update]**

[chuni] Add basic support to old release

[DIVA] Fix wrong name is being sent to the ranking

[DIVA] Fix exex ranking not being return.

[DIVA] Fix wrong contest progress is being sent

[DIVA] Add stage result index to prevent multiple result being sent by client, fix #3

[aimedb] Prevent same access code being register multiple times

[allnet] Fix host header

v0.0.6: **[This will do database update]**

[DIVA] Replace with correct pv list databank

[DIVA] fix stage_result placeholder to the correct length, level up animation is now working

[DIVA] Rival support and configurable border.

[DIVA] Fix ranking being reversed

[API] Allow edit diva rival and new border type


v0.0.5: **[This will do database update]**

[aimedb] fix some card number causing overflow

[chuni] fix unique key constraint , fix #1

[API] allow input space in aime request


v0.0.4:

[chuni] Fix user item being overwritten

[API] Fix record id not being return.

v0.0.3:

Add database migration tool. If you are running on a old version, I encourage you to delete the old database and generate a new one.

### Credit:
* **samnyan**
* **Akasaka Ryuunosuke** : providing all the DIVA protocol information
* All devs who contribute to the MiniMe server
