package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;

import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserCardRepository;
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserDataRepository;
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserPrintDetailRepository;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.request.UpsertUserPrint;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserCard;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserPrintDetail;
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
@Component("Maimai2UpsertUserPrintHandler")
public class UpsertUserPrintHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(UpsertUserPrintHandler.class);
    private final BasicMapper mapper;

    private final UserCardRepository userCardRepository;
    private final UserPrintDetailRepository userPrintDetailRepository;
    private final UserDataRepository userDataRepository;

    @Autowired
    public UpsertUserPrintHandler(BasicMapper mapper, UserPrintDetailRepository userPrintDetailRepository, UserCardRepository userCardRepository, UserDataRepository userDataRepository) {
        this.mapper = mapper;
        this.userPrintDetailRepository = userPrintDetailRepository;
        this.userCardRepository = userCardRepository;
        this.userDataRepository = userDataRepository;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        long userId = ((Number) request.get("userId")).longValue();

        UserDetail userData;

        Optional<UserDetail> userOptional = userDataRepository.findByCard_ExtId(userId);
        if (userOptional.isPresent()) {
            userData = userOptional.get();
        } else {
            logger.error("User not found. userId: {}", userId);
            return null;
        }

        UpsertUserPrint upsertUserPrint = mapper.convert(request, UpsertUserPrint.class);

        UserPrintDetail userPrintDetail = upsertUserPrint.getUserPrintDetail();
        UserCard newUserCard = userPrintDetail.getUserCard();

        newUserCard.setUser(userData);
        userPrintDetail.setUser(userData);

        newUserCard.setStartDate("2019-01-01 00:00:00.000000");
        newUserCard.setEndDate("2029-01-01 00:00:00.000000");
        userPrintDetail.setSerialId("FAKECARDIMAG12345678");

        Optional<UserCard> userCardOptional = userCardRepository.findByUserAndCardId(newUserCard.getUser(), newUserCard.getCardId());
        if (userCardOptional.isPresent()) {
            UserCard userCard = userCardOptional.get();
            newUserCard.setId(userCard.getId());
        }

        userCardRepository.save(newUserCard);
        userPrintDetailRepository.save(userPrintDetail);

        Map<String, Object> resultMap = new LinkedHashMap<>();
        resultMap.put("returnCode", 1);
        resultMap.put("orderId", 0);
        resultMap.put("serialId", "FAKECARDIMAG12345678");
        resultMap.put("startDate", "2019-01-01 00:00:00.000000");
        resultMap.put("endDate", "2029-01-01 00:00:00.000000");

        String json = mapper.write(resultMap);
        logger.info("Response: " + json);
        return json;
    }
}
