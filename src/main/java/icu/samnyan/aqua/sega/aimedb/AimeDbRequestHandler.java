package icu.samnyan.aqua.sega.aimedb;

import icu.samnyan.aqua.sega.aimedb.handler.impl.*;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */

@Component
@Scope("prototype")
public class AimeDbRequestHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(AimeDbRequestHandler.class);

    private final CampaignHandler campaignHandler;
    private final FeliCaLookupHandler feliCaLookupHandler;
    private final GoodbyeHandler goodbyeHandler;
    private final HelloHandler helloHandler;
    private final LogHandler logHandler;
    private final LookupHandler lookupHandler;
    private final Lookup2Handler lookup2Handler;
    private final RegisterHandler registerHandler;

    @Autowired
    public AimeDbRequestHandler(CampaignHandler campaignHandler, FeliCaLookupHandler feliCaLookupHandler, GoodbyeHandler goodbyeHandler, HelloHandler helloHandler, LogHandler logHandler, LookupHandler lookupHandler, Lookup2Handler lookup2Handler, RegisterHandler registerHandler) {
        this.campaignHandler = campaignHandler;
        this.feliCaLookupHandler = feliCaLookupHandler;
        this.goodbyeHandler = goodbyeHandler;
        this.helloHandler = helloHandler;
        this.logHandler = logHandler;
        this.lookupHandler = lookupHandler;
        this.lookup2Handler = lookup2Handler;
        this.registerHandler = registerHandler;
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof Map) {
            int type = ((int) ((Map) msg).get("type"));
            ByteBuf data = (ByteBuf) ((Map) msg).get("data");
            switch (type) {
                case 0x0001:
                    feliCaLookupHandler.handle(ctx, data);
                    break;
                case 0x0004:
                    lookupHandler.handle(ctx, data);
                    break;
                case 0x0005:
                    registerHandler.handle(ctx, data);
                    break;
                case 0x0009:
                    logHandler.handle(ctx, data);
                    break;
                case 0x000b:
                    campaignHandler.handle(ctx, data);
                    break;
                case 0x000d:
                    registerHandler.handle(ctx, data);
                    break;
                case 0x000f:
                    lookup2Handler.handle(ctx, data);
                    break;
                case 0x0064:
                    helloHandler.handle(ctx, data);
                    break;
                case 0x0066:
                    goodbyeHandler.handle(ctx, data);
                    break;
            }
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        logger.info("Connection closed");
    }
}
