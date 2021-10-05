package icu.samnyan.aqua.sega.aimedb;

import icu.samnyan.aqua.sega.aimedb.handler.impl.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class AimeDbServerInitializer extends ChannelInitializer<SocketChannel> {

    private final CampaignHandler campaignHandler;
    private final FeliCaLookupHandler feliCaLookupHandler;
    private final FeliCaLookup2Handler feliCaLookup2Handler;
    private final GoodbyeHandler goodbyeHandler;
    private final HelloHandler helloHandler;
    private final LogHandler logHandler;
    private final LookupHandler lookupHandler;
    private final Lookup2Handler lookup2Handler;
    private final RegisterHandler registerHandler;

    @Autowired
    public AimeDbServerInitializer(CampaignHandler campaignHandler, FeliCaLookupHandler feliCaLookupHandler, FeliCaLookup2Handler feliCaLookup2Handler, GoodbyeHandler goodbyeHandler, HelloHandler helloHandler, LogHandler logHandler, LookupHandler lookupHandler, Lookup2Handler lookup2Handler, RegisterHandler registerHandler) {
        this.campaignHandler = campaignHandler;
        this.feliCaLookup2Handler = feliCaLookup2Handler;
        this.feliCaLookupHandler = feliCaLookupHandler;
        this.goodbyeHandler = goodbyeHandler;
        this.helloHandler = helloHandler;
        this.logHandler = logHandler;
        this.lookupHandler = lookupHandler;
        this.lookup2Handler = lookup2Handler;
        this.registerHandler = registerHandler;
    }


    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("encoder", new AimeDbEncoder());
        pipeline.addLast("decoder", new AimeDbDecoder());
        pipeline.addLast("handler", new AimeDbRequestHandler(campaignHandler, feliCaLookupHandler, feliCaLookup2Handler, goodbyeHandler, helloHandler, logHandler, lookupHandler, lookup2Handler, registerHandler));

    }
}
