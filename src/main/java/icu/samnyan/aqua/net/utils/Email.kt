package icu.samnyan.aqua.net.utils

import ext.Bool
import ext.Str
import icu.samnyan.aqua.net.db.AquaNetUser
import org.simplejavamail.api.mailer.Mailer
import org.simplejavamail.email.EmailBuilder
import org.simplejavamail.springsupport.SimpleJavaMailSpringSupport
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.stereotype.Service

@Configuration
@ConfigurationProperties(prefix = "aqua-net.email")
class EmailProperties {
    var enable: Bool = false

    lateinit var senderName: Str

    lateinit var senderAddr: Str
}
