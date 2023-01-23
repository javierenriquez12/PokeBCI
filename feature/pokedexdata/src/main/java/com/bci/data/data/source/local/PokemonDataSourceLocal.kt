package com.bci.data.data.source.local

import com.bci.local.PokedexDao
import com.bci.local.entity.PokemonEntityDao
import javax.inject.Inject

class PokemonDataSourceLocal @Inject constructor(private val dao: PokedexDao) {

    suspend fun fetchPokemons() : List<PokemonEntityDao> = dao.fetchPokemons()

    suspend fun insertPokemons(pokemons: List<PokemonEntityDao>){
        dao.insertPokemons(pokemons)
    }

    suspend fun clear() {
        dao.clear()
    }
}