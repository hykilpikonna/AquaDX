package icu.samnyan.aqua.sega.general.dao;

import icu.samnyan.aqua.sega.general.model.PropertyEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository
public interface PropertyEntryRepository extends JpaRepository<PropertyEntry, Long> {
    Optional<PropertyEntry> findByPropertyKey(String key);
}
