package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserMap;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserMapRepository")
public interface UserMapRepository extends JpaRepository<UserMap, Long> {

    List<UserMap> findByUser_Card_ExtId(long userId);

    Optional<UserMap> findByUserAndMapId(UserDetail user, int mapId);

    Page<UserMap> findByUser_Card_ExtId(long userId, Pageable page);

    @Transactional
    void deleteByUser(UserDetail user);

}
