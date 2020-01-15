package icu.samnyan.aqua.sega.util.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class BooleanStringSerializer extends StdSerializer<Boolean> {

    public BooleanStringSerializer() {
        this(null);
    }

    public BooleanStringSerializer(Class<Boolean> t) {
        super(t);
    }

    @Override
    public void serialize(Boolean value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.toString());
    }
}
