package icu.samnyan.aqua.spring.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Configuration
public class Config {

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(-1);
        return multipartResolver;
    }
}
