package icu.samnyan.aqua.sega.maimai2.model.response.data;

import java.util.List;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserRate;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserUdemae;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRating {
	private int rating;
	private List<UserRate> ratingList;
	private List<UserRate> newRatingList;
	private List<UserRate> nextRatingList;
	private List<UserRate> nextNewRatingList;
	private UserUdemae udemae;
}
