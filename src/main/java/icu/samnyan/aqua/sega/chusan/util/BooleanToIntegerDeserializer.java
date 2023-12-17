package icu.samnyan.aqua.sega.chusan.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.Locale;

public class BooleanToIntegerDeserializer extends JsonDeserializer<Integer> {
    @Override
    public Integer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return switch (p.getCurrentToken()) {
            case VALUE_STRING -> {
                String value = p.getValueAsString();
                if (value.toLowerCase(Locale.ROOT).equals("true")) {
                    yield 1;
                } else if (value.toLowerCase(Locale.ROOT).equals("false")) {
                    yield 0;
                } else {
                    try {
                        yield Integer.parseInt(value);
                    } catch (NumberFormatException e) {
                        throw new UnsupportedOperationException("Cannot deserialize to integer field");
                    }
                }
            }
            case VALUE_NUMBER_INT -> p.getIntValue();
            case VALUE_TRUE -> 1;
            case VALUE_FALSE -> 0;
            default -> throw new UnsupportedOperationException("Cannot deserialize to integer field");
        };
    }
}
