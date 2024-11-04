package com.example.demo.client

import io.rsocket.RSocket
import io.rsocket.core.RSocketConnector
import io.rsocket.transport.netty.client.TcpClientTransport
import io.rsocket.util.DefaultPayload


class RSocketChatClient(host: String?, port: Int) {
    private val rSocket: RSocket = RSocketConnector.create()
        .connect(TcpClientTransport.create(host!!, port))
        .block()
        ?: throw RuntimeException("Could not connect to server")

    fun sendMessage(message: String?) {
        rSocket.fireAndForget(DefaultPayload.create(message!!)).block()
    }

    fun close() {
        rSocket.dispose()
    }
}