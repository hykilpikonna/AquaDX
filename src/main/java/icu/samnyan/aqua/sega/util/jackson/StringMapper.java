package icu.samnyan.aqua.sega.util.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonWriteFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class StringMapper {

    private final ObjectMapper mapper;

    public StringMapper() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        module.addSerializer(Boolean.class, new BooleanStringSerializer());
        module.addSerializer(boolean.class, new BooleanStringSerializer());
        mapper = JsonMapper.builder()
                .enable(JsonWriteFeature.WRITE_NUMBERS_AS_STRINGS)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(SerializationFeature.WRITE_ENUMS_USING_INDEX, true)
                .addModule(module)
                .build();
    }

    public String write(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);

    }

    public <T> T convert(Object map, Class<T> toClass) {
        return mapper.convertValue(map, toClass);
    }

    public LinkedHashMap<String, Object> toMap(Object object) {
        return mapper.convertValue(object, new TypeReference<>() {
        });
    }
}
