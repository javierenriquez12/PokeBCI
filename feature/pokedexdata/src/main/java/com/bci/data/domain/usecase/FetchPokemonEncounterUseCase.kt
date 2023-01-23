package com.bci.data.domain.usecase

import com.bci.data.domain.model.Encounter
import com.bci.data.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchPokemonEncounterUseCase @Inject constructor(private val pokemonRepository: PokemonRepository) {
    suspend operator fun invoke(pokemonId: String): Flow<Encounter> =
        pokemonRepository.fetchEncounterPokemon(pokemonId)
}