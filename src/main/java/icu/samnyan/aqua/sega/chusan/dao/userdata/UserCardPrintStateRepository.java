package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.sega.chusan.model.userdata.UserCardPrintState;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanUserCardPrintStateRepository")
public interface UserCardPrintStateRepository extends JpaRepository<UserCardPrintState, Long> {
    List<UserCardPrintState> findByUser_Card_ExtId(Long extId);

    List<UserCardPrintState> findByUser_Card_ExtIdAndHasCompleted(Long extId, boolean hasCompleted);

    List<UserCardPrintState> findByUserAndGachaIdAndHasCompleted(UserData userData, int gachaId, boolean hasCompleted);
}
