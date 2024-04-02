# AquaDX

Multipurpose game server powered by Spring Boot, for ALL.Net-based games

This is an attempt to rebuild the [original Aqua server](https://dev.s-ul.net/NeumPhis/aqua)

## Related Projects

* [AquaMai](./AquaMai): A maimai DX mod that adds many features to the game.
* [AquaNet](./AquaNet): A new web frontend for the modern age.

### Supported Games

Below is a list of games supported by this server. 

| Game                       | Ver  | Codename      | Thanks to                                  |
|----------------------------|------|---------------|--------------------------------------------|
| SDHD: CHUNITHM (Chusan)    | 2.20 | LUMINOUS      | [@rinsama](https://github.com/mxihan)      |
| SDEZ: MaiMai DX            | 1.40 | BUDDiES       | [@肥宅虾哥](https://github.com/FeiZhaixiage)   |
| SDED: Card Maker           | 1.34 |               |                                            |
| SBZV: Project DIVA Arcade  | 7.10 | Future Tone   |                                            |
| SDDT: O.N.G.E.K.I.         | 1.39 | bright MEMORY | [@Gamer2097](https://github.com/Gamer2097) |
| SDFE: Wacca (*ALPHA STAGE) | 3.07 | Reverse       |                                            |

> **News**: AquaDX just added Wacca support on Mar 29, 2024! Feel free to test it out, but expect bugs and issues.

Check out these docs for more information.
* [Game specific notes](docs/game_specific_notes.md)
* [Frequently asked questions](docs/frequently_asked_questions.md)

### Notes
* Some games may require additional patches and these will not provided in this project and repository. You already found this, so you know where to find related resources too.
* This repository may contain untested, experimental implementations for a few games which I can't test properly. If you couldn't find your wanted game in the above list, do not expect support.
* This server also provides a simple API for viewing play records and editing settings for some games.

### Usage

1. Install [Java 21 Temurin JDK](https://adoptium.net/temurin/releases/?version=21) (Please select your appropriate operating system)
2. Download the latest `aqua-nightly.zip` from [Releases](https://github.com/hykilpikonna/AquaDX/releases).
3. Extract the zip file to a folder.
4. Run `java -jar aqua.jar` in the folder.

By default, Aqua will use SQLite and save user data in `data/db.sqlite`.

If you want to use optional databases, please edit the configuration file then it will auto-create the table and import some initial data.

### Configuration
Configuration is saved in `config/application.properties`, spring loads this file automatically.

* The host and port of game title servers can be overwritten in `allnet.server.host` and `allnet.server.port`. By default it will send the same host and port the client used the request this information.
This will be sent to the game at booting and being used by the following request.
* You can switch to the MariaDB database by commenting the Sqlite part.
* For some games, you might need to change some game-specific config entries.

### Building
You need to install JDK on your system. However, you don't need to install Gradle separately, as the `gradlew` wrapper script is included.
```
gradlew clean build
```
The `build/libs` folder will contain a jar file.

### Credit
* **samnyan**: The creator and developer of the original Aqua server
* **Akasaka Ryuunosuke**: providing all the DIVA protocol information
* Dom Eori: Developer of forked Aqua server, from v0.0.17 and up
* All devs who contribute to the [MiniMe server](https://dev.s-ul.net/djhackers/minime)
* All contributors by merge requests, issues and other channels

### License: [CC By-NC-SA](https://creativecommons.org/licenses/by-nc-sa/4.0/deed.en)

* **Attribution** — You must give appropriate credit, provide a link to the license, and indicate if changes were made. You may do so in any reasonable manner, but not in any way that suggests the licensor endorses you or your use.
* **NonCommercial** — You may not use the material for commercial purposes.
* **ShareAlike** — If you remix, transform, or build upon the material, you must distribute your contributions under the same license as the original.
