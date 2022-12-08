package icu.samnyan.aqua.sega.maimai2.model.request;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserPrintDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpsertUserPrint implements Serializable {
	private long userId;
	private long orderId;
	private Map<String, Object> userPrintReserve;
	private UserPrintDetail userPrintDetail;
}
