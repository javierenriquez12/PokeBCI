package com.bci.data.data.source.remote

import com.bci.data.data.entity.EvolutionEntity
import com.bci.data.data.entity.PokedexEntity
import com.bci.data.data.entity.pokemon.EncounterEntity
import com.bci.data.data.entity.pokemon.PokemonEntity
import com.bci.data.data.entity.pokemon.SpeciesDetailEntity
import kotlinx.coroutines.flow.Flow

interface PokemonDataSourceRemote {
    suspend fun fetchPokemons(): Flow<PokedexEntity>
    suspend fun fetchPokemon(pokemonId: String): Flow<PokemonEntity>
    suspend fun fetchEncounterPokemon(pokemonId: String) : Flow<List<EncounterEntity>>
    suspend fun fetchSpeciesPokemon(pokemonId: String) : Flow<SpeciesDetailEntity>
    suspend fun fetchEvolutionPokemon(pokemonId: String) : Flow<EvolutionEntity>
}