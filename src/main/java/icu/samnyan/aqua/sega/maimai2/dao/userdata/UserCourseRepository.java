package icu.samnyan.aqua.sega.maimai2.dao.userdata;

import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserCourse;

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
@Repository("Maimai2UserCourseRepository")
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {

    List<UserCourse> findByUser_Card_ExtId(long userId);

    Optional<UserCourse> findByUserAndCourseId(UserDetail user, int courseId);

    Page<UserCourse> findByUser_Card_ExtId(long userId, Pageable page);

    @Transactional
    void deleteByUser(UserDetail user);
}
