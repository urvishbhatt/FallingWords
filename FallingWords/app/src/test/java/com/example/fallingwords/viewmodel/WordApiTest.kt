package com.example.fallingwords.viewmodel

import com.example.fallingwords.repository.network.WordsApi
import com.example.fallingwords.repository.network.WordsApiService
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.*

class WordApiTest {

    private lateinit var server : MockWebServer
    private lateinit var service : WordsApiService

    @Before
    fun setup() {
        server = MockWebServer()
        service = WordsApi.retrofitService
    }

    @After
    fun teardown() {
        server.close()
    }

    @Test
    fun testWordFecthingApis() = runBlocking<Unit> {
        val sampleResponse = this::class.java.getResource("/testwords.json").readText()

        server.enqueue(
            MockResponse()
                .setBody(sampleResponse)
        )

        val response = service.getWords()

        assertEquals(297, response.size)
    }
}