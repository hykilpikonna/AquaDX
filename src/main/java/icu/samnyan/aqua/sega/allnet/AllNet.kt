package icu.samnyan.aqua.sega.allnet

import ext.API
import ext.plus
import ext.plusAssign
import ext.toUrl
import icu.samnyan.aqua.sega.util.AquaConst
import icu.samnyan.aqua.sega.util.Decoder.decodeAllNet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@Configuration
@ConfigurationProperties(prefix = "allnet.server")
class AllNetProps {
    var host: String = ""
    var port: Int? = null
    var checkKeychip: Boolean = false

    var placeName: String = ""
    var placeId: String = "123"
    var region0: String = "1"
    var regionName0: String = "W"
    var regionName1: String = "X"
    var regionName2: String = "Y"
    var regionName3: String = "Z"
    var regionCountry: String = "JPN"

    // Java assumes every application.properties as ISO-8859-1 (wtf), so we need to "correctly" convert it to UTF-8
    // More better way to this is to use XML or yaml format as these treated as UTF-8
    // but I rather use hack than breaking backward compatibility.. for now
    // TODO: Fix this
    val normalizedPlaceName: String by lazy {
        String(placeName.toByteArray(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8)
    }

    val map: Map<String, String> by lazy { mapOf(
        "stat" to "1",
        "name" to "",
        "place_id" to placeId,
        "region0" to region0,
        "region_name0" to regionName0,
        "region_name1" to regionName1,
        "region_name2" to regionName2,
        "region_name3" to regionName3,
        "country" to regionCountry,
        "nickname" to normalizedPlaceName,
    ) }
}

@Suppress("HttpUrlsUsage")
@RestController
class AllNet(
    val keychipRepo: KeyChipRepo,
    val props: AllNetProps
) {
    @API("/")
    fun root(response: HttpServletResponse) {
        response.sendRedirect("web")
    }

    @API("/sys/test")
    fun selfTest() = "Server running"

    @API("/naomitest.html")
    fun naomiTest() = "naomi ok"

    @PostMapping("/sys/servlet/DownloadOrder", produces = ["text/plain"])
    fun downloadOrder(dataStream: InputStream, req: HttpServletRequest?): String {
        val bytes = dataStream.readAllBytes()
        val reqMap = decodeAllNet(bytes)

        logger.info("AllNet /DownloadOrder : $reqMap")

        val serial = reqMap["serial"] ?: AquaConst.DEFAULT_KEYCHIP_ID
        val resp = mapOf(
            "stat" to "0",
            "serial" to serial
        )

        logger.info("> Response: $resp")
        return resp.toUrl() + "\n"
    }

    @PostMapping("/sys/servlet/PowerOn", produces = ["text/plain"])
    fun powerOn(dataStream: InputStream, req: HttpServletRequest): String {
        val localAddr = req.localAddr
        val localPort = req.localPort.toString()

        // game_id SDEZ, ver 1.35, serial A0000001234, ip, firm_ver 50000, boot_ver 0000,
        // encode UTF-8, format_ver 3, hops 1， token 2010451813
        val reqMap = decodeAllNet(dataStream.readAllBytes())
        var serial = reqMap["serial"]
        logger.info("AllNet /PowerOn : $reqMap")

        // TODO: Proper keychip authentication
        if (props.checkKeychip) {
            // This will cause an allnet auth bad on client side
            if (serial == null || !keychipRepo.existsByKeychipId(serial)) {
                return "".also { logger.warn("> Rejected: Keychip not found") }
            }
        }

        val gameId = reqMap["game_id"] ?: return "".also { logger.warn("> Rejected: No game_id provided") }
        val ver = reqMap["ver"] ?: "1.0"

        serial = UUID.randomUUID().toString()
        val formatVer = reqMap["format_ver"] ?: ""
        val resp = props.map.toMutableMap() + mapOf(
            "uri" to switchUri(localAddr, localPort, gameId, ver, serial),
            "host" to switchHost(localAddr, localPort, gameId),
        )

        // Different responses for different versions
        if (formatVer.startsWith("2")) {
            val now = LocalDateTime.now()
            resp += mapOf(
                "year" to now.year,
                "month" to now.month.value,
                "day" to now.dayOfMonth,
                "hour" to now.hour,
                "minute" to now.minute,
                "second" to now.second,
                "setting" to "1",
                "timezone" to "+09:00",
                "res_class" to "PowerOnResponseV2"
            ).mapValues { it.value.toString() }
        } else {
            resp += mapOf(
                "allnet_id" to "456",
                "client_timezone" to "+0900",
                "utc_time" to Instant.now().toString().substring(0, 19) + "Z",
                "setting" to "",
                "res_ver" to "3",
                "token" to reqMap["token"]
            ).mapValues { it.value.toString() }
        }

        logger.info("> Response: $resp")
        return resp.toUrl() + "\n"
    }

    private fun switchUri(localAddr: String, localPort: String, gameId: String, ver: String, serial: String): String {
        val addr = props.host.ifBlank { localAddr }
        val port = props.port?.toString() ?: localPort

        return when (gameId) {
            "SDBT" -> "http://$addr:$port/g/chu2/$ver/$serial/"
            "SDHD" -> "http://$addr:$port/g/chu3/$ver/"
            "SBZV" -> "http://$addr:$port/g/diva/"
            "SDDT" -> "http://$addr:$port/g/ongeki/"
            "SDEY" -> "http://$addr:$port/g/mai/"
            "SDEZ" -> "http://$addr:$port/g/mai2/"
            "SDED" -> "http://$addr:$port/g/card/"
            else -> "http://$addr:$port/"
        }
    }

    private fun switchHost(localAddr: String, localPort: String, gameId: String): String {
        val addr = props.host.ifBlank { localAddr }
        val port = props.port?.toString() ?: localPort

        return when (gameId) {
            "SDDF" -> "$addr:$port/"
            else -> addr
        }
    }

    companion object {
        val logger: Logger = LoggerFactory.getLogger(AllNet::class.java)
    }
}

