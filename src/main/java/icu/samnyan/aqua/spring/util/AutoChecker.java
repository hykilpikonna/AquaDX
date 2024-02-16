package icu.samnyan.aqua.spring.util;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.client5.http.ssl.TrustAllStrategy;
import org.apache.hc.core5.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.Socket;
import java.util.Objects;

/**
 * A simple boot check to warn user if there is some wrong config
 *
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class AutoChecker {

    private final String LINEBREAK = System.lineSeparator();

    private final String SERVER_PORT;
    private final String ALLNET_HOST_OVERRIDE;
    private final String ALLNET_PORT_OVERRIDE;
    private final String AIMEDB_BIND;
    private final int AIMEDB_PORT;
    private final boolean AIMEDB_ENABLED;
    private final boolean BILLING_ENABLED;
    private final int BILLING_PORT;
    private final boolean AQUAVIEWER_ENABLED;
    private final String VERSION_TAG;
    private final String BUILD_TIMESTAMP;

    public AutoChecker(
        @Value("${server.port:}") String SERVER_PORT,
        @Value("${allnet.server.host:}") String ALLNET_HOST,
        @Value("${allnet.server.port:}") String ALLNET_PORT,
        @Value("${aimedb.server.address}") String AIMEDB_BIND,
        @Value("${aimedb.server.port}") int AIMEDB_PORT,
        @Value("${aimedb.server.enable}") boolean AIMEDB_ENABLED,
        @Value("${billing.server.port}") int BILLING_PORT,
        @Value("${billing.server.enable}") boolean BILLING_ENABLED,
        @Value("${aquaviewer.server.enable}") boolean AQUAVIEWER_ENABLED,
        @Value("${build.version:N/A}") String VERSION_TAG,
        @Value("${build.timestamp:N/A}") String BUILD_TIMESTAMP) {
        this.SERVER_PORT = SERVER_PORT;
        this.ALLNET_HOST_OVERRIDE = ALLNET_HOST;
        this.ALLNET_PORT_OVERRIDE = ALLNET_PORT;
        this.AIMEDB_BIND = AIMEDB_BIND;
        this.AIMEDB_PORT = AIMEDB_PORT;
        this.AIMEDB_ENABLED = AIMEDB_ENABLED;
        this.BILLING_PORT = BILLING_PORT;
        this.BILLING_ENABLED = BILLING_ENABLED;
        this.AQUAVIEWER_ENABLED = AQUAVIEWER_ENABLED;
        this.VERSION_TAG = VERSION_TAG;
        this.BUILD_TIMESTAMP = BUILD_TIMESTAMP;
    }

    public void check() {
        String host = ALLNET_HOST_OVERRIDE.isEmpty() ? "127.0.0.1" : ALLNET_HOST_OVERRIDE;
        String port = ALLNET_PORT_OVERRIDE.isEmpty() ? SERVER_PORT : ALLNET_PORT_OVERRIDE;

        // Boot message
        System.out.println(
            " _____ _____ _____ _____ " + LINEBREAK +
                "|  _  |     |  |  |  _  |" + LINEBREAK +
                "|     |  |  |  |  |     |" + LINEBREAK +
                "|__|__|__  _|_____|__|__|" + LINEBREAK +
                "         |__|            " + LINEBREAK);

        System.out.println("Aqua local server");
        System.out.println("Version: " + VERSION_TAG + " (Built on " + BUILD_TIMESTAMP + ")\n");

        System.out.println("This is a free open-source software. If you paid for it, you were scammed.\n");

        System.out.println("[Web Interface]");
        if (AQUAVIEWER_ENABLED) {
            System.out.println("Enabled : http://" + host + ":" + port + "/web/" + LINEBREAK);
        } else {
            System.out.println("Disabled" + LINEBREAK);
        }

        System.out.println("[Self testing]");
        StringBuilder failDetail = new StringBuilder();

        /*
         * Aime DB: try open socket to Aime DB port (default 22345)
         * TODO: Sending hello request would be more reliable than testing if port is open
         */
        System.out.print("Aime DB : Port " + AIMEDB_PORT + ", ");
        if (!AIMEDB_ENABLED) {
            System.out.println("SKIP (DISABLED)");
        } else {
            String address = "127.0.0.1";
            if (!AIMEDB_BIND.equals("0.0.0.0")) {
                address = AIMEDB_BIND;
            }
            try (Socket ignored = new Socket(address, AIMEDB_PORT)) {
                System.out.println("OK");
            } catch (Exception e) {
                System.out.println("ERROR");
                failDetail.append("Aime DB self-test raised an exception during testing").append(LINEBREAK);
                failDetail.append("Exception: ").append(e).append(LINEBREAK);
            }
        }

        // Billing: try open socket to Billing port (default 8443)
        System.out.print("Billing : Port " + BILLING_PORT + ", ");
        if (!BILLING_ENABLED) {
            System.out.println("SKIP (DISABLED)");
        } else {
            try {
                // Do not validate SSL certificate (self-signed ib cert)
                CloseableHttpClient httpClient = HttpClients.custom()
                    .setConnectionManager(PoolingHttpClientConnectionManagerBuilder.create()
                        .setSSLSocketFactory(SSLConnectionSocketFactoryBuilder.create()
                            .setSslContext(SSLContextBuilder.create()
                                .loadTrustMaterial(TrustAllStrategy.INSTANCE)
                                .build())
                            .setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                            .build()).build()).build();

                HttpComponentsClientHttpRequestFactory requestFactory =
                    new HttpComponentsClientHttpRequestFactory();

                requestFactory.setHttpClient(httpClient);
                RestTemplate insecureRestTemplate = new RestTemplate(requestFactory);

                String url = "https://" + host + ":" + BILLING_PORT + "/sys/test";

                ResponseEntity<String> resp = insecureRestTemplate.getForEntity(url, String.class);
                if (resp.getStatusCode().is2xxSuccessful() && Objects.equals(resp.getBody(), "Server running")) {
                    System.out.println("OK");
                } else {
                    System.out.println("ERROR");
                    failDetail.append("Billing self-test failed").append(url).append(LINEBREAK);
                    failDetail.append("Check if billing port being used by other application.").append(LINEBREAK);
                }

            } catch (Exception e) {
                System.out.println("ERROR");
                failDetail.append("Billing self-test raised an exception during testing").append(LINEBREAK);
                failDetail.append("Exception: ").append(e).append(LINEBREAK);
            }
        }

        // ALL.Net: try access /sys/test endpoint (default 80)
        System.out.print("ALL.Net : Port " + port + ", ");

        if (ALLNET_HOST_OVERRIDE.equals("localhost") || ALLNET_HOST_OVERRIDE.startsWith("127.")) {
            System.out.print("WARN, ");
            failDetail.append("ALL.Net host is currently using loopback address.").append(LINEBREAK);
            failDetail.append("Game might not connect to server with this. If it was not intentional, please edit configuration file.").append(LINEBREAK);
        }

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://" + host + ":" + port + "/sys/test";
        try {
            ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
            if (resp.getStatusCode().is2xxSuccessful() && Objects.equals(resp.getBody(), "Server running")) {
                System.out.println("OK");
            } else {
                System.out.println("ERROR");
                failDetail.append("ALL.Net self-test could not connect to ").append(url).append(LINEBREAK);
                failDetail.append("Status code: ").append(resp.getStatusCode().value()).append(LINEBREAK);
            }
        } catch (Exception e) {
            System.out.println("ERROR");
            failDetail.append("ALL.Net self-test raised an exception during testing").append(url).append(LINEBREAK);
            failDetail.append("Exception: ").append(e).append(LINEBREAK);
        }

        System.out.println();
        System.out.println(failDetail);
    }

}
