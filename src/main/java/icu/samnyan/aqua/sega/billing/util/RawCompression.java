package icu.samnyan.aqua.sega.billing.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import icu.samnyan.aqua.sega.util.ByteBufUtil;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class RawCompression {

    public static byte[] decompress(byte[] src) {
        ByteBuf result = Unpooled.buffer();
        byte[] buffer = new byte[100];
        Inflater decompressor = new Inflater(true); // Enable no wrap option
        decompressor.setInput(src);

        try {
            while (!decompressor.finished()) {
                int count = decompressor.inflate(buffer);
                if (count == 0) {
                    break;
                }
                result.writeBytes(buffer, result.readerIndex(), count);
            }
            decompressor.end();

            return ByteBufUtil.toBytes(result);
        } catch (DataFormatException e) {
            e.printStackTrace();
            return new byte[0];
        }

    }
    
}
