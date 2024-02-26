package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.net.utils.GenericUserDataRepo;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import icu.samnyan.aqua.sega.general.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanUserDataRepository")
public interface UserDataRepository extends JpaRepository<UserData, Long>, GenericUserDataRepo {

    UserData findByCard(Card card);

    Optional<UserData> findByCard_ExtId(Long extId);

    @Override
    @Query("select count(*) from Maimai2UserData where playerRating > :rating")
    long getRanking(int rating);
}
