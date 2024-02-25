package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;

import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserDataRepository;
import icu.samnyan.aqua.sega.maimai2.dao.userdata.UserPlaylogRepository;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.request.UploadUserPlaylog;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserDetail;
import icu.samnyan.aqua.sega.maimai2.model.userdata.UserPlaylog;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2UploadUserPlaylogHandler")
public class UploadUserPlaylogHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(UploadUserPlaylogHandler.class);

    private final BasicMapper mapper;
    private final UserPlaylogRepository userPlaylogRepository;
    private final UserDataRepository userDataRepository;

    public UploadUserPlaylogHandler(UserDataRepository userDataRepository, UserPlaylogRepository userPlaylogRepository, BasicMapper mapper) {
        this.userDataRepository = userDataRepository;
        this.userPlaylogRepository = userPlaylogRepository;
        this.mapper = mapper;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        UploadUserPlaylog uploadUserPlaylog = mapper.convert(request, UploadUserPlaylog.class);

        Optional<UserDetail> userOptional = userDataRepository.findByCardExtId(uploadUserPlaylog.getUserId());

        /*
        Due to how we handle userId, first user playlog can't be saved.
        (sequence order swapped, it sends playlog then user detail)
        It might be possible to fix this with some workaround, but leave it like this at this time.
        */
        if (userOptional.isPresent()) {
            UserDetail userDetail = userOptional.get();

            UserPlaylog userPlaylog = uploadUserPlaylog.getUserPlaylog();

            userPlaylog.setUser(userDetail);
            userPlaylogRepository.save(userPlaylog);
        }

        return "{\"returnCode\":1,\"apiName\":\"com.sega.maimai2servlet.api.UploadUserPlaylogApi\"}";
    }
}
