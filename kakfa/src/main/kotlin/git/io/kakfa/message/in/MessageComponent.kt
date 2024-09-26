package git.io.kakfa.message.`in`

import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class MessageComponent(
    private val streamsBuilder: StreamsBuilder
) {
    private val log = LoggerFactory.getILoggerFactory().getLogger("MessageComponent")

    init {
        streamsBuilder.stream<String, String>("all-copy.employees.dept_emp")
            .peek{ key, value -> log.info("[$key]Received message: $value") }
            .map({ key, value -> KeyValue(key, value + "Changed") })
            .to("message-changed")

    }
}