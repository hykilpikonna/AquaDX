package icu.samnyan.aqua.sega.maimai2.model.request;

import icu.samnyan.aqua.sega.maimai2.model.request.data.UserPortrait;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadUserPortrait implements Serializable {
    private UserPortrait userPortrait;
}
