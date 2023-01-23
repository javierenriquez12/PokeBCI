package com.bci.data.data.entity.pokemon

data class PokemonEntity(
    val abilities: List<AbilitiesEntity>,
    val types: List<TypesEntity>,
    val moves: List<MovesEntity>,
    val location_area_encounters: String,
    val species: SpeciesEntity,
    val name: String
)
