package com.bci.data.data.util

object Utils {
    fun getIdPokemon(url: String) = Regex("[0-9]+").findAll(url).last().value
}