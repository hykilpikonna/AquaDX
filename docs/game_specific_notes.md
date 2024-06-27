# Game specific notes
For best viewing experience, please use a markdown viewer that supports Github or Gitlab Flavored Markdown syntax.

This document is for detailed game specific notes, if any.

## Overview

| Name              | Game ID | Latest supported version | Latest supported option | Actively supported | Requires patch |
|-------------------|---------|--------------------------|-------------------------|--------------------|----------------|
| Chunithm (Chusan) | SDHD    | Luminous                 | A143                    | Yes                | Yes            |
| Chunithm          | SDBT    | Paradise Lost            | A032                    | Yes                | Yes (Paradise) |
| Maimai DX         | SDEZ    | Buddies                  | H061                    | Yes                | Yes            |
| O.N.G.E.K.I       | SDDT    | Bright memory            | A108                    | Yes                | Yes            |
| Card Maker        | SDED    | 1.34                     | A030                    | Yes                | Yes            |
| Maimai            | SDEY    | Finale                   | ?                       | No                 | ?              |
| Project DIVA AFT  | SBZV    | ?                        | ?                       | No                 | ?              |

* Actively supported: if yes, it will likely receive future bug fixes and new version support.
* Requires patch: if yes, game needs to be patched in order to work with Aqua server.
* Latest supported option: this may or may not include all options up to latest.

## Chunithm (Chusan)
Only JP variant is supported.

### Required patches
* No encryption
* For SUN Plus: Please edit `A001/event/event00000015/Event.xml` and change `<alwaysOpen>false</alwaysOpen>` to `true`.

### Non-working features
* Global matching
* Profile migration from Chunithm

### Additional notes
* Match `game.chusan.version` and `game.chusan.rom-version` key in `application.properties` same as your client. If not, online connectivity kill switch will be triggered or some game modes will not work.
* Team function can be enabled by changing `game.chusan.team-name` value. Leave this blank to disable team function.
* Chusan and Chunithm uses different endpoints and tables. Your progress from Chunithm won't carry over to Chusan.
* For user box customization, use Web UI.
* (For New plus or up) Class mode disabled when game set to free play. This is not a server restriction.
* While you can enter global matching mode, actual multiplayer won't work.

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
* Tournament mode
* Chart recommendation (Festival)

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

## Card Maker

### Required patches
* No TLS
* No encryption

### Support status
* Chunithm New: Yes, New (2.00)
* Maimai DX: Yes, Universe (1.20)
* O.N.G.E.K.I: No

### Additional notes
* Only stated version above are supported.
* Server does not consider gacha rarity and probability weight during card draw.
* Server returns same hard-coded serial for each cards. This is intentional behavior.
* Due to its high correlation with every game endpoints, this may cease to work after major game version up.
