package git.io.kakfa.message.out

import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
interface SampleGateway {
    fun call(request: SampleRequest)

    class SampleMessageGateway(
        private val kafkaTemplate: KafkaTemplate<String, SampleRequest>
    ) : SampleGateway {
        private val log = LoggerFactory.getLogger("KafkaTemplate")

        override fun call(request: SampleRequest) {
            val sendResult = kafkaTemplate.send(
                MessageBuilder.createMessage(
                    request, MessageHeaders(mapOf("header" to "Titile A"))
                )
            )

            val defaultExecutor = sendResult.defaultExecutor()
            sendResult
        }

    }


    class SampleHttpGateway(
        private val restClient: RestClient
    ) : SampleGateway {
        override fun call(request: SampleRequest) {
            TODO("Not yet implemented")
        }
    }

    data class SampleRequest(
        val title: String,
        val message: String
    )
}