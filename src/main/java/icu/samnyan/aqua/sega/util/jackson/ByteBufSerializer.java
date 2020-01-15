package icu.samnyan.aqua.sega.util.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

import java.io.IOException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class ByteBufSerializer extends StdSerializer<ByteBuf> {

    public ByteBufSerializer() {
        this(null);
    }

    public ByteBufSerializer(Class<ByteBuf> t) {
        super(t);
    }

    @Override
    public void serialize(ByteBuf value, JsonGenerator gen, SerializerProvider provider) throws IOException {

        gen.writeString(ByteBufUtil.hexDump(value));

    }
}
