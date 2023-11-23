package icu.samnyan.aqua.sega.chusan.util;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class BooleanStringIntDeserializer extends JsonDeserializer<Boolean> {
    @Override
    public Boolean deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return switch (p.getCurrentToken()) {
            case VALUE_STRING -> p.getText().trim().equals("1") || p.getText().trim().equalsIgnoreCase("true");
            case VALUE_NUMBER_INT -> p.getIntValue() == 1;
            case VALUE_TRUE -> true;
            case VALUE_FALSE -> false;
            default -> throw new UnsupportedOperationException("Cannot deserialize to boolean field");
        };
    }
}
