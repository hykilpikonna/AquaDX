package icu.samnyan.aqua.sega.maimai.dao.userdata;

import icu.samnyan.aqua.sega.maimai.model.userdata.UserPresentEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiUserPresentEventRepository")
public interface UserPresentEventRepository extends JpaRepository<UserPresentEvent, Long> {

    List<UserPresentEvent> findByUser_Card_ExtId(long userId);

}
