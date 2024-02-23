package icu.samnyan.aqua.sega.aimedb.util;

import icu.samnyan.aqua.sega.util.ByteBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class Encryption {

    private static final SecretKeySpec KEY = new SecretKeySpec("Copyright(C)SEGA".getBytes(StandardCharsets.UTF_8), "AES");

    public static ByteBuf decrypt(ByteBuf src) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, KEY);

        return Unpooled.copiedBuffer(cipher.doFinal(ByteBufUtil.toBytes(src)));
    }

    public static ByteBuf encrypt(ByteBuf src) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, KEY);
        byte[] bytes = cipher.doFinal(ByteBufUtil.toAllBytes(src));
        return Unpooled.copiedBuffer(bytes);
    }
}
