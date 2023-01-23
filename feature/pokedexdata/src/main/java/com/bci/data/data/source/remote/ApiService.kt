package com.bci.data.data.source.remote

import com.bci.data.data.entity.EvolutionEntity
import com.bci.data.data.entity.PokedexEntity
import com.bci.data.data.entity.pokemon.EncounterEntity
import com.bci.data.data.entity.pokemon.PokemonEntity
import com.bci.data.data.entity.pokemon.SpeciesDetailEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/v2/pokemon")
    suspend fun fetchPokemons(@Query("limit") limit: String): Response<PokedexEntity>

    @GET("api/v2/pokemon/{idPokemon}")
    suspend fun fetchPokemon(@Path("idPokemon") idPokemon: String): Response<PokemonEntity>

    @GET("api/v2/pokemon/{idPokemon}/encounters")
    suspend fun fetchEncountersPokemon(@Path("idPokemon") idPokemon: String) : Response<List<EncounterEntity>>

    @GET("api/v2/pokemon-species/{idPokemon}")
    suspend fun fetchSpeciesPokemon(@Path("idPokemon") idPokemon: String) : Response<SpeciesDetailEntity>

    @GET("api/v2/evolution-chain/{idPokemon}")
    suspend fun fetchEvolutionPokemon(@Path("idPokemon") idPokemon: String) : Response<EvolutionEntity>
}