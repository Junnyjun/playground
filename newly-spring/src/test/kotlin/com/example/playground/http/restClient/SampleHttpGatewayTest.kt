package com.example.playground.http.restClient

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class SampleHttpGatewayTest{

    val sampleHttpGateway = SampleHttpGateway(RestClientConfiguration().restClient())

    @Test
    fun `getSampleApi`(){
        val result = sampleHttpGateway.getSampleApi()
        assertNotNull(result)
    }

    @Test
    fun `postSampleApi`(){
        val result = sampleHttpGateway.postSampleApi()
        assertNotNull(result)
    }
}