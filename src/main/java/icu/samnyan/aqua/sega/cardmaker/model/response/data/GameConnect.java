package icu.samnyan.aqua.sega.cardmaker.model.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameConnect {
    private int modelKind; // 0: chunithm, 1: maimai, 2: ongeki
    private int type; // 0: LAN, 1: WAN
    private String titleUri;
}
