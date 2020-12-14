package icu.samnyan.aqua.sega.chunithm.model.requet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSetting {
    private String placeId;
    private String clientId;
    private String placeName;
    private String regionId;
    private String regionName;
    private String allNetId;
    private String bordId;
    private String romVersion;
    private String dataVersion;
    private String dumpFileNum;
}
