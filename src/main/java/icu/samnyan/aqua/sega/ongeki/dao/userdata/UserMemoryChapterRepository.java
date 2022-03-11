package icu.samnyan.aqua.sega.ongeki.dao.userdata;

import icu.samnyan.aqua.sega.ongeki.model.userdata.UserMemoryChapter;
import icu.samnyan.aqua.sega.ongeki.model.userdata.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("OngekiUserMemoryChapterRepository")
public interface UserMemoryChapterRepository extends JpaRepository<UserMemoryChapter, Long> {

    List<UserMemoryChapter> findByUser_Card_ExtId(long userId);

    Optional<UserMemoryChapter> findByUserAndChapterId(UserData userData, int chapterId);

    @Transactional
    void deleteByUser(UserData user);
}
