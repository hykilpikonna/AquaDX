package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserRival;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author dp (privateamusement@protonmail.com)
 */
@Repository("OngekiUserRivalDataRepository")
public interface UserRivalDataRepository extends JpaRepository<UserRival, Long> {
    List<UserRival> findByUser_Card_ExtId(long userId);

    @Transactional
    void removeByUser_Card_ExtIdAndRivalUserId(long userId,long rivalUserId);

    @Transactional
    void deleteByUser(UserData user);
}
