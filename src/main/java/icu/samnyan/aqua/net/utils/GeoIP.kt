package icu.samnyan.aqua.net.utils

import com.maxmind.geoip2.DatabaseReader
import ext.Bool
import ext.Str
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Service
import java.io.File
import java.net.InetAddress
import java.net.URL
import java.nio.file.Files


@Configuration
@ConfigurationProperties(prefix = "aqua-net.geoip")
class GeoIPProperties {
    var geoLitePath: Str = "data/GeoLite2-Country.mmdb"
    var ipHeader: Str = ""
}

@Service
class GeoIP(
    val props: GeoIPProperties
) {
    val log = LoggerFactory.getLogger(GeoIP::class.java)!!
    lateinit var geoLite: DatabaseReader

    @PostConstruct
    fun onLoad() {
        // Check path exists
        if (!File(props.geoLitePath).exists()) {
            log.error("GeoIP Service is enabled but GeoLite2 database is not found, trying to download from GitHub.")

            // Download from GitHub
            try {
                URL("https://github.com/P3TERX/GeoLite.mmdb/raw/download/GeoLite2-Country.mmdb").openStream()
                    .use { Files.copy(it, File(props.geoLitePath).toPath()) }
            } catch (e: Exception) {
                log.error("Failed to download GeoLite2 database", e)
                throw e
            }
        }

        geoLite = DatabaseReader.Builder(File(props.geoLitePath)).build()
        selfTest()
        log.info("GeoIP Service Enabled")
    }

    /**
     * Test the connection of the GeoIP service on startup
     */
    fun selfTest() {
        try {
            getCountry("1.1.1.1")
        } catch (e: Exception) {
            log.error("GeoIP Service Self Test Failed", e)
            throw e
        }
    }

    /**
     * Get the IP address from a request
     */
    fun getIP(request: HttpServletRequest): Str =
        if (props.ipHeader.isEmpty()) request.remoteAddr else request.getHeader(props.ipHeader) ?: request.remoteAddr

    /**
     * Get the country code from an IP address
     */
    fun getCountry(ip: Str): Str
    {
        return try {
            geoLite.country(InetAddress.getByName(ip)).country.isoCode
        } catch (e: Exception) {
            log.error("Failed to get country from IP $ip", e)
            ""
        }
    }
}