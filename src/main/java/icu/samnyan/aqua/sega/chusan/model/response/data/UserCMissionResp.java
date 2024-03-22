package icu.samnyan.aqua.sega.chusan.model.response.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCMissionResp implements Serializable {
    private long userId;
    private int missionId;
    private int point;
    private List<UserCMissionProgressResp> userCMissionProgressList;
}
