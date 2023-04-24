package icu.samnyan.aqua.sega.allnet.dao.keychip;

import icu.samnyan.aqua.sega.allnet.model.Keychip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("KeyChipRepository")
public interface KeyChipRepository extends JpaRepository<Keychip, Long> {
    @Query(value = "select 1 from allnet_keychips where keychip_id = ?1 limit 1", nativeQuery = true)
    Optional<Integer> findKeyChipExistence(String KeychipId);
}
