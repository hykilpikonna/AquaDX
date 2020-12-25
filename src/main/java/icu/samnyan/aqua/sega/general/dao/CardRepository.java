package icu.samnyan.aqua.sega.general.dao;

import icu.samnyan.aqua.sega.general.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Repository("SegaCardRepository")
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByExtId(Long extId);

    Optional<Card> findByLuid(String luid);
}
