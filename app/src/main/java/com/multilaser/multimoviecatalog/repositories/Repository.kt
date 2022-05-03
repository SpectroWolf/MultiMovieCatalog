package com.multilaser.multimoviecatalog.repositories

import com.multilaser.multimoviecatalog.models.Movie
import com.multilaser.multimoviecatalog.models.MovieList
import retrofit2.Response


interface Repository {

    suspend fun getPopularMovieList(): Response<MovieList>

    suspend fun getTopRatedMovieList(): Response<MovieList>

    suspend fun searchMovie(search: String): Response<MovieList>

    suspend fun getMovieDetails(id: String): Response<Movie>

    suspend fun getMovieByGenre(id: String): Response<MovieList>

    suspend fun getRecommendations(id: String): Response<MovieList>
}