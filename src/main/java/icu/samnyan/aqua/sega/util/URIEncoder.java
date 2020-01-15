package icu.samnyan.aqua.sega.util;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class URIEncoder {

    public static String encode(String str) {
        return URLEncoder.encode(str, StandardCharsets.UTF_8).replaceAll("\\+", "%20");
    }
}
