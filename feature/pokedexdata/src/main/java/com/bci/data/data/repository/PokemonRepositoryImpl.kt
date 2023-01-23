package com.bci.data.data.repository

import com.bci.data.data.mapper.PokemonEncounterMapper.encounterEntityToDomain
import com.bci.data.data.mapper.PokemonMapper.listDaoToDomain
import com.bci.data.data.mapper.PokemonMapper.listToDomain
import com.bci.data.data.mapper.PokemonMapper.listToLocal
import com.bci.data.data.mapper.PokemonMapper.toDomain
import com.bci.data.data.source.local.PokemonDataSourceLocal
import com.bci.data.data.source.remote.PokemonDataSourceRemote
import com.bci.data.data.util.Constants
import com.bci.data.data.util.Utils
import com.bci.data.domain.model.Encounter
import com.bci.data.domain.model.Pokedex
import com.bci.data.domain.model.Pokemon
import com.bci.data.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val pokemonDataSourceLocal: PokemonDataSourceLocal,
    private val pokemonDataSourceRemote: PokemonDataSourceRemote,
) : PokemonRepository {

    override suspend fun fetchPokemons(): Flow<Pokedex> {
        return pokemonDataSourceRemote.fetchPokemons()
            .onEach {
                pokemonDataSourceLocal.insertPokemons(pokemons = it.listToLocal())
            }.map {
                it.listToDomain()
            }.catch {
                emit(listDaoToDomain(pokemonDataSourceLocal.fetchPokemons()))
            }
    }

    override suspend fun fetchPokemon(pokemonId: String): Flow<Pokemon> {
        return pokemonDataSourceRemote.fetchPokemon(pokemonId).map {
            it.toDomain()
        }
    }

    override suspend fun fetchEncounterPokemon(pokemonId: String): Flow<Encounter> {
        return pokemonDataSourceRemote.fetchEncounterPokemon(pokemonId).map {
            encounterEntityToDomain(it)
        }
    }

    override suspend fun fetchEvolutionPokemon(pokemonId: String): Flow<String> {
        return pokemonDataSourceRemote.fetchSpeciesPokemon(pokemonId).map {
            pokemonDataSourceRemote.fetchEvolutionPokemon(Utils.getIdPokemon(it.evolution_chain.url))
        }.map { it.first() }.map {
            if (it.chain.evolves_to.isNotEmpty())
                it.chain.evolves_to.map { secondLevel ->
                    when {
                        pokemonId.toInt() + Constants.LEVEL_UP
                                == Utils.getIdPokemon(secondLevel.species.url)
                            .toInt() -> {
                            Utils.getIdPokemon(secondLevel.species.url)
                        }
                        secondLevel.evolves_to.isNotEmpty() -> {
                            secondLevel.evolves_to.map { thirdLevel ->
                                if (pokemonId.toInt() + Constants.LEVEL_UP ==
                                    Utils.getIdPokemon(thirdLevel.species.url)
                                        .toInt()
                                ) {
                                    Utils.getIdPokemon(thirdLevel.species.url)
                                } else ""
                            }.first()
                        }
                        else -> ""
                    }
                }.first() else ""
        }
    }
}