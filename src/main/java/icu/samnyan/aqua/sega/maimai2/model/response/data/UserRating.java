package icu.samnyan.aqua.sega.maimai2.model.response.data;

import java.util.List;

import icu.samnyan.aqua.sega.maimai2.model.userdata.Mai2UserRate;
import icu.samnyan.aqua.sega.maimai2.model.userdata.Mai2UserUdemae;
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
	private List<Mai2UserRate> ratingList;
	private List<Mai2UserRate> newRatingList;
	private List<Mai2UserRate> nextRatingList;
	private List<Mai2UserRate> nextNewRatingList;
	private Mai2UserUdemae udemae;
}
