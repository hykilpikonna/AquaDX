package icu.samnyan.aqua.sega.cardmaker.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CodeResp {
    private int returnCode;
    private String apiName;
}
