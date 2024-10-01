//package git.io.kakfa.global
//
//import org.apache.kafka.streams.StreamsConfig
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.kafka.annotation.EnableKafkaStreams
//import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration
//import org.springframework.kafka.config.KafkaStreamsConfiguration
//
//@Configuration
//@EnableKafkaStreams
//class KafkaTopologyConfiguration {
//
//    @Bean(name = [KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME])
//    fun kafkaConfig(): KafkaStreamsConfiguration = KafkaStreamsConfiguration(
//        kafkaStreamsProperties()
//    )
//
//    @Bean
//    fun kafkaStreamsProperties(): Map<String, Any> = mapOf(
//        StreamsConfig.APPLICATION_ID_CONFIG to "kafka-streams",
//        "bootstrap.servers" to "146.56.115.136:10000,146.56.115.136:10001,146.56.115.136:10002",
//        "key.serializer" to "org.apache.kafka.common.serialization.StringSerializer",
//        "value.serializer" to "org.apache.kafka.common.serialization.JsonSerializer",
//        "key.deserializer" to "org.apache.kafka.common.serialization.StringDeserializer",
//        "value.deserializer" to "org.apache.kafka.common.serialization.JsonSerializer",
//        "auto.startup" to "false",
//    )
//}