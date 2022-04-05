# Chusan dev notes

## Item types
| ItemKind |       Name        | Single / Multiple |             Notes             |
|----------|-------------------|-------------------|-------------------------------|
|    1     |  Nameplate        |      Single       |               -               |
|    2     |  Frame            |      Single       |               -               |
|    3     |  Trophy           |      Single       |               -               |
|    4     |  Skill            |      Multiple     |     Stock is level of skill   |
|    5     |  Ticket           |      Multiple     |               -               |
|    6     |  Present          |      Multiple?    |               -               |
|    7     |  Music (Unlock)   |      Single       |               -               |
|    8     |  Map Icon         |      Single       |               -               |
|    9     |  System Voice     |      Single       |               -               |
|    10    |  Symbol Chat      |      Single       |               -               |
|    11    |  Avatar Accessory |      Single       |Part can determined by category|

## Avatar accessory category
|  Category ID  | Part name |
|---------------|-----------|
|       1       |    Wear   |
|       2       |    Head   |
|       3       |    Face   |
|       4       |    Skin   |
|       5       |    Item   |
|       6       |    Front  |
|       7       |    Back   |

## National matching
- Can be disabled dynamically on GetGameSettingHandler
- NAT hole punching, direct connection between cab to cab?
- Session management required
- Fallback to CPU competitors if conditions met
- ReflectorUri, Related?