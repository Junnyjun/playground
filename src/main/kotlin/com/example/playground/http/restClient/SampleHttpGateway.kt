package com.example.playground.http.restClient

import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import java.lang.reflect.ParameterizedType

@Component
class SampleHttpGateway(
    private val sampleapisClient: RestClient
) {
    fun getSampleApi(): List<Response>? {
       return sampleapisClient.get()
            .uri("/switch/games")
            .retrieve()
            .body<List<Response>>()
    }

    data class Response(
        val id: Int,
        val name: String,
        val genre: List<String>,
        val developers: List<String>,
        val publishers: List<String>,
        val releaseDates: Map<String, String>
    )

    fun postSampleApi(): Response? {
        return sampleapisClient.post()
            .uri("/switch/games")
            .body(
                Request(
                    name = "Super Mario Odyssey",
                    genre = listOf("Platformer", "Adventure"),
                    developers = listOf("Nintendo"),
                    publishers = listOf("Nintendo"),
                    releaseDates = mapOf("NA" to "2017-10-27", "EU" to "2017-10-27", "JP" to "2017-10-27")
                )
            )
            .retrieve()
            .body<Response>()
    }

    class Request(
        val name: String,
        val genre: List<String>,
        val developers: List<String>,
        val publishers: List<String>,
        val releaseDates: Map<String, String>
    )

}