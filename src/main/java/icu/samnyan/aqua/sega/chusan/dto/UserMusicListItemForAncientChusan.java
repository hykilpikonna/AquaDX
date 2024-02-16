package icu.samnyan.aqua.sega.chusan.dto;

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
public class UserMusicListItemForAncientChusan {
    private int length;
    private List<UserMusicDetailForAncientChusan> userMusicDetailList;
}
