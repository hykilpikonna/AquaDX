package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.request.UploadUserPhoto;
import icu.samnyan.aqua.sega.maimai2.model.request.data.UserPhoto;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class UploadUserPhotoHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(UploadUserPhotoHandler.class);

    private final BasicMapper mapper;

    public UploadUserPhotoHandler(BasicMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        /*
        Maimai DX sends splited base64 data for one jpeg image.
        So, make a temp file and keep append bytes until last part received.
        If finished, rename it to other name so user can keep save multiple score cards in a single day.
        */

        UploadUserPhoto uploadUserPhoto = mapper.convert(request, UploadUserPhoto.class);
        UserPhoto userPhoto = uploadUserPhoto.getUserPhoto();

        long userId = userPhoto.getUserId();
        int trackNo = userPhoto.getTrackNo();

        int divNumber = userPhoto.getDivNumber();
        int divLength = userPhoto.getDivLength();
        String divData = userPhoto.getDivData();

        try {
            String tmp_filename = userId + "-" + trackNo + ".tmp";
            byte[] imageData = Base64.getDecoder().decode(divData);
            Files.write(Paths.get("data/" + tmp_filename), imageData, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

            if (divNumber == (divLength - 1)) {
                String filename = userId + "-" + LocalDateTime.now().toEpochSecond(ZoneOffset.UTC) + ".jpg";
                Files.move(Paths.get("data/" + tmp_filename), Paths.get("data/" + filename));
            }
        } catch (IOException e) {
            logger.error("Result: User photo save failed", e);
        }

        logger.info("Result: User photo saved");

        return "{\"returnCode\":1,\"apiName\":\"com.sega.maimai2servlet.api.UploadUserPhotoApi\"}";
    }
}
