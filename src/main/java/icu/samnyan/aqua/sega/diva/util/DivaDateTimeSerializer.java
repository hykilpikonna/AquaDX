package icu.samnyan.aqua.sega.diva.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class DivaDateTimeSerializer extends StdSerializer<LocalDateTime> {

    public DivaDateTimeSerializer() {
        this(null);
    }

    public DivaDateTimeSerializer(Class<LocalDateTime> t) {
        super(t);
    }

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(DivaDateTimeUtil.getString(value));
    }
}
