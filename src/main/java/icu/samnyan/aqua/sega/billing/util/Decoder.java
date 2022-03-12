package icu.samnyan.aqua.sega.billing.util;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class Decoder {

    public static Map<String, String> decode(byte[] src) {
        //byte[] bytes = Base64.getMimeDecoder().decode(src);

        byte[] output = RawCompression.decompress(src);

        String outputString = new String(output, StandardCharsets.UTF_8).trim();
        String[] split = outputString.split("&");
        Map<String, String> resultMap = new HashMap<>();
        for (String s :
                split) {
            String[] kv = s.split("=");
            resultMap.put(kv[0], kv[1]);
        }

        return resultMap;
    }
}
