package icu.samnyan.aqua.sega.chusan.model.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCMissionProgressResp implements Serializable {
    private int order;
    private int stage;
    private int progress;
}
