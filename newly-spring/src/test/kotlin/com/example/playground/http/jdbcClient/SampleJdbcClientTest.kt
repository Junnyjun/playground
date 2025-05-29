package com.example.playground.http.jdbcClient

import com.example.playground.jdbcClient.SampleJdbcClient
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.jdbc.core.simple.JdbcClient
import javax.sql.DataSource

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class SampleJdbcClientTest{
f
    @Autowired
    lateinit var datasource: DataSource

    lateinit var result: SampleJdbcClient

    @BeforeEach
    fun init(){
        result = SampleJdbcClient(jdbcClient = JdbcClient.create(datasource))
    }

    @Test
    fun `getSampleApi`(){
        result.findAll()

        assertNotNull(result)
    }
    @Test
    fun `insertSampleApi`(){
        result.insert()

        assertNotNull(result)
    }

    @Test
    fun `updateSampleApi`(){
        result.update()

        assertNotNull(result)
    }

}