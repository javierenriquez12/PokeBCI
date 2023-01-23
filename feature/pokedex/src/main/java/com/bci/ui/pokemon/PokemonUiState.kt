package com.bci.ui.pokemon

import com.bci.data.domain.model.Result

sealed class PokemonUiState {
    data class Success(val pokemons: List<Result>) : PokemonUiState()
    data class Error(val exception: Exception) : PokemonUiState()
    data class Loading(val isLoad: Boolean) : PokemonUiState()

}
