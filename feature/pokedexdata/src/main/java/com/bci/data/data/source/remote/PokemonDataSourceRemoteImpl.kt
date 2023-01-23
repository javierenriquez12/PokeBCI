package com.bci.data.data.source.remote

import com.bci.common.data.HttpException
import com.bci.data.data.entity.EvolutionEntity
import com.bci.data.data.entity.PokedexEntity
import com.bci.data.data.entity.pokemon.EncounterEntity
import com.bci.data.data.entity.pokemon.PokemonEntity
import com.bci.data.data.entity.pokemon.SpeciesDetailEntity
import com.bci.data.data.util.Constants.QUERY_PARAMS_LIMIT_POKEMONS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class PokemonDataSourceRemoteImpl @Inject constructor(
    private val apiService: ApiService
) : PokemonDataSourceRemote {
    override suspend fun fetchPokemons(): Flow<PokedexEntity> {
        return flow {
            val connect = apiService.fetchPokemons(
                limit = QUERY_PARAMS_LIMIT_POKEMONS
            )
            connect.body()?.let {
                emit(it)
            } ?: run {
                throw HttpException()
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchPokemon(pokemonId: String): Flow<PokemonEntity> {
        return flow {
            val connect = apiService.fetchPokemon(
                idPokemon = pokemonId
            )
            connect.body()?.let {
                emit(it)
            } ?: run {
                throw HttpException()
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchEncounterPokemon(pokemonId: String): Flow<List<EncounterEntity>> {
        return flow {
            val connect = apiService.fetchEncountersPokemon(
                idPokemon = pokemonId
            )
            connect.body()?.let {
                emit(it)
            } ?: run {
                throw HttpException()
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchSpeciesPokemon(pokemonId: String): Flow<SpeciesDetailEntity> {
        return flow {
            val connect = apiService.fetchSpeciesPokemon(
                idPokemon = pokemonId
            )
            connect.body()?.let {
                emit(it)
            } ?: run {
                throw HttpException()
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun fetchEvolutionPokemon(pokemonId: String): Flow<EvolutionEntity> {
        return flow {
            val connect = apiService.fetchEvolutionPokemon(
                idPokemon = pokemonId
            )
            connect.body()?.let {
                emit(it)
            } ?: run {
                throw HttpException()
            }
        }.flowOn(Dispatchers.IO)
    }

}