package com.bci.ui.util

object Utils {
    fun getUrlImagePokemon(id: String): String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${
            id
        }.png"
}
