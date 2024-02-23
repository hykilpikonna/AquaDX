package icu.samnyan.aqua.sega.aimedb

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

/**
 * @author samnyan (privateamusement@protonmail.com)
 */
@Component
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
class AimeDbServerInitializer(
    val aimeDbRequestHandler: AimeDbRequestHandler
) : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        ch.pipeline().apply {
            addLast("encoder", AimeDbEncoder())
            addLast("decoder", AimeDbDecoder())
            addLast("handler", aimeDbRequestHandler)
        }
    }
}
