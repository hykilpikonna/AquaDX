package icu.samnyan.aqua.sega.maimai2.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import icu.samnyan.aqua.sega.maimai2.model.request.data.UserAll;
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
public class UpsertUserAll implements Serializable {
	private long userId;
	private long playlogId;
	@JsonProperty("isEventMode")
	private boolean isEventMode;
	@JsonProperty("isFreePlay")
	private boolean isFreePlay;
	private UserAll upsertUserAll;
}
