package icu.samnyan.aqua.sega.maimai2.model.response.data;

import java.util.List;

import icu.samnyan.aqua.sega.maimai2.model.userdata.Mai2UserAct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserActivity {
    private List<Mai2UserAct> playList;
    private List<Mai2UserAct> musicList;
}
