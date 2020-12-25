package icu.samnyan.aqua.sega.maimai.dao.userdata;

import icu.samnyan.aqua.sega.maimai.model.userdata.UserItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("MaimaiUserItemRepository")
public interface UserItemRepository extends JpaRepository<UserItem, Long> {

    Page<UserItem> findByUser_Card_ExtIdAndItemKind(long userId, int kind, Pageable page);

}
