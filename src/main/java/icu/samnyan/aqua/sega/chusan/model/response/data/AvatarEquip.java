package icu.samnyan.aqua.sega.chusan.model.response.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvatarEquip {

    private int wearID;
    private int headID;
    private int faceID;
    private int skinID;
    private int itemID;
    private int frontID;
    private int backID;
    
}
