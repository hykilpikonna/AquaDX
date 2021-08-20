package icu.samnyan.aqua.sega.maimai2.model.request.data;

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
public class UserPhoto implements Serializable {
	private int orderId;
	private long userId;
	private int divNumber;
	private int divLength;
	private String divData;
	private int placeId;
	private String clientId;
	private String uploadDate;
	private long playlogId;
	private int trackNo;
}
