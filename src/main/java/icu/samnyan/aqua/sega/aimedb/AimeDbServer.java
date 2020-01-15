package icu.samnyan.aqua.sega.aimedb;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
public class AimeDbServer {

    private static final Logger logger = LoggerFactory.getLogger(AimeDbServer.class);
    private final AimeDbServerInitializer aimeDbServerInitializer;
    private final int port;
    private final boolean enableAimeDb;

    public AimeDbServer(AimeDbServerInitializer aimeDbServerInitializer,
                        @Value("${aimedb.server.port}") int port,
                        @Value("${aimedb.server.enable}") boolean enableAimeDb) {
        this.aimeDbServerInitializer = aimeDbServerInitializer;
        this.port = port;
        this.enableAimeDb = enableAimeDb;
    }

    public void start() throws Exception {
        if (enableAimeDb) {
            ServerBootstrap bootstrap = new ServerBootstrap();
            EventLoopGroup boss = new NioEventLoopGroup();
            EventLoopGroup work = new NioEventLoopGroup();

            bootstrap.group(boss, work)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .channel(NioServerSocketChannel.class)
                    .childHandler(aimeDbServerInitializer)
                    .option(ChannelOption.SO_BACKLOG, 128);

            ChannelFuture f = bootstrap.bind(new InetSocketAddress(port)).sync();
            logger.info("Aime DB start up on port : " + port);
            f.channel().closeFuture();
        }
    }
}
