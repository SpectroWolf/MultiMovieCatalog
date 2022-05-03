package com.multilaser.multimoviecatalog.di

import com.multilaser.multimoviecatalog.repositories.MovieRepository
import com.multilaser.multimoviecatalog.repositories.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Singleton
    @Binds
    abstract fun bindRepository(
        movieRepository: MovieRepository
    ) : Repository
}