package icu.samnyan.aqua.sega.allnet.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadOrderResponse {
    private int stat;
    private String serial;
    //private String uri;

    // Set uri to null: app code 300, option code 100 with http download error
    // Exclude uri key or set stat to 0: both code 100 (current)
    @Override
    public String toString() {
        return "stat=" + stat +
        "&serial=" + serial;
    }
}
