package icu.samnyan.aqua.sega.chusan.model.response.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchingWaitState {

    @JsonProperty("isFinish")
    private boolean isFinish = false;
    private int restMSec = 30000;
    private int pollingInterval = 10;
    private List<MatchingMemberInfo> matchingMemberInfoList = new ArrayList<>();
    
}
