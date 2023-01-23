package com.bci.test.datasource

import com.bci.test.Constants
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

open class BaseDataSourceTest {
    val mockWebServer = MockWebServer()
    lateinit var retrofit: Retrofit
    private val client = OkHttpClient.Builder().build()

    @Before
    fun setUp() {
        mockWebServer.start(Constants.MOCK_SERVER_PORT)

        mockWebServer.url(Constants.BASE_URL).let {
            retrofit = Retrofit.Builder().baseUrl(it).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}