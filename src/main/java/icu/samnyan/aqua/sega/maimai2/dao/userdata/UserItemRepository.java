package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserItem;

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
@Repository("Maimai2UserItemRepository")
public interface UserItemRepository extends JpaRepository<UserItem, Long> {

    List<UserItem> findByUser_Card_ExtId(long userId);

    Page<UserItem> findByUser_Card_ExtId(long userId, Pageable page);

    Optional<UserItem> findByUserAndItemKindAndItemId(UserDetail user, int itemKind, int itemId);

    Page<UserItem> findByUser_Card_ExtIdAndItemKind(long userId, int kind, Pageable page);

    @Transactional
    void deleteByUser(UserDetail user);

}
