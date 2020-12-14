package icu.samnyan.aqua.sega.allnet;

import com.fasterxml.jackson.databind.ObjectMapper;
import icu.samnyan.aqua.sega.allnet.model.response.PowerOnResponse;
import icu.samnyan.aqua.sega.allnet.model.response.PowerOnResponseV2;
import icu.samnyan.aqua.sega.allnet.model.response.PowerOnResponseV3;
import icu.samnyan.aqua.sega.allnet.util.Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.zip.DataFormatException;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
public class AllNetController {

    private static final Logger logger = LoggerFactory.getLogger(AllNetController.class);

    private final ObjectMapper mapper = new ObjectMapper();
    private final String HOST;
    private final String PORT;

    public AllNetController(@Value("${allnet.server.host}") String HOST,
                            @Value("${allnet.server.port}") String PORT) {
        this.HOST = HOST;
        this.PORT = PORT;
    }

    @GetMapping("/")
    public String root() {
        return "Server running";
    }

    @GetMapping("/sys/test")
    public String selfTest() {
        return "Server running";
    }

    @PostMapping(value = "/sys/servlet/PowerOn", produces = "text/plain")
    public String powerOn(InputStream dataStream) throws IOException {

        byte[] bytes = dataStream.readAllBytes();
        Map<String, String> reqMap = Decoder.decode(bytes);

        logger.info("Request: PowerOn, " + new ObjectMapper().writeValueAsString(reqMap));
        // TODO: Verify KeyChip id

        String gameId = reqMap.getOrDefault("game_id", "");
        String format_ver = reqMap.getOrDefault("format_ver", "");
        PowerOnResponse resp;
        if (format_ver.startsWith("2")) {
            var now = LocalDateTime.now();
            resp = new PowerOnResponseV2(
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
                    now.getYear(),
                    now.getMonth().getValue(),
                    now.getDayOfMonth(),
                    now.getHour(),
                    now.getMinute(),
                    now.getSecond(),
                    "1",
                    "+09:00",
                    "PowerOnResponseV2"
            );
        } else {
            resp = new PowerOnResponseV3(
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
        }
        logger.info("Response: " + mapper.writeValueAsString(resp));
        return resp.toString().concat("\n");
    }

    private String switchUri(String gameId) {
        switch (gameId) {
            case "SDBT":
                return "http://" + HOST + ":" + PORT + "/";
            case "SBZV":
                return "http://" + HOST + ":" + PORT + "/diva/";
            case "SDDT":
                return "http://" + HOST + ":" + PORT + "/OngekiServlet/";
            default:
                return "http://" + HOST + ":" + PORT + "/";
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

