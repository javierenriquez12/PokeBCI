package com.bci.data.domain.usecase

import com.bci.data.domain.model.Pokedex
import com.bci.data.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPokemonsUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(): Flow<Pokedex> =
        pokemonRepository.fetchPokemons()
}