package com.bci.ui.pokedex.detail

import com.bci.data.domain.model.Pokemon

sealed class PokemonDetailUiState {
    data class Success(val pokemon: Pokemon) : PokemonDetailUiState()
    data class Error(val exception: Exception) : PokemonDetailUiState()
    data class LoadingDetail(val isLoad: Boolean) : PokemonDetailUiState()
    data class SuccessEncounter(val string:List<String>) : PokemonDetailUiState()
    data class SuccessEvolution(val idEvolution: String) : PokemonDetailUiState()
}