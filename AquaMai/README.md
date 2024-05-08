## AquaMai

This mod is heavily WIP. More details will be added as the development progresses.

### Features

**Cheats**

* Unlock all tickets

**UX Optimization**

* Remove the starting logo and warning cutscene
* Single Player (1P) mode
* Skip from card scanning directly to music selection (experimental)
* Disable daily automatic reboot
* Customize version text
* Skip the current song by holding 7
* Skip "new event" and "information" screen for new players.

**Bug Fixes**

* Fix crash in the character selection screen

**Performance**

* Speed up things

### Development

1. Copy `Assembly-CSharp.dll` to `Libs` folder.
2. Install [.NET Framework 4.7.2 Developer Pack](https://dotnet.microsoft.com/en-us/download/dotnet-framework/thank-you/net472-developer-pack-offline-installer)
3. Open `AquaMai.sln` in JetBrains Rider.
4. Build the solution.
5. Copy `Output/AquaMai.dll` to `Mods` folder.
6. Configure and copy `AquaMai.toml` to the same folder as your game executable: `Sinmai.exe`

### Relevant Links

* [MelonLoader Wiki](https://melonwiki.xyz/#/modders/quickstart)
* [Harmony Docs](https://harmony.pardeike.net/articles/patching-prefix.html)
