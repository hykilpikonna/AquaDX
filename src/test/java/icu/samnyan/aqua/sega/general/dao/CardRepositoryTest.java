package icu.samnyan.aqua.sega.general.dao;

import icu.samnyan.aqua.sega.general.model.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author sam_nya (privateamusement@protonmail.com)
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CardRepositoryTest {
    @Autowired
    private CardRepository cardRepository;

    @Test
    void findByExtId_Exists() {
        cardRepository.save(new Card(1, 114514L, "01145141919810000000", LocalDateTime.now(), LocalDateTime.now()));

        var c = cardRepository.findByExtId(114514L);

        assertThat(c).isPresent();
    }

}