package icu.samnyan.aqua.util;

import icu.samnyan.aqua.sega.general.model.Card;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @author sam_nya (privateamusement@protonmail.com)
 */
public class CardHelper {
    private static Random rand = new Random();

    public static Card getCard() {
        var now = LocalDateTime.now();
        return new Card(1L, 114514L, "01145141919810000000", now, now, null);
    }

    public static Card getRandomCard() {
        var now = LocalDateTime.now();

        var luid = "";
        for (int i = 0; i < "01145141919810028570".length(); i++)
            luid += rand.nextInt(10);

        var extId = 0L;
        for (int i = 0; i < "114514".length(); i++)
            extId = extId * 10 + rand.nextInt(10);

        return new Card(0, extId, luid, now, now, null);
    }
}
