package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserRate;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserRateRepository")
public interface UserRateRepository extends JpaRepository<UserRate, Long> {

    Optional<UserRate> findByUserAndMusicId(UserDetail user, int musicId);

    List<UserRate> findByUser_Card_ExtId(long userId);

}
