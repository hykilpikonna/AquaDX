package icu.samnyan.aqua.sega.maimai2.model.response.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRivalMusic {
    private int musicId;
    private List<UserRivalMusicDetail> userRivalMusicDetailList;
}
