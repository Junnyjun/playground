package com.example.playground.http.restClient

import io.micrometer.observation.ObservationRegistry
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestClient


@Configuration
class RestClientConfiguration {
    private val logger = LoggerFactory.getLogger("[SAMPLE API]")

    @Bean("sampleapisClient")
    fun restClient(): RestClient {
        return RestClient.builder()
            .baseUrl("https://api.sampleapis.com")
            .defaultHeader()
            .defaultLogger()
            .observationRegistry(ObservationRegistry.create())
            .build()

    }


    private fun RestClient.Builder.defaultHeader(): RestClient.Builder = this
        .defaultHeaders {
            it.set("Accept", "application/json")
            it.set("Content-Type", "application/json")
        }

    private fun RestClient.Builder.defaultLogger(): RestClient.Builder = this
        .requestFactory(BufferingClientHttpRequestFactory(SimpleClientHttpRequestFactory()))
        .requestInterceptor { request, body, execution ->
            logger.info("Request: ${request.method} ${request.uri}")
                .let { execution.execute(request, body) }
                .also { logger.info("Response: ${it.statusCode} ${String(it.body.readAllBytes()).take(100)}")}
        }

}

