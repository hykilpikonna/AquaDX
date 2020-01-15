package icu.samnyan.aqua.api.controller;

import icu.samnyan.aqua.sega.general.dao.CardRepository;
import icu.samnyan.aqua.sega.general.model.Card;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/sega/aime")
public class ApiAimeController {

    private final CardRepository cardRepository;

    public ApiAimeController(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostMapping("getByAccessCode")
    public Optional<Card> getByAccessCode(@RequestBody Map<String, String> request) {
        return cardRepository.findByLuid(request.get("accessCode"));
    }
}
