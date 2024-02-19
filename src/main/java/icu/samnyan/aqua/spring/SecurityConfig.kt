package icu.samnyan.aqua.spring

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig() {
    @Bean
    fun configure(http: HttpSecurity): SecurityFilterChain = http
        .headers { it.disable() }
        .cors(Customizer.withDefaults())
        .csrf { it.disable() }
        .authorizeHttpRequests { it.anyRequest().permitAll() }
        .build()
}
