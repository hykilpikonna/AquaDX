package icu.samnyan.aqua.sega.util.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import icu.samnyan.aqua.sega.general.model.Card;

import java.io.IOException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class AccessCodeSerializer extends StdSerializer<Card> {

    public AccessCodeSerializer() {
        this(null);
    }

    public AccessCodeSerializer(Class<Card> t) {
        super(t);
    }

    @Override
    public void serialize(Card value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(value.getLuid());
    }
}
