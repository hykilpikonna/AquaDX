package icu.samnyan.aqua.net.utils

import com.maxmind.geoip2.DatabaseReader
import ext.Bool
import jakarta.annotation.PostConstruct
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
    var enable: Bool = false

    lateinit var geoLitePath: String
}

@Service
class GeoIP(
    val props: GeoIPProperties
) {
    val log = LoggerFactory.getLogger(GeoIP::class.java)!!
    lateinit var geoLite: DatabaseReader

    @PostConstruct
    fun onLoad() {
        if (!props.enable) return

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

        // Self test
        try {
            getCountry("1.1.1.1")
        } catch (e: Exception) {
            log.error("GeoIP Service Self Test Failed", e)
            throw e
        }

        log.info("GeoIP Service Enabled")
    }

    /**
     * Get the country code from an IP address
     */
    fun getCountry(ip: String): String
    {
        if (!props.enable) return ""

        return try {
            geoLite.country(InetAddress.getByName(ip)).country.isoCode
        } catch (e: Exception) {
            log.error("Failed to get country from IP $ip", e)
            ""
        }
    }
}