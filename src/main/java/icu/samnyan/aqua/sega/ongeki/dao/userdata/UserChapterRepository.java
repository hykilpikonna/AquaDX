package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserChapter;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserChapterRepository")
public interface UserChapterRepository extends JpaRepository<UserChapter, Long> {

    List<UserChapter> findByUser_Card_ExtId(int userId);

    Optional<UserChapter> findByUserAndChapterId(UserData userData, int chapterId);

}
