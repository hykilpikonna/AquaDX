package icu.samnyan.aqua.sega.aimedb.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import icu.samnyan.aqua.sega.util.jackson.ByteBufSerializer;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class LogMapper {

    private final ObjectMapper mapper;

    public LogMapper() {
        mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(ByteBuf.class, new ByteBufSerializer());
        mapper.registerModule(module);
    }

    public String write(Object o) throws JsonProcessingException {
        return mapper.writeValueAsString(o);
    }
}
