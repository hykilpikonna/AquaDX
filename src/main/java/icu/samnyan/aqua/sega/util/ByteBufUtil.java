package icu.samnyan.aqua.sega.util;

import io.netty.buffer.ByteBuf;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class ByteBufUtil {

    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

    /**
     * Read bytes from start to current writer index
     * Not modifying the reader index
     *
     * @param byteBuf The input buffer
     * @return bytes
     */
    public static byte[] toBytes(ByteBuf byteBuf) {

        int readerPos = byteBuf.readerIndex();
        byteBuf.resetReaderIndex();
        byte[] result = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(result);

        byteBuf.readerIndex(readerPos);
        return result;
    }

    /**
     * Move writer index to the capacity and read all the byte to the end
     * Not modifying the reader index
     *
     * @param byteBuf The input buffer
     * @return bytes
     */
    public static byte[] toAllBytes(ByteBuf byteBuf) {

        int readerPos = byteBuf.readerIndex();
        byteBuf.resetReaderIndex();
        byteBuf.writerIndex(byteBuf.capacity());
        byte[] result = new byte[byteBuf.capacity()];
        byteBuf.readBytes(result);

        byteBuf.readerIndex(readerPos);
        return result;
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

}
