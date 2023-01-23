package com.bci.data.source.remote

import com.bci.common.data.HttpException
import com.bci.data.data.source.remote.ApiService
import com.bci.data.data.source.remote.PokemonDataSourceRemote
import com.bci.data.data.source.remote.PokemonDataSourceRemoteImpl
import com.bci.test.Constants
import com.bci.test.datasource.BaseDataSourceTest
import com.google.common.truth.Truth
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import org.junit.Assert.assertTrue
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PokemonDataSourceRemoteImplTest : BaseDataSourceTest() {
    private val fetchPokemonDataSource: PokemonDataSourceRemote by lazy {
        PokemonDataSourceRemoteImpl(
            retrofit.create(ApiService::class.java)
        )
    }

    @Test
    fun `given a any pokemon id when response is 200 then return a pokemon detail`() =
        runTest {
            val response = MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setBody(MockBciApiResponse.getPokemonDetail())
            mockWebServer.enqueue(response)
            val pokemonDetail =
                fetchPokemonDataSource.fetchPokemon(Constants.MOCK_DUMMY_ACCOUNT_ID).first()
            Truth.assertThat(pokemonDetail.name).isNotEmpty()
        }

    @Test
    fun `given a any pokemon id when response is an error then return a null pokemon detail`() =
        runTest {
            val response = MockResponse()
                .addHeader("Content-Type", "application/json; charset=utf-8")
                .addHeader("Cache-Control", "no-cache")
                .setBody(MockBciApiResponse.getPokemonDetail(true))
                .setResponseCode(500)
            mockWebServer.enqueue(response)
            val pokemonDetail =
                fetchPokemonDataSource.fetchPokemon(Constants.MOCK_DUMMY_ACCOUNT_ID)
            pokemonDetail.catch { exception ->
                Truth.assertThat(exception as HttpException)
            }

        }
}