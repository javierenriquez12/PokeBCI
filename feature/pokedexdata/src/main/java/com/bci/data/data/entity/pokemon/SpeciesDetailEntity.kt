package com.bci.data.data.entity.pokemon

data class SpeciesDetailEntity(
    val evolution_chain: EvolutionEntity
)

data class EvolutionEntity(
    val url: String
)