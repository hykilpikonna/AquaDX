package icu.samnyan.aqua.sega.chunithm.dao.userdata;

import icu.samnyan.aqua.sega.chunithm.model.userdata.UserData;
import icu.samnyan.aqua.sega.chunithm.model.userdata.UserItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface UserItemRepository extends JpaRepository<UserItem, Long> {

    Optional<UserItem> findTopByUserAndItemIdAndItemKindOrderByIdDesc(UserData user, int itemId, int itemKind);

    Page<UserItem> findAllByUser_Card_ExtIdAndItemKind(int extId, int itemKind, Pageable pageable);

    List<UserItem> findAllByUser_Card_ExtId(int extId);

    Page<UserItem> findByUser_Card_ExtId(int extId, Pageable pageable);
}
