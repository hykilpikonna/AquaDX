package icu.samnyan.aqua.sega.chusan.dao.userdata;

import icu.samnyan.aqua.sega.chusan.model.userdata.UserCMissionProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ChusanUserCMissionProgressRepository")
public interface UserCMissionProgressRepository extends JpaRepository<UserCMissionProgress, Long> {
    List<UserCMissionProgress> findByUser_Card_ExtIdAndMissionId(Long extId, int missionId);

    Optional<UserCMissionProgress> findByUser_Card_ExtIdAndMissionIdAndOrder(Long extId, int missionId, int order);
}