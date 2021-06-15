package icu.samnyan.aqua;

import icu.samnyan.aqua.sega.aimedb.AimeDbServer;
import icu.samnyan.aqua.spring.util.AutoChecker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication()
public class AquaServerApplication {

    public static void main(String[] args) throws Exception {
        final SpringApplicationBuilder builder = new SpringApplicationBuilder(AquaServerApplication.class);
        builder.beanNameGenerator(new AquaBeanNameGenerator());

        ConfigurableApplicationContext ctx = builder.run(args);

        final AimeDbServer aimeDbServer = ctx.getBean(AimeDbServer.class);
        aimeDbServer.start();

        final AutoChecker checker = ctx.getBean(AutoChecker.class);
        checker.check();
    }

}
