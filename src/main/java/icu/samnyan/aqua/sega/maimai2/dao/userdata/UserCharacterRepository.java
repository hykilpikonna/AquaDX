package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserCharacter;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("Maimai2UserCharacterRepository")
public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {

    List<UserCharacter> findByUser_Card_ExtId(long userId);

    Page<UserCharacter> findByUser_Card_ExtId(long userId, Pageable page);

    Optional<UserCharacter> findByUserAndCharacterId(UserDetail user, int characterId);

    @Transactional
    void deleteByUser(UserDetail user);

}
