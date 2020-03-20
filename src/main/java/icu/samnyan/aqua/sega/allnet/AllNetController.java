package icu.samnyan.aqua.sega.allnet;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.samnyan.aqua.sega.allnet.model.response.PowerOnResponse;
import icu.samnyan.aqua.sega.allnet.util.Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.util.Map;
import java.util.zip.DataFormatException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
public class AllNetController {

    private static final Logger logger = LoggerFactory.getLogger(AllNetController.class);

    private final String HOST;
    private final String PORT;

    public AllNetController(@Value("${allnet.server.host}") String HOST,
                            @Value("${allnet.server.port}") String PORT) {
        this.HOST = HOST;
        this.PORT = PORT;
    }

    @PostMapping(value = "/sys/servlet/PowerOn", produces = "text/plain")
    String powerOn(InputStream dataStream) throws DataFormatException, IOException {

        byte[] bytes = dataStream.readAllBytes();
        Map<String, String> reqMap = Decoder.decode(bytes);

        logger.info("Request: PowerOn, " + new ObjectMapper().writeValueAsString(reqMap));
        // TODO: Verify KeyChip id

        String gameId = reqMap.getOrDefault("game_id", "");
        PowerOnResponse resp = new PowerOnResponse(
                1,
                switchUri(gameId),
                switchHost(gameId),
                "123",
                "",
                "",
                "1",
                "W",
                "X",
                "Y",
                "Z",
                "JPN",
                "456",
                "+0900",
                Instant.now().toString().substring(0, 19).concat("Z"),
                "",
                "3",
                reqMap.get("token")
        );
        logger.info("Response: " + new ObjectMapper().writeValueAsString(resp));
        return resp.toString().concat("\n");
    }

    private String switchUri(String gameId) {
        switch (gameId) {
            case "SDBT":
                return "http://" + HOST + ":" + PORT + "/";
            case "SBZV":
                return "http://" + HOST + ":" + PORT + "/diva/";
            default:
                return "http://" + HOST + ":" + PORT + "/ongeki/";
        }
    }

    private String switchHost(String gameId) {
        switch (gameId) {
            case "SDDF":
                return HOST + ":" + PORT + "/";
            default:
                return HOST;
        }
    }

}

