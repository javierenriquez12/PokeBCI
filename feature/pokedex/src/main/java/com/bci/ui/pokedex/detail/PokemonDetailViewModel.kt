package com.bci.ui.pokedex.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bci.data.domain.usecase.FetchPokemonDetailUseCase
import com.bci.data.domain.usecase.FetchPokemonEncounterUseCase
import com.bci.data.domain.usecase.FetchPokemonEvolutionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val pokemonDetailUseCase: FetchPokemonDetailUseCase,
    private val pokemonEncounterUseCase: FetchPokemonEncounterUseCase,
    private val pokemonEvolutionUseCase: FetchPokemonEvolutionUseCase,

    ) :
    ViewModel() {

    private var _fetchDetailPokemon: MutableStateFlow<PokemonDetailUiState> = MutableStateFlow(
        PokemonDetailUiState.LoadingDetail(
            true
        )
    )
    val fetchDetailPokemon: StateFlow<PokemonDetailUiState> = _fetchDetailPokemon

    fun fetchPokemon(idPokemon: String) {
        viewModelScope.launch {
            pokemonDetailUseCase(idPokemon).collect {
                fetchAreaEncounter(idPokemon)
                fetchEvolution(idPokemon)
                _fetchDetailPokemon.value = PokemonDetailUiState.Success(it)
            }
        }
    }

    private fun fetchAreaEncounter(idPokemon: String) {
        viewModelScope.launch {
            pokemonEncounterUseCase(idPokemon).catch { exception ->
                _fetchDetailPokemon.value = PokemonDetailUiState.LoadingDetail(false)
                _fetchDetailPokemon.value = PokemonDetailUiState.Error(Exception(exception.message))
            }.collect {
                _fetchDetailPokemon.value = PokemonDetailUiState.SuccessEncounter(it.nameEncounters)
            }
        }
    }

    private fun fetchEvolution(idPokemon: String) {
        viewModelScope.launch {
            pokemonEvolutionUseCase(idPokemon)
                .catch { exception ->
                    PokemonDetailUiState.LoadingDetail(false)
                    _fetchDetailPokemon.value =
                        PokemonDetailUiState.Error(Exception(exception.message))
                }.collect {
                    _fetchDetailPokemon.value = PokemonDetailUiState.SuccessEvolution(it)
                    _fetchDetailPokemon.value = PokemonDetailUiState.LoadingDetail(false)
                }
        }
    }
}