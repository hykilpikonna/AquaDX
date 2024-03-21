package icu.samnyan.aqua.sega.maimai2.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.general.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.userdata.Mai2UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.Mai2UserDataRepo;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2CMGetUserPreviewHandler")
public class CMGetUserPreviewHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(CMGetUserPreviewHandler.class);

    private final BasicMapper mapper;

    private final Mai2UserDataRepo userDataRepository;

    @Autowired
    public CMGetUserPreviewHandler(BasicMapper mapper, Mai2UserDataRepo userDataRepository) {
        this.mapper = mapper;
        this.userDataRepository = userDataRepository;
    }


    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        Long userId = ((Number) request.get("userId")).longValue();
        String segaIdAuthKey = String.valueOf(request.get("segaIdAuthKey"));

        Optional<Mai2UserDetail> userDataOptional = userDataRepository.findByCardExtId(userId);

        if (userDataOptional.isPresent()) {
            Map<String, Object> resultMap = new LinkedHashMap<>();
            Mai2UserDetail user = userDataOptional.get();

            resultMap.put("userName", user.getUserName());
            resultMap.put("rating", user.getPlayerRating());
            resultMap.put("lastDataVersion", "1.20.00"); //Hardcode due to outdated release
            resultMap.put("isLogin", false);
            resultMap.put("isExistSellingCard", false);

            String json = mapper.write(resultMap);
            logger.info("Response: " + json);
            return json;
        }

        return null;
    }
}
