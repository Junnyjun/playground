package com.example.playground.http.jdbcClient

import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource
import org.springframework.jdbc.core.simple.JdbcClient
import org.springframework.stereotype.Component

@Component
class SampleJdbcClient(
    private val jdbcClient: JdbcClient
) {

    fun findAll(): MutableList<Response> = jdbcClient
            .sql("SELECT * FROM sample_entity",)
            .query(Response::class.java)
            .list()

    fun insert(): Int = jdbcClient
            .sql("INSERT INTO sample_entity (id, name) VALUES (?1, ?2)",)
            .paramSource(
                Response(
                    id = 1,
                    name = "Super Mario Odyssey",
                )
            ).update()

    fun update(): Int = jdbcClient
            .sql("UPDATE sample_entity SET name = :id WHERE id = :name")
            .paramSource(
                Response(
                    id = 1,
                    name = "Super Mario Odyssey",
                )
            ).update()

    data class Response(
        val id: Int,
        val name: String,
    )
}