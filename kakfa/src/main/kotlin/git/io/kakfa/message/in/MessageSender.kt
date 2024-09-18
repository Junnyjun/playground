package git.io.kakfa.message.`in`

import org.springframework.cloud.stream.function.StreamBridge
import org.springframework.messaging.support.MessageBuilder
import org.springframework.messaging.support.MessageHeaderAccessor
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessageSender(
    private val streamBridge: StreamBridge
) {

    fun send(message: String) {
        streamBridge.send("message-out-0", MessageBuilder
            .withPayload(message)
            .setHeaders(MessageHeaderAccessor().also {
                it.setHeader("test-message", "message+${UUID.randomUUID()}")
            })
            .build()
        )

    }
}