package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserTradeItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserTradeItemRepository")
public interface UserTradeItemRepository extends JpaRepository<UserTradeItem, Long> {

    List<UserTradeItem> findByUser_Card_ExtId(long userId);

    List<UserTradeItem> findByUser_Card_ExtIdAndChapterIdGreaterThanEqualAndChapterIdLessThanEqual(long userId, int startChapterId, int endChapterId);

    Optional<UserTradeItem> findByUserAndChapterIdAndTradeItemId(UserData userData, int chapterId, int tradeItemId);

    @Transactional
    void deleteByUser(UserData user);
}
