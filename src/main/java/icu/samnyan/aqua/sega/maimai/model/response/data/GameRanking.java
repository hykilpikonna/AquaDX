package icu.samnyan.aqua.sega.maimai.model.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GameRanking {
    private Integer id;
    private Integer point;
    private String userName;
}
