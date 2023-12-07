package icu.samnyan.aqua.api.model.resp.sega.maimai2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhotoResp {
    private int imageIndex;
    private int totalImage;
    private String fileName;
    private String divData;
}
