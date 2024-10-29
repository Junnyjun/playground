package com.example.demo.server

import io.netty.channel.group.ChannelGroup
import io.rsocket.ConnectionSetupPayload
import io.rsocket.RSocket
import io.rsocket.SocketAcceptor
import io.rsocket.core.RSocketServer
import io.rsocket.transport.netty.server.TcpServerTransport
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Mono


@Configuration
class SocketServer(
    @Value("\${server.port:8080}") private val port: Int,
    private val channel: ChannelGroup
) {
    private val log = LoggerFactory.getLogger(SocketServer::class.java)
    private val create = RSocketServer.create { setup: ConnectionSetupPayload, sendingSocket: RSocket ->
        val dataUtf8 = setup.dataUtf8
        channel.writeAndFlush(dataUtf8)
        Mono.just(sendingSocket)
    }

    fun start() {
        create.bind(TcpServerTransport.create(port)).subscribe { server ->
            log.info("Server started on port: $port")
            server.onClose().block()
        }
    }

}