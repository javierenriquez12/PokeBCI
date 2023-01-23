package com.bci.ui.pokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bci.data.domain.usecase.FetchPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(private val pokemonUseCase: FetchPokemonsUseCase) :
    ViewModel() {

    private var _fetchPokemons: MutableStateFlow<PokemonUiState> = MutableStateFlow(
        PokemonUiState.Success(
            emptyList()
        )
    )
    val fetchPokemons: StateFlow<PokemonUiState> = _fetchPokemons

    fun fetchPokemons() {
        viewModelScope.launch {
            pokemonUseCase().onStart {
                _fetchPokemons.value = PokemonUiState.Loading(true)
            }.catch { exception ->
                _fetchPokemons.value = PokemonUiState.Error(Exception(exception.message))
            }.collect {
                _fetchPokemons.value = PokemonUiState.Success(it.pokemonList)
            }
            _fetchPokemons.value = PokemonUiState.Loading(false)
        }
    }
}