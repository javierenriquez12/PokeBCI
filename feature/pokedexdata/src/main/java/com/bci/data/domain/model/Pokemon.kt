package com.bci.data.domain.model

data class Pokemon(
    val abilities: List<String>,
    val types: List<String>,
    val moves: List<String>,
    val speciesUrl: String,
    val locationEncounter: String,
    val name: String
)