package com.bci.data.di


import com.bci.data.data.repository.PokemonRepositoryImpl
import com.bci.data.data.source.remote.PokemonDataSourceRemote
import com.bci.data.data.source.remote.PokemonDataSourceRemoteImpl
import com.bci.data.domain.repository.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindsPokemonDataSource(
        pokemonDataSourceRemoteImpl: PokemonDataSourceRemoteImpl
    ): PokemonDataSourceRemote

    @Binds
    abstract fun bindsPokemonRepository(
        pokemonRepository: PokemonRepositoryImpl
    ): PokemonRepository
}