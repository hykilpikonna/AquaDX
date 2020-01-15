package icu.samnyan.aqua.api.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class ApiMapper {

    private final ObjectMapper mapper;

    public ApiMapper() {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        SimpleModule module = new SimpleModule();
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(module);
    }

    public String write(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);

    }

    public <T> T convert(Object object, TypeReference<T> toClass) {
        return mapper.convertValue(object, toClass);
    }
}
