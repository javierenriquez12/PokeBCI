package com.bci.data.domain.model

data class Pokedex(
    val count: Int? = null,
    val next: String? = null,
    val previous: String? = null,
    val pokemonList: List<Result>
)