package com.bci.data.data.entity

data class EvolutionEntity(
    val chain: ChainEntity,
)

data class ChainEntity(
    val species: SpeciesEntity,
    val evolves_to: List<ChainEntity>
)

data class SpeciesEntity(
    val name: String,
    val url: String
)