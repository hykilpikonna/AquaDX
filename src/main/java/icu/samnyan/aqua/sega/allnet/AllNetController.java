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
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static icu.samnyan.aqua.sega.util.AquaConst.DEFAULT_KEYCHIP_ID;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@RestController
public class AllNetController {

    private static final Logger logger = LoggerFactory.getLogger(AllNetController.class);

    private final ObjectMapper mapper = new ObjectMapper();
    private final String HOST_OVERRIDE;
    private final String PORT_OVERRIDE;
    private final boolean MAIMAI2_NO_HTTP;

    public AllNetController(@Value("${allnet.server.host:}") String HOST,
                            @Value("${allnet.server.port:}") String PORT,
                            @Value("${game.maimai2.splash-old-patch:false}") boolean MAIMAI2_NO_HTTP) {
        this.HOST_OVERRIDE = HOST;
        this.PORT_OVERRIDE = PORT;
        this.MAIMAI2_NO_HTTP = MAIMAI2_NO_HTTP;
    }

    @GetMapping("/")
    public void root(HttpServletResponse response) throws IOException {
        response.sendRedirect("web");
    }

    @GetMapping("/sys/test")
    public String selfTest() {
        return "Server running";
    }

    @PostMapping(value = "/sys/servlet/DownloadOrder", produces = "text/plain")
    public String downloadOrder(InputStream dataStream, HttpServletRequest req) throws IOException {
        logger.info("Request: DownloadOrder");
        String resp = "stat=1&uri=null";
        logger.info("Response: " + resp);
        return resp + "\n";
    }

    @PostMapping(value = "/sys/servlet/PowerOn", produces = "text/plain")
    public String powerOn(InputStream dataStream, HttpServletRequest req) throws IOException {

        String localAddr = req.getLocalAddr();
        String localPort = Integer.toString(req.getLocalPort());
        byte[] bytes = dataStream.readAllBytes();
        Map<String, String> reqMap = Decoder.decode(bytes);

        logger.info("Request: PowerOn, " + mapper.writeValueAsString(reqMap));
        // TODO: Verify KeyChip id

        String gameId = reqMap.getOrDefault("game_id", "");
        String ver = reqMap.getOrDefault("ver", "1.0");
        String serial = reqMap.getOrDefault("serial", DEFAULT_KEYCHIP_ID);
        if (serial.equals(DEFAULT_KEYCHIP_ID)) {
            serial = UUID.randomUUID().toString();
        }
        String format_ver = reqMap.getOrDefault("format_ver", "");
        PowerOnResponse resp;
        if (format_ver.startsWith("2")) {
            var now = LocalDateTime.now();
            resp = new PowerOnResponseV2(
                    1,
                    switchUri(localAddr, localPort, gameId, ver, serial),
                    switchHost(localAddr, localPort, gameId),
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
                    switchUri(localAddr, localPort, gameId, ver, serial),
                    switchHost(localAddr, localPort, gameId),
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

    private String switchUri(String localAddr, String localPort, String gameId, String ver, String serial) {
        String addr = HOST_OVERRIDE.equals("") ? localAddr : HOST_OVERRIDE;
        String port = PORT_OVERRIDE.equals("") ? localPort : PORT_OVERRIDE;
        switch (gameId) {
            case "SDBT":
                return "http://" + addr + ":" + port + "/ChuniServlet/" + ver + "/" + serial + "/";
            case "SBZV":
                return "http://" + addr + ":" + port + "/diva/";
            case "SDDT":
                return "http://" + addr + ":" + port + "/OngekiServlet/";
            case "SDEY":
                return "http://" + addr + ":" + port + "/MaimaiServlet/";
            case "SDEZ":
                // Workaround for old splash patch
                if (MAIMAI2_NO_HTTP) {
                    return addr + ":" + port + "/Maimai2Servlet/";
                } else {
                    return "http://" + addr + ":" + port + "/Maimai2Servlet/";
                }
            case "SDHD":
                return "http://" + addr + ":" + port + "/ChusanServlet/";
            default:
                return "http://" + addr + ":" + port + "/";
        }
    }

    private String switchHost(String localAddr, String localPort, String gameId) {
        String addr = HOST_OVERRIDE.equals("") ? localAddr : HOST_OVERRIDE;
        String port = PORT_OVERRIDE.equals("") ? localPort : PORT_OVERRIDE;
        switch (gameId) {
            case "SDDF":
                return addr + ":" + port + "/";
            default:
                return addr;
        }
    }

}

