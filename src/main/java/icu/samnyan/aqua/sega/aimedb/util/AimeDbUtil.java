package icu.samnyan.aqua.sega.aimedb.util;

import io.netty.buffer.ByteBuf;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class AimeDbUtil {

    public static Map<String, Object> getBaseInfo(ByteBuf in) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("gameId", in.toString(0x000a, 0x000e - 0x000a, StandardCharsets.US_ASCII));
        resultMap.put("keychipId", in.toString(0x0014, 0x001f - 0x0014, StandardCharsets.US_ASCII));
        return resultMap;
    }
}
