package com.bci.data.data.mapper

import com.bci.data.data.entity.PokedexEntity
import com.bci.data.data.entity.pokemon.PokemonEntity
import com.bci.data.data.util.Utils
import com.bci.local.entity.PokemonEntityDao
import com.bci.data.domain.model.Pokedex
import com.bci.data.domain.model.Pokemon
import com.bci.data.domain.model.Result

object PokemonMapper {
    fun PokedexEntity.listToDomain() = Pokedex(
        count = this.count,
        next = this.next,
        previous = this.previous,
        pokemonList = this.results.map {
            Result(
                Utils.getIdPokemon(it.url),
                it.name,
                it.url
            )
        }
    )

    fun PokemonEntity.toDomain() = Pokemon(
        abilities = this.abilities.map { it.ability.name },
        types = this.types.map { it.type.name },
        moves = this.moves.map { it.move.name },
        speciesUrl = this.species.url,
        locationEncounter = this.location_area_encounters,
        name = this.name
    )

    fun PokedexEntity.listToLocal() =
        this.results.mapIndexed { index, it ->
            PokemonEntityDao(
                index.toString(),
                it.name,
                it.url
            )
        }

    fun listDaoToDomain(pokemonList: List<PokemonEntityDao>) =
        Pokedex(
            pokemonList = pokemonList.map {
                Result(
                    it.id,
                    it.name,
                    it.url
                )
            }
        )
}