package com.bci.data.domain.repository

import com.bci.data.domain.model.Encounter
import com.bci.data.domain.model.Pokedex
import com.bci.data.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun fetchPokemons() : Flow<Pokedex>
    suspend fun fetchPokemon(pokemonId: String) : Flow<Pokemon>
    suspend fun fetchEncounterPokemon(pokemonId: String) : Flow<Encounter>
    suspend fun fetchEvolutionPokemon(pokemonId: String) : Flow<String>
}