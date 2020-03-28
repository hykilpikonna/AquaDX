package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserItemRepository")
public interface UserItemRepository extends JpaRepository<UserItem, Long> {

    Page<UserItem> findByUser_Card_ExtId(Integer aimeId, Pageable page);

    Optional<UserItem> findByUserAndItemKindAndItemId(UserData userData, int itemKind, int itemId);

    Page<UserItem> findByUser_Card_ExtIdAndItemKind(int userId, int kind, Pageable page);

}
