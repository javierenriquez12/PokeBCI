package com.bci.data.data.mapper

import com.bci.data.domain.model.Encounter

object PokemonEncounterMapper {

    fun encounterEntityToDomain(listEncounter: List<com.bci.data.data.entity.pokemon.EncounterEntity>): Encounter {
        return Encounter(nameEncounters = listEncounter.map {
            it.location_area.name
        })
    }
}