# Aqua Server
An multipurpose game server power by Spring Boot.

### Supported Game:
* CHUNITHM Amazon
* Project DIVA Arcade Future Tone

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

### Other Information:
This server provide a simple API for changing some DIVA's setting.

A Web App can be found on https://github.com/samnyan/aqua-viewer

Live Version: http://aqua.samnyan.icu/

And DIVA screenshot will be save in data folder.


### Changelog:

v0.0.9: **[This will do database update]**

[api] Fix rating fail to calculate due to lack of music level info

[api] Move diva music list to database

[DIVA] Fix continue not work

[DIVA] Clear status now will count lower clear rank

v0.0.8: **[This will do database update]**

[chuni] Fix a course table column

[api] Force unlock diva session

[api] Get screenshot

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