package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.sega.chusan.model.userdata.UserCMission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("ChusanUserCMissionRepository")
public interface UserCMissionRepository extends JpaRepository<UserCMission, Long> {
    Optional<UserCMission> findByUser_Card_ExtIdAndMissionId(Long extId, int missionId);
}
