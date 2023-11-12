package com.example.playground.http.jdbcClient

import org.springframework.jdbc.core.namedparam.SimplePropertySqlParameterSource
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

    fun insert(): Int = jdbcClient
            .sql("INSERT INTO SAMPLE_TABLE (id, name) VALUES (?1, ?2)",)
            .paramSource(
                Response(
                    id = 1,
                    name = "Super Mario Odyssey",
                )
            ).update()

    fun update(): Int = jdbcClient
            .sql("UPDATE SAMPLE_TABLE SET name = ?1 WHERE id = ?2")
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