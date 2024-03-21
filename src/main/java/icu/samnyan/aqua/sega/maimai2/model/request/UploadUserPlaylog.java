package icu.samnyan.aqua.sega.maimai2.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import icu.samnyan.aqua.sega.maimai2.model.userdata.Mai2UserPlaylog;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadUserPlaylog implements Serializable {
	private long userId;
	private Mai2UserPlaylog userPlaylog;
}
