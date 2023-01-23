package com.bci.data.domain.usecase

import com.bci.data.domain.model.Pokemon
import com.bci.data.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPokemonDetailUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(pokemonId: String): Flow<Pokemon> =
        pokemonRepository.fetchPokemon(pokemonId)
}