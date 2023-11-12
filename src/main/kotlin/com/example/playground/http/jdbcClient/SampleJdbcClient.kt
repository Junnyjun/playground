package com.example.playground.http.jdbcClient

import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Component

@Component
class SampleJdbcClient(
    private val jdbcClient: JdbcClient
) {

    fun findAll(): MutableList<Response> = jdbcClient
            .sql("SELECT * FROM SAMPLE_TABLE",)
            .query(Response::class.java)
            .list()

    data class Response(
        val id: Int,
        val name: String,
    )
}