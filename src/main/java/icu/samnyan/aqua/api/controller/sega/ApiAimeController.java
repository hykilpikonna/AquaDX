package icu.samnyan.aqua.api.controller.sega;

import icu.samnyan.aqua.sega.general.model.Card;
import icu.samnyan.aqua.sega.general.service.CardService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

/**
 * General Aime actions endpoint
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
@RequestMapping("api/sega/aime")
public class ApiAimeController {

    private final CardService cardService;

    public ApiAimeController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("getByAccessCode")
    public Optional<Card> getByAccessCode(@RequestBody Map<String, String> request) {
        return cardService.getCardByAccessCode(request.get("accessCode").replaceAll("-", "").replaceAll(" ", ""));
    }
}
