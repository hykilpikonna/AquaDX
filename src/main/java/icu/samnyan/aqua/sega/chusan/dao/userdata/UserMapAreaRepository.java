package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserMapArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface UserMapAreaRepository extends JpaRepository<UserMapArea, Long> {
    List<UserMapArea> findAllByUser(UserData user);

    List<UserMapArea> findAllByUser_Card_ExtId(Long extId);

    Optional<UserMapArea> findTopByUserAndMapAreaIdOrderByIdDesc(UserData user, int mapAreaId);
}
