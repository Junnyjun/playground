package git.io.cloud.integration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.integration.IntegrationMessageHeaderAccessor
import org.springframework.integration.dsl.IntegrationFlow
import org.springframework.integration.dsl.IntegrationFlowDefinition
import org.springframework.integration.kafka.dsl.Kafka
import org.springframework.integration.kafka.dsl.Kafka.publishSubscribeChannel
import org.springframework.integration.kafka.dsl.KafkaProducerMessageHandlerSpec
import org.springframework.integration.kafka.dsl.KafkaTemplateSpec
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.ProducerFactory
import org.springframework.kafka.support.DefaultKafkaHeaderMapper
import org.springframework.messaging.Message
import java.util.stream.Stream


@Configuration
class SampleIntegration {


    @Bean
    fun sendToKafkaFlow(): IntegrationFlow {
        return IntegrationFlow { flow: IntegrationFlowDefinition<*> ->
            flow.split{ Stream.generate { "a" } }
                .channel("toKafka")
                }
        }
    }

    @Bean
    fun mapper(): DefaultKafkaHeaderMapper {
        return DefaultKafkaHeaderMapper()
    }

    private fun kafkaMessageHandler(
        producerFactory: ProducerFactory<Int, String>, topic: String
    ): KafkaProducerMessageHandlerSpec<Int, String, *> {
        return Kafka
            .outboundChannelAdapter(producerFactory)
            .messageKey { m: Message<Any> ->
                m
                    .headers[IntegrationMessageHeaderAccessor.SEQUENCE_NUMBER]
            }
            .headerMapper(mapper())
            .partitionId { m: Message<Any>? -> 10 }
            .topicExpression("headers[kafka_topic] ?: '$topic'")
            .configureKafkaTemplate { t: KafkaTemplateSpec<Int, String> ->
                t.id(
                    "kafkaTemplate:$topic"
                )
            }
    }
