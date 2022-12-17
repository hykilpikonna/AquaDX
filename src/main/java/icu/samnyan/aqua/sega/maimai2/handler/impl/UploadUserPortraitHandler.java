package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.request.UploadUserPortrait;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Base64;
import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2UploadUserPortraitHandler")
public class UploadUserPortraitHandler implements BaseHandler {

    private static final Logger logger = LoggerFactory.getLogger(UploadUserPortraitHandler.class);

    private final BasicMapper mapper;

    private final String picSavePath;
    private final boolean enable;
    private final long divMaxLength;

    public UploadUserPortraitHandler(BasicMapper mapper,
                                     @Value("${game.maimai2.userPhoto.enable:true}") boolean enable,
                                     @Value("${game.maimai2.userPhoto.picSavePath:data/userPhoto}") String picSavePath,
                                     @Value("${game.maimai2.userPhoto.divMaxLength:16}") long divMaxLength) {
        this.mapper = mapper;
        this.picSavePath = picSavePath;
        this.enable = enable;
        this.divMaxLength = divMaxLength;

        if (enable) {
            try {
                Files.createDirectories(Paths.get(picSavePath));
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        /*
        Maimai DX sends splited base64 data for one jpeg image.
        So, make a temp file and keep append bytes until last part received.
        If finished, rename it to other name so user can keep save multiple score cards in a single day.
        */

        if (enable) {
            var uploadUserPhoto = mapper.convert(request, UploadUserPortrait.class);
            var userPhoto = uploadUserPhoto.getUserPortrait();

            long userId = userPhoto.getUserId();
            int divNumber = userPhoto.getDivNumber();
            int divLength = userPhoto.getDivLength();
            String divData = userPhoto.getDivData();

            if (divLength > divMaxLength)
                return "{\"returnCode\":-1,\"apiName\":\"com.sega.maimai2servlet.api.UploadUserPortraitApi\"}";

            try {
                var tmp_filename = Paths.get(picSavePath, userId + "-up.tmp");
                if (divNumber == 0)
                    Files.deleteIfExists(tmp_filename);

                byte[] imageData = Base64.getDecoder().decode(divData);
                Files.write(tmp_filename, imageData, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

                logger.info(String.format("received user %d photo data %d/%d", userId, divNumber + 1, divLength));

                if (divNumber == (divLength - 1)) {
                    var filename = Paths.get(picSavePath, userId + "-up.jpg");
                    Files.move(tmp_filename, filename, StandardCopyOption.REPLACE_EXISTING);

                    userPhoto.setDivData("");
                    var userPortaitMetaJson = mapper.write(userPhoto);
                    var json_filename = Paths.get(picSavePath, userId + "-up.json");
                    Files.write(json_filename, userPortaitMetaJson.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING);

                    logger.info(String.format("saved user %d photo data", userId));
                }
            } catch (IOException e) {
                logger.error("Result: User photo save failed", e);
            }
        }
        return "{\"returnCode\":1,\"apiName\":\"com.sega.maimai2servlet.api.UploadUserPortraitApi\"}";
    }
}
