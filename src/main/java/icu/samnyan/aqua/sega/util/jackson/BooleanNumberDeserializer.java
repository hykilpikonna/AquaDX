package icu.samnyan.aqua.sega.util.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
public class BooleanNumberDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return !"0".equals(p.getText());
    }

}
