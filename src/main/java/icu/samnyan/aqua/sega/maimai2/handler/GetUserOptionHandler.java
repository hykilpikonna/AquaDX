package icu.samnyan.aqua.sega.maimai2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserOptionRepo;
import icu.samnyan.aqua.sega.general.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserOption;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserOptionHandler")
public class GetUserOptionHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserOptionHandler.class);

    private final BasicMapper mapper;

    private final Mai2UserOptionRepo userOptionRepository;

    public GetUserOptionHandler(BasicMapper mapper, Mai2UserOptionRepo userOptionRepository) {
        this.mapper = mapper;
        this.userOptionRepository = userOptionRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();

        UserOption userOption = userOptionRepository.findSingleByUser_Card_ExtId(userId).orElseThrow();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("userOption", userOption);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
