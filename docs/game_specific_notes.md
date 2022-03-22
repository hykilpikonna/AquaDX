# Game specific notes
For best viewing experience, please use a markdown viewer that supports Github or Gitlab Flavored Markdown syntax.

This document is for detailed game specific notes, if any.

## Overview

|       Name      | Game ID | Latest supported version | Latest supported option | Actively supported | Requires patch |
| ---             | ---     | ---                      | ---                     | ---                | ---            |
|Chunithm (Chusan)|SDHD     |New                       |A141                     |Yes                 |Yes             |
|Chunithm         |SDBT     |Paradise Lost             |A032                     |Yes                 |Yes (Paradise)  |
|Maimai DX        |SDEZ     |Universe                  |D051                     |Yes                 |Yes             |
|O.N.G.E.K.I      |SDDT     |Bright                    |A016                     |Yes                 |Yes             |
|Maimai           |SDEY     |Finale                    |?                        |No                  |?               |
|Project DIVA AFT |SBZV     |?                         |?                        |No                  |?               |

* Actively supported: if yes, it will likely receive future bug fixes and new version support.
* Requires patch: if yes, game needs to be patched in order to work with Aqua server.
* Latest supported option: this may or may not include all options up to latest.

## Chunithm (Chusan)
Only JP variant is supported.

### Required patches
* No encryption

### Non-working features
* Global matching
* Profile migration from Chunithm

### Additional notes
* Match `game.chusan.version` and `game.chusan.rom-version` key in `application.properties` same as your client. If not, class mode is disabled due to version mismatch.
* Team function can be enabled by changing `game.chusan.team-name` value. Leave this blank to disable team function.
* Chusan and Chunithm uses different endpoints and tables. Your progress from Chunithm won't carry over to Chusan.
* User box customization requires Web UI, which is not added yet. The only option to do now is editing database.

## Chunithm
Only JP variant is supported.

### Required patches
This section only applies to Paradise and up.
* No TLS
* No encryption

### Additional notes
* Workaround for profile version mismatch is implemented, but not recommended.
* Team function can be enabled by changing `game.chunithm.team-name` value. Leave this blank to disable team function.

## Maimai DX
Only JP variant is supported.

### Required patches
* No TLS
* No certificate pinning
* No URI obfuscation
* No encryption

### Non-working features
* KOP related
* User portrait
* DX Pass
* Tournament mode

### Additional notes
* Previous versions of Aqua reported different endpoint URI for Maimai DX thus required compatible patches. Currently, it doesn't matter and both will work.
* Score cards are saved in the data folder.

## O.N.G.E.K.I

### Required patches
* No TLS
* No certificate pinning
* No URI obfuscation
* No encryption

### Non-working features
* KOP related
* Physical cards

### Additional notes
* Match `game.ongeki.version` key in `application.properties` same as your client version. This applies to Bright Memory version and up.