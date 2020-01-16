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

v0.0.3: Add database migration tool. If you are running on a old version, I encourage you to delete the old database and generate a new one.

### Credit:
* **samnyan**
* **Akasaka Ryuunosuke** : providing all the DIVA protocol information
* All devs who contribute to the MiniMe server