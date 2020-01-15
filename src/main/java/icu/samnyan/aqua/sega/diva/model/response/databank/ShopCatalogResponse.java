package icu.samnyan.aqua.sega.diva.model.response.databank;

import icu.samnyan.aqua.sega.diva.model.response.BaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Getter
@Setter
public class ShopCatalogResponse extends BaseResponse {
    private LocalDateTime shp_ctlg_lut;
    private String shp_ctlg;

    public ShopCatalogResponse(String cmd, String req_id, String stat, LocalDateTime shp_ctlg_lut, String shp_ctlg) {
        super(cmd, req_id, stat);
        this.shp_ctlg_lut = shp_ctlg_lut;
        this.shp_ctlg = shp_ctlg;
    }
}
