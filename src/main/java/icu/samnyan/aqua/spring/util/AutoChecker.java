package icu.samnyan.aqua.spring.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Objects;

/**
 * A simple boot check to warn user if there is some wrong config
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class AutoChecker {

    private final String LINEBREAK = System.getProperty("line.separator");

    private final String SERVER_PORT;
    private final String ALLNET_HOST_OVERRIDE;
    private final String ALLNET_PORT_OVERRIDE;
    private final String AIMEDB_BIND;
    private final int AIMEDB_PORT;
    private final boolean AIMEDB_ENABLED;
    private final boolean BILLING_ENABLED;
    private final int BILLING_PORT;
    private final boolean AQUAVIEWER_ENABLED;

    public AutoChecker(
            @Value("${server.host:}") String SERVER_PORT,
            @Value("${allnet.server.host:}") String ALLNET_HOST,
            @Value("${allnet.server.port:}") String ALLNET_PORT,
            @Value("${aimedb.server.address}") String AIMEDB_BIND,
            @Value("${aimedb.server.port}") int AIMEDB_PORT,
            @Value("${aimedb.server.enable}") boolean AIMEDB_ENABLED,
            @Value("${billing.server.port}") int BILLING_PORT,
            @Value("${billing.server.enable}") boolean BILLING_ENABLED,
            @Value("${aquaviewer.server.enable:true}") boolean AQUAVIEWER_ENABLED) {
        this.SERVER_PORT = SERVER_PORT;
        this.ALLNET_HOST_OVERRIDE = ALLNET_HOST;
        this.ALLNET_PORT_OVERRIDE = ALLNET_PORT;
        this.AIMEDB_BIND = AIMEDB_BIND;
        this.AIMEDB_PORT = AIMEDB_PORT;
        this.AIMEDB_ENABLED = AIMEDB_ENABLED;
        this.BILLING_PORT = BILLING_PORT;
        this.BILLING_ENABLED = BILLING_ENABLED;
        this.AQUAVIEWER_ENABLED = AQUAVIEWER_ENABLED;
    }

    public void check() {
        // Boot message
        System.out.println(" █████╗  ██████╗ ██╗   ██╗ █████╗ \n" +
                "██╔══██╗██╔═══██╗██║   ██║██╔══██╗\n" +
                "███████║██║   ██║██║   ██║███████║\n" +
                "██╔══██║██║▄▄ ██║██║   ██║██╔══██║\n" +
                "██║  ██║╚██████╔╝╚██████╔╝██║  ██║\n" +
                "╚═╝  ╚═╝ ╚══▀▀═╝  ╚═════╝ ╚═╝  ╚═╝\n" +
                "                                  ");

        if (AQUAVIEWER_ENABLED) {
            System.out.println("Aqua viewer at http://localhost/web/\n");
        } 

        System.out.println("======= Self test running =======");
        // Check aimedb
        System.out.print("        AimeDB    :  ");
        if(!AIMEDB_ENABLED) {
            System.out.println("DISABLED, SKIP");
        } else {
            String address = "127.0.0.1";
            if(!AIMEDB_BIND.equals("0.0.0.0")) {
                address = AIMEDB_BIND;
            }
            try (Socket test = new Socket(address, AIMEDB_PORT)){
                System.out.println("OK");
            } catch (Exception e) {
                System.out.println("ERROR!!");
                System.out.println(e.getMessage());
            }
        }

        // Check billing
        System.out.print("        Billing   :  ");
        if(!BILLING_ENABLED) {
            System.out.println("DISABLED, SKIP");
        } else {
            String host = ALLNET_HOST_OVERRIDE.equals("") ? "127.0.0.1" : ALLNET_HOST_OVERRIDE;
            try (Socket test = new Socket(host, BILLING_PORT)){
                System.out.println("OK");
            } catch (Exception e) {
                System.out.println("ERROR!!");
                System.out.println(e.getMessage());
            }
        }

        // Check http part
        System.out.print("        AllNet    :  ");
        StringBuilder allNetSb = new StringBuilder();
        if(ALLNET_HOST_OVERRIDE.equals("localhost")||ALLNET_HOST_OVERRIDE.startsWith("127.0.0.")) {
            System.out.print("WARNING!!   ");
            allNetSb.append("You are using loopback address.").append(LINEBREAK);
            allNetSb.append("Some game won't connect with loopback address,").append(LINEBREAK);
            allNetSb.append("please change setting in `application.properties`.").append(LINEBREAK);
        }

        RestTemplate restTemplate = new RestTemplate();
        String host = ALLNET_HOST_OVERRIDE.equals("") ? "127.0.0.1" : ALLNET_HOST_OVERRIDE;
        String port = ALLNET_PORT_OVERRIDE.equals("") ? SERVER_PORT : ALLNET_PORT_OVERRIDE;
        String url = "http://" + host + ":" + port + "/sys/test";
        try{
            ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);
            if(resp.getStatusCode().is2xxSuccessful() && Objects.equals(resp.getBody(), "Server running")) {
                System.out.println("OK");
            } else {
                System.out.println("FAIL!");
                allNetSb.append("Could not connect to ").append(url).append(LINEBREAK);
                allNetSb.append("Status code: ").append(resp.getStatusCodeValue()).append(LINEBREAK);
            }
        } catch (Exception e) {
            System.out.println("ERROR!");
            System.out.println(e.getCause().getMessage());
        }

        System.out.println();
        System.out.println(allNetSb.toString());
    }

}
