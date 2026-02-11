package com.example.dinosaurios.di

import com.example.dinosaurios.data.datasource.DinosaurDataSource
import com.example.dinosaurios.data.local.LocalDinosaurDataSource
import com.example.dinosaurios.data.repository.DinosaurRepositoryImpl
import com.example.dinosaurios.domain.repository.DinosaurRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDinosaurDataSource(): DinosaurDataSource {
        // en un caso real, aqu se inyectara room o lo que sea necesario.
        // como 'repository' es un object esttico en el cdigo original, lo usamos directamente.
        return LocalDinosaurDataSource()
    }

    @Provides
    @Singleton
    fun provideDinosaurRepository(dataSource: DinosaurDataSource): DinosaurRepository {
        return DinosaurRepositoryImpl(dataSource)
    }
}
