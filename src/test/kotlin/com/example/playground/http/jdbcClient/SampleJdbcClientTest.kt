package com.example.playground.http.jdbcClient

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SampleJdbcClientTest{

        val result = SampleJdbcClient(DatasourceConfiguration().jdbcClient())
    @Test
    fun `getSampleApi`(){
        result.findAll()

        assertNotNull(result)
    }
}