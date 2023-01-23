package com.bci.data.domain.usecase

import com.bci.data.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPokemonEvolutionUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(pokemonId: String): Flow<String> =
        pokemonRepository.fetchEvolutionPokemon(pokemonId)
}