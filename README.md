# Aqua Server
An multipurpose game server power by Spring Boot.

### Supported Game:
* CHUNITHM Amazon
* Project DIVA Arcade Future Tone

### Usage:
Requirements:
* Java 11 or above
* MySQL (Optional)

Just run `java -jar aqua.jar`

or use the `start.bat` if you are using windows.

User data will be save in data/db.sqlite.
If you switch to MySQL, it will auto create the table and import some initial data.

Auto creation won't work with Sqlite, so if you want to recreate the database please use the file come with the release

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

### Credit:
* **samnyan**
* **Akasaka Ryuunosuke** : providing all the DIVA protocol information
* All devs who contribute to the MiniMe server