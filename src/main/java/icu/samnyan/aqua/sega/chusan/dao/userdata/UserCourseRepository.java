package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.sega.chusan.model.userdata.UserCourse;
import icu.samnyan.aqua.sega.chusan.model.userdata.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("ChusanUserCourseRepository")
public interface UserCourseRepository extends JpaRepository<UserCourse, Long> {
    Optional<UserCourse> findTopByUserAndCourseIdOrderByIdDesc(UserData user, int courseId);

    Page<UserCourse> findByUser_Card_ExtId(Long extId, Pageable page);

    List<UserCourse> findByUser_Card_ExtId(Long extId);
}
