package icu.samnyan.aqua.sega.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class Compression {

    public static byte[] decompress(byte[] src) {
        ByteBuf result = Unpooled.buffer();
        byte[] buffer = new byte[100];
        Inflater decompressor = new Inflater();
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

    public static byte[] compress(byte[] src) {
        ByteBuf result = Unpooled.buffer();
        byte[] buffer = new byte[100];
        Deflater compressor = new Deflater();
        compressor.setInput(src);
        compressor.finish();

        while (!compressor.finished()) {
            int count = compressor.deflate(buffer);
            if (count == 0) {
                break;
            }
            result.writeBytes(buffer, result.readerIndex(), count);
        }
        compressor.end();

        return ByteBufUtil.toBytes(result);
    }
}
