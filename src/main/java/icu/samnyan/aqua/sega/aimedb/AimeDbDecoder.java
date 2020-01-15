package icu.samnyan.aqua.sega.aimedb;

import icu.samnyan.aqua.sega.aimedb.exception.InvalidRequestException;
import icu.samnyan.aqua.sega.aimedb.util.Encryption;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AimeDbDecoder extends ByteToMessageDecoder {

    private static final Logger logger = LoggerFactory.getLogger(AimeDbDecoder.class);

    private int length = 0;

    /**
     * Decrypt the incoming request including frame management
     *
     * @param ctx
     * @param in
     * @param out
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 16) {
            return;
        }
        if (length == 0) {
            length = getLength(in);
        }

        if (in.readableBytes() < length) {
            return;
        }

        // Create a byte array to store the encrypted data
        ByteBuf result = Encryption.decrypt(in.readBytes(length));

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("type", ((Short) result.getShortLE(0x04)).intValue());
        resultMap.put("data", result);

        logger.debug("Aime Server Request Type: " + resultMap.get("type"));

        out.add(resultMap);
    }

    /**
     * Get the length from request
     *
     * @param in the request
     * @return int the length of this request
     * @throws InvalidRequestException
     * @throws IllegalBlockSizeException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     */
    private int getLength(ByteBuf in) throws InvalidRequestException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        int currentPos = in.readerIndex();
        ByteBuf result = Encryption.decrypt(in);
        // Check the header
        if (result.getByte(0) != 0x3e) {
            throw new InvalidRequestException();
        }

        // Read the length from offset 6

        return result.getShortLE(currentPos + 6);
    }
}
