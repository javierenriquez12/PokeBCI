package com.bci.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bci.local.entity.PokemonEntityDao

@Database(entities = [PokemonEntityDao::class], version = 1, exportSchema = false)
abstract class PokedexDB : RoomDatabase() {
    abstract fun pokedexDao() : PokedexDao
}