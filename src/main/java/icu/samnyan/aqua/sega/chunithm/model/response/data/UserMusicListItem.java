package icu.samnyan.aqua.sega.chunithm.model.response.data;

import icu.samnyan.aqua.sega.chunithm.model.userdata.UserMusicDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserMusicListItem {
    private int length;
    private List<UserMusicDetail> userMusicDetailList;
}
