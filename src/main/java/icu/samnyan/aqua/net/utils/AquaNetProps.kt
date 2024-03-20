package icu.samnyan.aqua.net.utils

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "aqua-net")
class AquaNetProps {
    var linkCardLimit: Int = 10
    var importBackupPath = "data/import-backups"
}