package icu.samnyan.aqua.util;

import icu.samnyan.aqua.sega.general.model.Card;

import java.time.LocalDateTime;

/**
 * @author sam_nya (privateamusement@protonmail.com)
 */
public class CardHelper {

    public static Card getCard() {
        var now = LocalDateTime.now();
        return new Card(1L, 114514L, "01145141919810000000", now, now);
    }

}
