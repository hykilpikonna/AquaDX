package icu.samnyan.aqua.sega.maimai2.model.userdata;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserGhost {
	private String name;
	private int iconId;
	private int plateId;
	private int titleId;
	private int rate;
	private int udemaeRate;
	private String playDatetime;
	private int shopId;
	private int regionCode;
	private int typeId;
	private int musicId;
	private int difficulty;
	private int version;
	private List<Byte> resultBitList;
	private int resultNum;
	private int achievement;
}
