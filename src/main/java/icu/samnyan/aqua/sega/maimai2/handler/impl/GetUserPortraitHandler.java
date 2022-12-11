package icu.samnyan.aqua.sega.maimai2.handler.impl;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonProcessingException;
import icu.samnyan.aqua.sega.maimai2.handler.BaseHandler;
import icu.samnyan.aqua.sega.maimai2.model.request.UploadUserPortrait;
import icu.samnyan.aqua.sega.maimai2.model.request.data.UserPortrait;
import icu.samnyan.aqua.sega.util.jackson.BasicMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component("Maimai2GetUserPortraitHandler")
public class GetUserPortraitHandler implements BaseHandler {
    private static final Logger logger = LoggerFactory.getLogger(GetUserPortraitHandler.class);

    private final BasicMapper mapper;
    private final String picSavePath;
    private final boolean enable;

    public GetUserPortraitHandler(BasicMapper mapper, @Value("${game.maimai2.userPhoto.enable:}") boolean enable, @Value("${game.maimai2.userPhoto.picSavePath:}") String picSavePath) {
        this.mapper = mapper;
        this.picSavePath = picSavePath;
        this.enable = enable;

        if (enable) {
            try {
                Files.createDirectories(Paths.get(picSavePath));
            } catch (Exception ignored) {
            }
        }
    }

    @Override
    public String handle(Map<String, Object> request) throws JsonProcessingException {
        if (enable) {
            var userId = ((Number) request.get("userId")).longValue();
            var list = new ArrayList<UserPortrait>();

            try {
                var filePath = Paths.get(picSavePath, userId + "-up.jpg");

                var templateJsonStr = Files.readString(Paths.get(picSavePath, userId + "-up.json"));
                var templateUserPortrait = mapper.read(templateJsonStr, UserPortrait.class);

                var buffer = new byte[10240];

                if (Files.exists(filePath)) {
                    var stream = new FileInputStream(filePath.toFile());
                    while (stream.available() > 0) {
                        var read = stream.read(buffer, 0, 10240);

                        var encodeBuffer = read == 10240 ? buffer : Arrays.copyOfRange(buffer, 0, read);

                        var userPortrait = new UserPortrait();

                        userPortrait.setFileName(templateUserPortrait.getFileName());
                        userPortrait.setPlaceId(templateUserPortrait.getPlaceId());
                        userPortrait.setUserId(templateUserPortrait.getUserId());
                        userPortrait.setClientId(templateUserPortrait.getClientId());
                        userPortrait.setUploadDate(templateUserPortrait.getUploadDate());
                        userPortrait.setDivData(Utf8.decode(Base64.getEncoder().encode(encodeBuffer)));

                        userPortrait.setDivNumber(list.size());

                        list.add(userPortrait);
                    }

                    stream.close();
                    for (var i = 0; i < list.size(); i++) {
                        var userPortrait = list.get(i);
                        userPortrait.setDivLength(list.size());
                    }

                    var map = new HashMap<String, Object>();
                    map.put("length", list.size());
                    map.put("userPortraitList", list);

                    var respJson = mapper.write(map);
                    return respJson;
                }
            } catch (Exception e) {
                logger.error("Result: User photo save failed", e);
            }
        }

        return "{\"length\":0,\"userPortraitList\":[]}";
    }
}
