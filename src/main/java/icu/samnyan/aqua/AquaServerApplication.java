package icu.samnyan.aqua;

import icu.samnyan.aqua.sega.aimedb.AimeDbServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class AquaServerApplication {

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext ctx = SpringApplication.run(AquaServerApplication.class, args);

        final AimeDbServer aimeDbServer = ctx.getBean(AimeDbServer.class);
        aimeDbServer.start();
    }

}
