package icu.samnyan.aqua.spring.configuration;

import org.eclipse.jetty.http.HttpVersion;
import org.eclipse.jetty.server.*;
import org.eclipse.jetty.util.resource.URLResourceFactory;
import org.eclipse.jetty.util.ssl.SslContextFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.net.URL;
import java.util.Arrays;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Configuration
public class Config {

    private final int SERVER_PORT;
    private final boolean ENABLE_BILLING;
    private final int BILLING_PORT;

    public Config(@Value("${server.port}") int SERVER_PORT,
                  @Value("${billing.server.port}") int BILLING_PORT,
                  @Value("${billing.server.enable}") boolean ENABLE_BILLING, Environment env) {
        this.SERVER_PORT = SERVER_PORT;
        this.BILLING_PORT = BILLING_PORT;
        this.ENABLE_BILLING = ENABLE_BILLING;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebServerFactoryCustomizer<JettyServletWebServerFactory> webServerFactoryCustomizer() {

        return new WebServerFactoryCustomizer<>() {

            @Override
            public void customize(JettyServletWebServerFactory factory) {

                factory.addServerCustomizers(new JettyServerCustomizer() {

                    @Override
                    public void customize(Server server) {

                        try (ServerConnector httpConnector = new ServerConnector(server)) {
                            httpConnector.setPort(SERVER_PORT);

                            if (ENABLE_BILLING) {
                                SslContextFactory.Server sslContextFactory = new SslContextFactory.Server();

                                // TLS_RSA_* ciphers must be enabled, otherwise Auth NG
                                String[] excludedCiphersWithoutTlsRsaExclusion = Arrays
                                        .stream(sslContextFactory.getExcludeCipherSuites())
                                        .filter(cipher -> !cipher.equals("^TLS_RSA_.*$")).toArray(String[]::new);

                                URL keystoreURL = getClass().getClassLoader().getResource("server.p12");
                                var resFac = new URLResourceFactory();
                                var res = resFac.newResource(keystoreURL);
                                System.out.println(res);
                                sslContextFactory.setKeyStoreResource(res);
                                sslContextFactory.setKeyStorePassword("aquaserver");
                                sslContextFactory.setCertAlias("ib");
                                sslContextFactory.setExcludeCipherSuites(excludedCiphersWithoutTlsRsaExclusion);
                                sslContextFactory.setSniRequired(false);

                                HttpConfiguration httpsConfiguration = new HttpConfiguration();
                                var cus = new SecureRequestCustomizer();
                                cus.setSniHostCheck(false);
                                httpsConfiguration.addCustomizer(cus);

                                try (ServerConnector httpsConnector = new ServerConnector(server,
                                        new SslConnectionFactory(sslContextFactory, HttpVersion.HTTP_1_1.asString()),
                                        new HttpConnectionFactory(httpsConfiguration))) {
                                    httpsConnector.setPort(BILLING_PORT);
                                    server.setConnectors(new Connector[]{httpConnector, httpsConnector});
                                }
                            } else {
                                server.setConnectors(new Connector[]{httpConnector});
                            }
                        }
                    }
                });

            }
        };
    }
}
