package icu.samnyan.aqua.sega.maimai2.model.request;

import icu.samnyan.aqua.sega.maimai2.model.request.data.UserPhoto;
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
public class UploadUserPhoto implements Serializable {
	private UserPhoto userPhoto;
}
