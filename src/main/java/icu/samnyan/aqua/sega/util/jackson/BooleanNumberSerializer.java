package icu.samnyan.aqua.sega.util.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class BooleanNumberSerializer extends StdSerializer<Boolean> {

    public BooleanNumberSerializer() {
        this(null);
    }

    public BooleanNumberSerializer(Class<Boolean> t) {
        super(t);
    }

    @Override
    public void serialize(Boolean value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeNumber(value ? 1 : 0);
    }
}
