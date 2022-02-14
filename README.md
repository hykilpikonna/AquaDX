# Aqua Server
An multipurpose game server power by Spring Boot.

### Supported Game:
* CHUNITHM Paradise Lost (and below)
* Maimai DX Universe (and below)
* Project DIVA Arcade Future Tone
* O.N.G.E.K.I. bright (and below)

### Notes:
* Some game may require additional patches and these will not provided in this project and repository. You already found this, so you know where to find related resources too.
* This repository may contain untested, experimental support for few game which I can't test properly. If you couldn't find your wanted game in the above list, do not expect worthwhile support.

### Usage:
Requirements:
* Java 11 or above
* Optional: MariaDB or MySQL (<8.0)

Run `java -jar aqua.jar` or use the `start.bat` if you are using windows.

By default, aqua will use sqlite and save user data in data/db.sqlite.

If you want to MySQL (or MariaDB), edit configuration then it will auto create the table and import some initial data.

Please go to the database migration tool's website to check if your database version is being supported https://flywaydb.org/documentation/database/mariadb

### Configuration:
Configuration is save in `application.properties`

- The host and port of game title servers can be overritten in `allnet.server.host` and `allnet.server.port`. By default it will send the same host and port the client used the request this information.
This will be send to the game at booting and being used by following request.

- You can switch to MySQL (or MariaDB) database by commenting the Sqlite part.

#### Game specific setting:

##### CHUNITHM:
- Support auto profile downgrade now. You can just run any legacy version, but works better if you set a different keychip serial.
- You can enable team function and customize team name by changing `game.chunithm.team-name` value. Leave this blank to disable team function.

### Other Information:
- This server provide a simple API for view play information and edit settings for some games.
- DIVA and Maimai DX screenshot will be save in data folder.

### Building:
You need to install JDK on your system. However, you don't need to care about Maven, as wrapper script is included.
```
mvnw package
```
The output jar will be in `target` folder.

Currently, there is no script for making distribution package automatically.

### Credit:
* **samnyan**
* **Akasaka Ryuunosuke** : providing all the DIVA protocol information
* Dom Eori : from v0.0.17 and up
* All devs who contribute to the MiniMe server
* All contributors by merge request, issues and other channels..
