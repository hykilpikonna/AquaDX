package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserFavoriteRepository")
public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long> {

    List<UserFavorite> findByUser_Card_ExtId(long userId);

    Optional<UserFavorite> findByUserAndItemKind(UserDetail user, int kind);
    //Optional<UserFavorite> findByUserIdAndItemKind(long userId, int kind);

    List<UserFavorite> findByUserIdAndItemKind(long userId, int kind);

    @Transactional
    void deleteByUser(UserDetail user);
}
