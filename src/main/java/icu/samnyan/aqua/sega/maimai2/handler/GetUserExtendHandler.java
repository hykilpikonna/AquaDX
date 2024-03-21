package icu.samnyan.aqua.sega.maimai2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserExtendRepo;
import icu.samnyan.aqua.sega.general.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.userdata.Mai2UserExtend;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserExtendHandler")
public class GetUserExtendHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(GetUserExtendHandler.class);

    private final BasicMapper mapper;

    private final Mai2UserExtendRepo userExtendRepository;

    public GetUserExtendHandler(BasicMapper mapper, Mai2UserExtendRepo userExtendRepository) {
        this.mapper = mapper;
        this.userExtendRepository = userExtendRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();

        Mai2UserExtend userExtend = userExtendRepository.findSingleByUser_Card_ExtId(userId).orElseThrow();

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("userId", userId);
        resultMap.put("userExtend", userExtend);

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
